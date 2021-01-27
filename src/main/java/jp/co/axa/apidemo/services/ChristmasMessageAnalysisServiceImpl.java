package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.dto.*;
import jp.co.axa.apidemo.entities.ChildrenFeedback;
import jp.co.axa.apidemo.entities.ChristmasMessage;
import jp.co.axa.apidemo.enums.WishType;
import jp.co.axa.apidemo.util.LogUtil;
import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizerME;
import opennlp.tools.lemmatizer.LemmatizerME;
import opennlp.tools.lemmatizer.LemmatizerModel;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * this service performs a NLP analysis with the Open NLP framework.
 */
@Service
public class ChristmasMessageAnalysisServiceImpl implements ChristmasMessageAnalysisService{

    @Autowired
    ChristmasMessageService christmasMessageService;

    @Autowired
    ChildrenFeedbackService childrenFeedbackService;

    @Autowired
    WishlistService wishlistService;

    // load the models as static variables. another option would be a singleton model provider.
    private static POSTaggerME posTaggerME;

    private static LemmatizerME lemmatizerME;

    private static DocumentCategorizerME categorizerME;

    private static DocumentCategorizerME sentimentME;

    private static SentenceDetectorME sentenceDetectorME;

    private static TokenizerME tokenizerME;


    /**
     * This method performs the analysis of the text from the given ChrismasMessageEntity with messageID.
     *
     * @param messageID
     * @return
     */
    @Override
    public List<String> analyseMessage(Long messageID) {
        ensureSetup();

        ChristmasMessage message = christmasMessageService.getChristmasMessage(messageID);


        // break the message into sentences
        String[] sentences = getSentencesFromString(message.getText());

        List<String> sentenceList = new ArrayList<String>();

        boolean automationDone = false;

        List<NLPAnalysisDTO> analysisWishes = new ArrayList<>();
        List<NLPAnalysisDTO> analysisFeedback = new ArrayList<>();

        // analyse each sentence
        for (String sentence: sentences) {
            String[] tokens = tokenizeSentence(sentence);
            String[] tags = detectPOSTags(tokens);
            String[] lemmata = lemmatizeTokens(tokens,tags);


            // main outcome is the category, upon which later an action is decided
            String category = detectCategory(lemmata);

            // sentence tagged as wish for wishlist
            if ("WISH".equals(category)){
                analysisWishes.add(getAnalysisDTO(category, lemmata, tags, tokens, sentence));
                automationDone = true;
            }

            // sentence tagged as feedback for feedback db
            if ("FEEDBACK".equals(category)){
                analysisFeedback.add(getAnalysisDTO(category, lemmata, tags, tokens, sentence));
                automationDone = true;
            }


            sentenceList.add(category + ": " + sentence);
        }


        generateWishes(analysisWishes, message);

        generateFeedback(analysisFeedback, message);


        // if the automatic process could retrieve neither a feedback nor a wish, we request manual checks.
        if (!automationDone){
            requestManualAssessment(message);
        }


        return sentenceList;
    }

    /**
     * this method generates the wishes from the sentences identified as wishes.
     *
     * the current implementation assumes an english sentence S V O where the object is the wish.
     * it extracts the subsentence after the first verb and the following noun or qualifier.
     *
     * There are several problems with this approach,
     *    - wishes could be expressed as a list. then the sentence 'dog.' would not return a wish.
     *    - a descriptive sentence 'a lego set would be cool', could in this domain be considered a wish, but here the wish is before the verb.
     *    - a enumeration of wishes connected with and count as a single wish, instead of two (or more) ex. 'a doll and a dollhouse'
     *
     * And a better implementation with machine learning might be possible, but would require more and qualified data, as the sentences and their corresponding wish could be used to feed such a model.
     *
     * Also, with more data, the features of quantity (a doll -> 1 doll) or the wish being (toy -> physical) or (daddy to work less ideal ) might be extractable.
     *
     * @param analysisWishes
     * @param message
     */
    private void generateWishes(List<NLPAnalysisDTO> analysisWishes, ChristmasMessage message) {
        WishListDTO wishListDto = new WishListDTO();
        List<WishDTO> wishes = new ArrayList<>();
        wishListDto.setWishes(wishes);
        for (NLPAnalysisDTO wishSentence : analysisWishes){
            String[] tags = wishSentence.getTags();
            String[] words = wishSentence.getTokens();
            StringBuilder wishBuilder = new StringBuilder();
            boolean firstVerb = false;
            boolean extract = false;
            for (int i = 0; i < wishSentence.getTags().length; i ++){
                if (tags[i] == "."){
                    continue;
                }
                if (firstVerb && (tags[i].startsWith("DT")||tags[i].startsWith("NN"))) {
                    extract= true;
                }
                if (tags[i].startsWith("VB")) {
                    firstVerb= true;
                }

                if (extract){
                    wishBuilder.append(words[i]).append(" ");
                }

            }
            WishDTO aWish = new WishDTO();
            aWish.setSubject(wishBuilder.toString());
            aWish.setQuantity(1);
            aWish.setWishType(WishType.PHYSICAL);
            wishes.add(aWish);


        }



        wishlistService.saveWishList(wishListDto, message.getId());
    }

    /**
     * this method inserts a feedback entry into the database.
     * those sentences were directly identified as feedback like, and could be interesting for santa to read, and improve his services.
     * @param analysisFeedback
     * @param message
     */
    private void generateFeedback(List<NLPAnalysisDTO> analysisFeedback, ChristmasMessage message) {
        ChildrenFeedbackListDTO feedbackDTO = new ChildrenFeedbackListDTO();
        ArrayList<ChildrenFeedbackDTO> list = new ArrayList<>();
        feedbackDTO.setFeedback(list);
        for (NLPAnalysisDTO analysisDTO: analysisFeedback) {
            ChildrenFeedbackDTO feedback = new ChildrenFeedbackDTO();
            feedback.setOriginalChristmasMessageID(message.getId());
            feedback.setTextExtract(analysisDTO.getSentence());
            feedback.setSentiment(detectFeedbackSentiment(analysisDTO.getLemmata()));
            list.add(feedback);

        }

        childrenFeedbackService.saveChildrenFeedback(feedbackDTO);
    }

