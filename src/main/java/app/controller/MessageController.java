package app.controller;

import app.model.EmoteType;
import app.model.MessageType;
import app.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
public class MessageController {

    @Autowired
    MessageService messageService;

    @RequestMapping("/messages/{type}")
    @ResponseBody
    public ResponseEntity send(@PathVariable(value="type", required = true) String type,
                               @RequestParam(value = "payload", required = false) String payload) {
        //required here is false, so we can handle 'payload' later, otherwise it will return 'Bad Request 400'
        if(type.equalsIgnoreCase("send_message")) {
            messageService.send(new MessageType(payload, "here"));
        }
        if(type.equalsIgnoreCase("send_emotion")) {
            messageService.send(new EmoteType(payload, "here"));
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleMissingParam() {
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
    }
}
