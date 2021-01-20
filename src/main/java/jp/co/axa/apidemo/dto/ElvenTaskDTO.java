package jp.co.axa.apidemo.dto;

import jp.co.axa.apidemo.enums.TaskStatus;
import jp.co.axa.apidemo.enums.TaskType;
import lombok.Getter;
import lombok.Setter;

/**
 * This class is used to publish task information without providing user information.
 */
public class ElvenTaskDTO extends WebhookObject{

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private TaskType taskType;

    @Getter
    @Setter
    private TaskStatus taskStatus;

    @Getter
    @Setter
    private Long linkedObjectID;

    @Override
    public String getFilter() {
        return taskType.toString();
    }

    @Override
    public String asJSON() {
        return "TODO";
    }
}
