package info.inside.jwt.service;

import info.inside.jwt.config.JwtProvider;
import info.inside.jwt.dto.JwtRequest;
import info.inside.jwt.dto.JwtResponse;
import info.inside.jwt.dto.MessageDto;
import info.inside.jwt.dto.convertors.MessageConvertor;
import info.inside.jwt.entities.Message;
import info.inside.jwt.entities.User;
import info.inside.jwt.repository.MessageRepository;
import info.inside.jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j
public class UserService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final MessageConvertor messageConvertor;
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;


    public Integer getNumberMessagesByRequest(MessageDto messageDto) {
        String message = messageDto.getText();
        if (Optional.ofNullable(message).isPresent()) {
            String[] words = message.split("\\s");
            if (words.length == 2 && Objects.equals(words[0], "history") && NumberUtils.isDigits(words[1])) {
                return Integer.parseInt(words[1]);
            }
        }
        return null;
    }

    public List<MessageDto> getMessagesByRequest(Integer numberMessages, MessageDto messageDto) {
        Optional<User> user = userRepository.findByName(messageDto.getUserName());
        if (user.isPresent()) {
            List<Message> userMessages = messageRepository.findLastMessagesByRequest(user.get().getId(), numberMessages);
            return userMessages
                    .stream()
                    .map(messageConvertor::toDto)
                    .toList();
        } else {
            return null;
        }
    }

    public MessageDto saveUserMessageInDB(MessageDto messageDto) {
        Optional<User> user = userRepository.findByName(messageDto.getUserName());
        if (user.isPresent()) {
            Message messageFromUser = Message.builder()
                    .user(user.get())
                    .text(messageDto.getText())
                    .build();
            Message newMessage = messageRepository.save(messageFromUser);
            return messageConvertor.toDto(newMessage);
        }
        return null;
    }

    public JwtResponse authenticate(JwtRequest jwtRequest) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getName(), jwtRequest.getPassword()));
            System.out.println(authentication);
        } catch (BadCredentialsException e) {
            log.error(e.getStackTrace());
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Name or password is wrong", e);
        }
        Optional<User> user = userRepository.findByName(jwtRequest.getName());
        if (user.isPresent()) {
            String jwtToken = jwtProvider.generateAccessToken(user.get().getName());
            return new JwtResponse(jwtToken);
        }
        return null;
    }
}
