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
    private long id;

    @Getter
    @Setter
    @Id
    private TaskType taskType;

    @Getter
    @Setter
    @Id
    private TaskStatus taskStatus;





}
