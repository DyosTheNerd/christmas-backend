package jp.co.axa.apidemo.repositories;

import jp.co.axa.apidemo.entities.ChristmasMessage;
import jp.co.axa.apidemo.entities.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishListRepository  extends JpaRepository<WishList,Long> {


    Optional<WishList> findByOriginalChristmasMessage(ChristmasMessage originalChristmasMessage);

}
