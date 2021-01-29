package jp.co.axa.apidemo.repositories;

import jp.co.axa.apidemo.entities.ChildrenFeedback;
import jp.co.axa.apidemo.entities.ChristmasMessage;
import jp.co.axa.apidemo.entities.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChildrenFeedbackRepository extends JpaRepository<ChildrenFeedback,Long> {


    List<ChildrenFeedback> findByOriginalChristmasMessage(ChristmasMessage originalChristmasMessage);

}
