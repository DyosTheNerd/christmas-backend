package jp.co.axa.apidemo.entities;

import jp.co.axa.apidemo.enums.TaskStatus;
import jp.co.axa.apidemo.enums.TaskType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="ELVENTASK")
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
    @JoinColumn(name = "RESOLVED_BY_ID", referencedColumnName = "ID")
    @OneToOne
    private Employee resolvedBy;

    @Getter
    @Setter
    private Long linkedObjectID;


}
