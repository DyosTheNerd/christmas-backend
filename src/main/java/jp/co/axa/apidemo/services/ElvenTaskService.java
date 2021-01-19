package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.enums.TaskType;
import org.springframework.stereotype.Service;


@Service
public interface ElvenTaskService {


    /**
     * this method initiates a task, defined by its type and the linked ID.
     * @param type the type of the task to be initiated
     * @param linkedID a link to the object, the task about
     */
    public void createElvenTask(TaskType type, Long linkedID);

}
