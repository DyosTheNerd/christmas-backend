package jp.co.axa.apidemo.repositories;

import jp.co.axa.apidemo.entities.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishListRepository  extends JpaRepository<WishList,Long> {
}
