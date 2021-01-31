package de.the.nerd.automaton.christmas.backend.services;


import de.the.nerd.automaton.christmas.backend.dto.ChildrenFeedbackListDTO;

/**
 * This class maintains the incoming feedback
 */
public interface ChildrenFeedbackService {

        /**
         * This Method creates an ordered List of  feedback items for the given message ID. It is only for service layer use. Use saveChildrenFeedbackWithTask for controller layer calls.
         * @param feedbackDTO the feedback to be saved
         */
        void saveChildrenFeedback(ChildrenFeedbackListDTO feedbackDTO);


        /**
         * This Method returns the list of feedback for the given message ID
         */
        ChildrenFeedbackListDTO getChildrenFeedbackForMessageID(Long messageID);

        /**
         * This method saves the feedback when a user task is given.
         */
        void saveChildrenFeedbackWithTask(ChildrenFeedbackListDTO feedbackDTO, Long messageID);


}
