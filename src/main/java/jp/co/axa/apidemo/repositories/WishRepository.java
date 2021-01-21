package jp.co.axa.apidemo.repositories;

import jp.co.axa.apidemo.entities.Wish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishRepository extends JpaRepository<Wish,Long> {
}
