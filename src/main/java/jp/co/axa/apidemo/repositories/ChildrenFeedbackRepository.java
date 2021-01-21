package jp.co.axa.apidemo.repositories;

import jp.co.axa.apidemo.entities.ChildrenFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildrenFeedbackRepository extends JpaRepository<ChildrenFeedback,Long> {

}
