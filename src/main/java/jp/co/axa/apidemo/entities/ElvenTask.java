package jp.co.axa.apidemo.entities;

import jp.co.axa.apidemo.enums.TaskStatus;
import jp.co.axa.apidemo.enums.TaskType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ElvenTask {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private TaskType taskType;

    @Getter
    @Setter
    private TaskStatus taskStatus;

    @Getter
    @Setter
    private Employee resolvedBy;

    @Getter
    @Setter
    private String linkedObjectID;


}
