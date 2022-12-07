package info.inside.jwt.controller;

import info.inside.jwt.dto.MessageDto;
import info.inside.jwt.dto.JwtRequest;
import info.inside.jwt.dto.JwtResponse;
import info.inside.jwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/message")
    public ResponseEntity saveOrGetMessages(@RequestBody MessageDto message) {
        Integer numberMessages = userService.getNumberMessagesByRequest(message);
        if (Optional.ofNullable(numberMessages).isPresent()) {
            List<MessageDto> messages = userService.getMessagesByRequest(numberMessages, message);
            if (Optional.ofNullable(messages).isPresent()) {
                return new ResponseEntity<>(messages, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            MessageDto messageDto = userService.saveUserMessageInDB(message);
            if (Optional.ofNullable(messageDto).isPresent()) {
                return new ResponseEntity<>(messageDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
    }

    @PostMapping("/authenticate")
    public JwtResponse createAuthenticationToken(@RequestBody JwtRequest jwtRequest) {
        return userService.authenticate(jwtRequest);
    }
}
