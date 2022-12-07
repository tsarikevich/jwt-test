package info.inside.jwt.repository;

import info.inside.jwt.entities.Message;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MessageRepositoryTest {

    @Autowired
    private MessageRepository messageRepository;

    @Test
    void findLastMessagesByRequest() {
        List<Message> messages = messageRepository.findLastMessagesByRequest(1, 5);
        Assertions.assertThat(messages.size()).isLessThanOrEqualTo(5);
    }
}