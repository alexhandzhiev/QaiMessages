package app.controller;

import app.exception.MessageException;
import app.model.EmoteType;
import app.service.MessageService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MessageController.class)
public class MessageControllerTest {

    @MockBean
    MessageService messageService;

    @Autowired
    MockMvc mockMvc;

    @DisplayName("Post send_message")
    @Test
    void whenValidInput_thenReturns201() throws Exception {
        mockMvc.perform(post("/messages/{type}", "send_message")
                .param("payload", "testValue"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @DisplayName("Post bad payload send_emotion")
    @Test
    void whenInvalidInput_thenReturns413() throws Exception {
        when(messageService.send(any(EmoteType.class))).thenThrow(new MessageException("Payload is bad."));
        mockMvc.perform(post("/messages/{type}", "send_emotion")
                .param("payload", "testPayload"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

}
