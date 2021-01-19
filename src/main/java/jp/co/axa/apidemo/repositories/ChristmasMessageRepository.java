package jp.co.axa.apidemo.repositories;


import jp.co.axa.apidemo.entities.ChristmasMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChristmasMessageRepository extends JpaRepository<ChristmasMessage,Long> {
}
