package app.validation;

import app.model.Message;
import app.model.MessageEnum;
import org.springframework.stereotype.Component;

@Component
public class ValidatorFactory {

    public Validator getValidator(Message msg) {
        if (MessageEnum.SEND_MESSAGE.toString().equals(msg.getType())) {
            System.out.println("MESSAGE VALIDATOR IS CALLED.");
            return new MessageValidator();
        } else if (MessageEnum.SEND_EMOTION.toString().equals(msg.getType())) {
            System.out.println("EMOTION VALIDATOR IS CALLED.");
            return new EmotionValidator();
        } else {             System.out.println(msg.getType() + ": " + MessageEnum.SEND_EMOTION.toString() + " NULL VALIDATOR IS CALLED.");
        return null; }
    }
}
