package info.inside.jwt.repository;

import info.inside.jwt.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query(value = "select * from jwt.messages where user_id=?1 order by id desc limit ?2", nativeQuery = true)
    List<Message> findLastMessagesByRequest(long userId, int numberMessages);
}
