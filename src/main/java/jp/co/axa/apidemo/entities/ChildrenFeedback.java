package jp.co.axa.apidemo.entities;

import jp.co.axa.apidemo.enums.TaskStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="CUSTOMER_FEEDBACK")
public class ChildrenFeedback {



    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "originalMessage_id", referencedColumnName = "id")
    private ChristmasMessage originalChristmasMessage;

    @Getter
    @Setter
    @Column(name="TEXT_EXTRACT")
    // a quote of text to qualify the feedback
    private String textExtract;



    @Getter
    @Setter
    @Column(name="SENTIMENT")
    // What kind of emotion was the most apparent in the feedback
    private String sentiment;


}
