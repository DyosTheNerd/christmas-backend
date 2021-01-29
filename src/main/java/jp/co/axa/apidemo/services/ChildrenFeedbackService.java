package jp.co.axa.apidemo.services;


import jp.co.axa.apidemo.dto.ChildrenFeedbackListDTO;

/**
 * This class maintains the incoming feedback
 */
public interface ChildrenFeedbackService {

        /**
         * This Method creates an ordered List of  feedback items for the given message ID
         * @param feedbackDTO
         */
        void saveChildrenFeedback(ChildrenFeedbackListDTO feedbackDTO);

        ChildrenFeedbackListDTO getChildrenFeedbackForMessageID(Long messageID);

        void saveChildrenFeedbackWithTask(ChildrenFeedbackListDTO feedbackDTO, Long messageID);


}