    /**
     * This method fills an NLP DTO object
     */
    private NLPAnalysisDTO getAnalysisDTO(String category, String[] lemmata, String[] tags, String[] tokens, String sentence){
        NLPAnalysisDTO returnValue = new NLPAnalysisDTO();
        returnValue.setCategory(category);
        returnValue.setLemmata(lemmata);
        returnValue.setSentence(sentence);
        returnValue.setTags(tags);
        returnValue.setTokens(tokens);


        return returnValue;
    }


    /**
     * This method starts the process of manual analysis.
     * @param message
     */
    private void requestManualAssessment(ChristmasMessage message) {
    }


    /**
     * This method splits the given text into an array of sentences.
     *
     * @param text an english text.
     * @return The same text, but split into its sentences.
     */
    public String[] getSentencesFromString(String text){


        String[] sentences = sentenceDetectorME.sentDetect(text);
        System.out.println("Sentence Detection: " + Arrays.stream(sentences).collect(Collectors.joining(" | ")));

        return sentences;


    }


    /**
     * Break sentence into words & punctuation marks using tokenizer feature of
     * Apache OpenNLP.
     *
     * @param sentence A single sentence String.
     * @return the sentence split into an array of strings, where each string represents a token (which is mostly a word and punctuation).
     */
    private static String[] tokenizeSentence(String sentence) {
            // Tokenize sentence.
            String[] tokens = tokenizerME.tokenize(sentence);
            LogUtil.log("Tokenizer : " + Arrays.stream(tokens).collect(Collectors.joining(" | ")));
            return tokens;
    }

    /**
     * Find part-of-speech or POS tags of all tokens using POS tagger feature of
     * Apache OpenNLP.
     *
     * @param tokens The tokens of a sentence as an array
     * @return an Array of POSTag Strings, describing the token grammar.
     */
    private static String[] detectPOSTags(String[] tokens)  {
        String[] posTokens = posTaggerME.tag(tokens);
        LogUtil.log("POS Tags : " + Arrays.stream(posTokens).collect(Collectors.joining(" | ")));
        return posTokens;
    }

    /**
     * Find lemma of tokens using lemmatizer feature of Apache OpenNLP.
     *
     * @param tokens
     * @param posTags
     * @return
     */
    private static String[] lemmatizeTokens(String[] tokens, String[] posTags)
    {

        String[] lemmaTokens = lemmatizerME.lemmatize(tokens, posTags);
        LogUtil.log("Lemmatizer : " + Arrays.stream(lemmaTokens).collect(Collectors.joining(" | ")));

        return lemmaTokens;

    }



    /**
     * Detect category using given token. Use categorizer feature of Apache OpenNLP.

     * @param finalTokens
     * @return
     */
    private static String detectCategory(String[] finalTokens)  {
        // Initialize document categorizer tool

        // Get best possible category.
        double[] probabilitiesOfOutcomes = categorizerME.categorize(finalTokens);
        String category = categorizerME.getBestCategory(probabilitiesOfOutcomes);
        LogUtil.log("Category: " + category);
        return category;
    }

    private static String detectFeedbackSentiment(String[] finalTokens) {
        // Initialize document categorizer tool

        // Get best possible category.
        double[] probabilitiesOfOutcomes = sentimentME.categorize(finalTokens);
        String category = sentimentME.getBestCategory(probabilitiesOfOutcomes);
        LogUtil.log("Feedback Sentiment: " + category);
        return category;
    }


    /**
     * This method ensures that the models are loaded into the static variables, so that they can be used
     * immediately and also in subsequent processes
     */
    private void ensureSetup(){
        if (posTaggerME == null){
            try {
                ClassPathResource cpResource = new ClassPathResource("openNLPmodels//en-pos-maxent.bin");
                // Initialize POS tagger tool
                posTaggerME = new POSTaggerME(new POSModel(cpResource.getInputStream()));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (lemmatizerME == null) {

            try {
                ClassPathResource cpResource = new ClassPathResource("openNLPmodels//en-lemmatizer.bin");


                // Tag sentence.
                lemmatizerME = new LemmatizerME(new LemmatizerModel(cpResource.getInputStream()));

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        if (categorizerME == null){

            try {
                ClassPathResource cpResource = new ClassPathResource("openNLPmodels//categories.bin");
                 categorizerME = new DocumentCategorizerME(new DoccatModel(cpResource.getInputStream()));
                // Get best possible category.

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (sentimentME == null){

            try {
                ClassPathResource cpResource = new ClassPathResource("openNLPmodels//sentiments.bin");
                sentimentME = new DocumentCategorizerME(new DoccatModel(cpResource.getInputStream()));
                // Get best possible category.

            } catch (IOException e) {
                e.printStackTrace();
            }
        }



        if (tokenizerME == null){
            try {
                ClassPathResource cpResource = new ClassPathResource("openNLPmodels//en-token.bin");
                // Initialize tokenizer tool
                tokenizerME = new TokenizerME(new TokenizerModel(cpResource.getInputStream()));


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (sentenceDetectorME == null) {
            try {
                ClassPathResource cpResource = new ClassPathResource("openNLPmodels//en-sent.bin");

                sentenceDetectorME = new SentenceDetectorME(new SentenceModel(cpResource.getInputStream()));

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
