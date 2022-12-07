package info.inside.jwt.repository;

import info.inside.jwt.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findByName() {
        Optional<User> user = userRepository.findByName("Иван");
        assertEquals(1,user.get().getId());
    }
}