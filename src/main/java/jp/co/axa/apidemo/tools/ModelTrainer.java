package jp.co.axa.apidemo.tools;

import opennlp.tools.doccat.*;
import opennlp.tools.lemmatizer.*;
import opennlp.tools.util.MarkableFileInputStreamFactory;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.TrainingParameters;
import opennlp.tools.util.model.BaseModel;
import opennlp.tools.util.model.ModelUtil;

import java.io.*;

/**
 * This is a tool class for the generation of model binaries.
 */
public class ModelTrainer {

    public static final String basePath = ".//src//main//resources//";

    public static final String sentenceCategorizationTestDataPath = "openNLPTestData//categorization.txt";

    public static final String sentenceCategorizationModelPath = "openNLPmodels//categories.bin";

    public static final String lemmatizerDataPath = "openNLPTestData//en-lemmatizer.txt";

    public static final String lemmatizerModelPath = "openNLPmodels//en-lemmatizer.bin";

    public static final String sentimentsDataPath = "openNLPTestData//sentiments.txt";

    public static final String sentimentsModelPath = "openNLPmodels//sentiments.bin";



    private static DoccatModel trainCategorizerModel(String dataPath) {
        DoccatModel model = null;
        File dataIn = null;

        try {
            dataIn = new File(basePath + dataPath);
            MarkableFileInputStreamFactory markableFileInputStreamFactory = new MarkableFileInputStreamFactory(dataIn);
            ObjectStream<String> lineStream =
                    new PlainTextByLineStream(markableFileInputStreamFactory, "UTF-8");
            ObjectStream<DocumentSample> sampleStream = new DocumentSampleStream(lineStream);

            DoccatFactory factory = new DoccatFactory(new FeatureGenerator[]{new BagOfWordsFeatureGenerator()});

            TrainingParameters params = ModelUtil.createDefaultTrainingParameters();
            params.put(TrainingParameters.CUTOFF_PARAM, 0);

            model = DocumentCategorizerME.train("en", sampleStream, params, factory);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return model;
    }

    private static LemmatizerModel trainLemmatizerModel() {
        LemmatizerModel model = null;
        File dataIn = null;

        try {
            dataIn = new File(basePath + lemmatizerDataPath);

            MarkableFileInputStreamFactory markableFileInputStreamFactory = new MarkableFileInputStreamFactory(dataIn);
            ObjectStream<String> lineStream =
                    new PlainTextByLineStream(markableFileInputStreamFactory, "UTF-8");
            ObjectStream<LemmaSample> sampleStream = new LemmaSampleStream(lineStream);

            LemmatizerFactory factory = new LemmatizerFactory();

            TrainingParameters params = ModelUtil.createDefaultTrainingParameters();
            params.put(TrainingParameters.CUTOFF_PARAM, 0);

            model = LemmatizerME.train("en", sampleStream,params,factory);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return model;
    }

    private static void serializeModel(BaseModel model, String extension){
        try (OutputStream modelOut = new BufferedOutputStream(new FileOutputStream(basePath + extension))) {
            model.serialize(modelOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void trainCategories(){
        DoccatModel model = trainCategorizerModel(sentenceCategorizationTestDataPath);
        serializeModel(model, sentenceCategorizationModelPath);
    }

    public static void trainLemmatizer(){
        LemmatizerModel model = trainLemmatizerModel();
        serializeModel(model, lemmatizerModelPath);
    }

    public static void trainSentiments(){
        DoccatModel model = trainCategorizerModel(sentimentsDataPath);
        serializeModel(model, sentimentsModelPath);
    }


    public static void main(String args[]){

        trainSentiments();
    }


}
