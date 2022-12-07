package info.inside.jwt.dto.convertors;

import info.inside.jwt.dto.MessageDto;
import info.inside.jwt.entities.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageConvertor {
    public MessageDto toDto(Message message) {
        return MessageDto.builder()
                .id(message.getId())
                .userName(message.getUser().getName())
                .text(message.getText())
                .build();
    }
}
