package app.validation;

import app.exception.MessageException;
import app.model.Message;
import org.springframework.util.StringUtils;

public class EmotionValidator implements Validator {

    private int PAYLOAD_MIN = 2;
    private int PAYLOAD_MAX = 10;

    @Override
    public String validate(Message msg) {

        if (StringUtils.isEmpty(msg.getPayload())) {
            throw new MessageException("Payload is empty.");
        }

        if (msg.getPayload().length() < PAYLOAD_MIN || msg.getPayload().length() > PAYLOAD_MAX) {
            throw new MessageException("Payload should be between 1 and 160 symbols.");
        }

        if (msg.getPayload().chars().anyMatch(Character::isDigit)) {
            throw new MessageException("Payload contains digits.");
        }
        return null;
    }
}
