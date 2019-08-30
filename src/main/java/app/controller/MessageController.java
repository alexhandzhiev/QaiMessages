package app.controller;

import app.model.EmoteType;
import app.model.Message;
import app.model.MessageEnum;
import app.model.MessageType;
import app.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * The message control center.
 */
@RestController
@Validated
public class MessageController {

    @Autowired
    MessageService messageService;

    /**
     * The message endpoint which handles the receiving message/emotion.
     * Will return an empty body. 201 CREATED on success and 412 PRECONDITION_FAILED on failure.
     * @param type mandatory 'send_message' or 'send_emotion'
     * @param payload is optional on purpose, so we can handle it later, if we use annotation validation - it will
     *                throw 'Bad Request - 400' and we don't want that
     * @return ResponseEntity
     */
    @RequestMapping("/messages/{type}")
    @ResponseBody
    public ResponseEntity send(@PathVariable(value="type", required = true) String type,
                               @RequestParam(value = "payload", required = false) String payload) {
        messageService.send(new Message(MessageEnum.valueOf(type.toUpperCase()), payload, "here"));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleMissingParam() {
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
    }
}
