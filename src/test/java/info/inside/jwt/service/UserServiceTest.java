package info.inside.jwt.service;

import info.inside.jwt.dto.MessageDto;
import info.inside.jwt.repository.MessageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private MessageRepository messageRepository;

    @Test
    void getNumberMessagesByRequest() {
        MessageDto messageDto = new MessageDto(0, "Иван", "history 8");
        assertEquals(8, userService.getNumberMessagesByRequest(messageDto));
    }

    @Test
    void saveUserMessageInDB() {
        MessageDto messageDto = new MessageDto(0, "Иван", "Тестовое сообщение");
        MessageDto newMessage = userService.saveUserMessageInDB(messageDto);
        org.assertj.core.api.Assertions.assertThat(newMessage.getId()).isGreaterThan(1);
        messageRepository.deleteById(newMessage.getId());
    }

}