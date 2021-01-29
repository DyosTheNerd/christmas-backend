package jp.co.axa.apidemo.services;

import org.activiti.engine.task.Task;

import java.util.Map;

public interface TaskService {

    Task getTaskForTypeAndMessageID(Long messageID, String taskName);

    void completeTask(String id, Long messageID, Map<String, Object> taskVariables);
}
