package app.service;

import app.exception.MessageException;
import app.model.EmoteType;
import app.model.MessageType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MessageServiceTest {

    @Autowired
    MessageService messageService;

    @DisplayName("Send send_emotion")
    @Test
    void testSendEmote() {
        EmoteType emoteType = new EmoteType("smiley", "here");
        EmoteType actual = (EmoteType) messageService.send(new EmoteType("smiley", "here"));
        assertAll("send_emotion should be sent correctly",
                () -> assertEquals(emoteType.getType(), actual.getType()),
                () -> assertEquals(emoteType.getPayload(), actual.getPayload())
        );
    }

    @DisplayName("Send send_emotion contains digits")
    @Test
    void testSendEmoteContainsDigits() {
        MessageException thrown = assertThrows(MessageException.class, () -> messageService.send(new EmoteType("1", "here")), "");
        assertEquals(thrown.getMessage(), "Payload contains digits.");
    }

    @DisplayName("Send send_emotion too long")
    @Test
    void testSendEmoteTooLong() {
        MessageException thrown = assertThrows(MessageException.class, () -> messageService.send(new EmoteType("https://www.eduard.com", "")));
        assertEquals(thrown.getMessage(), "length must be between 2 and 10");
    }

    @DisplayName("Send send_emotion too short")
    @Test
    void testSendEmoteTooShort() {
        MessageException thrown = assertThrows(MessageException.class, () -> messageService.send(new EmoteType("a", "here")));
        assertEquals(thrown.getMessage(), "length must be between 2 and 10");
    }

    @DisplayName("Send send_message")
    @Test
    void testSendMessage() {
        MessageType messageType = new MessageType("This is some test content.", "here");
        MessageType actual = (MessageType) messageService.send(new MessageType("This is some test content.", "here"));
        assertAll("send_message should be sent correctly",
                () -> assertEquals(messageType.getType(), actual.getType()),
                () -> assertEquals(messageType.getPayload(), actual.getPayload())
        );
    }

    @DisplayName("Send send_message too long")
    @Test
    void testSendMessageTooLong() {
        MessageException thrown = assertThrows(MessageException.class, () -> messageService.send(new MessageType("The Supermarine Spitfire is a British single-seat fighter aircraft used by the Royal Air Force and other Allied countries before, during, and after World War II.", "here")));
        assertEquals(thrown.getMessage(), "length must be between 1 and 160");
    }

    @DisplayName("Send send_message too short")
    @Test
    void testSendMessageTooShort() {
        MessageException thrown = assertThrows(MessageException.class, () -> messageService.send(new MessageType("", "here")));
        assertEquals(thrown.getMessage(), "Payload is empty.");
    }
}
