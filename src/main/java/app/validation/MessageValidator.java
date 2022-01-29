package app.validation;

import app.exception.MessageException;
import app.model.Message;
import org.springframework.util.StringUtils;

public class MessageValidator implements Validator {

    private int PAYLOAD_MIN = 1;
    private int PAYLOAD_MAX = 160;

    @Override
    public String validate(Message msg) {

        if (StringUtils.isEmpty(msg.getPayload())) {
            throw new MessageException("Payload is empty.");
        }

        if (msg.getPayload().length() < PAYLOAD_MIN || msg.getPayload().length() > PAYLOAD_MAX) {
            throw new MessageException("Payload should be between 1 and 160 symbols.");
        }
        return null;
    }
}
