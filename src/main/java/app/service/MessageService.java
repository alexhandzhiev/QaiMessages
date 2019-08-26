package app.service;

import app.dao.MessageDAO;
import app.exception.MessageException;
import app.model.EmoteType;
import app.model.Message;
import app.model.MessageType;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * Where message related business logic is based.
 */
@Service
public class MessageService {

    @Autowired
    MessageDAO messageDAO;

    /**
     * Emoiton payload is validated here. Should not be null/empty or containing digits.
     * Length 2-10 is validated by Hibernate.
     * @param msg
     * @return
     * @throws MessageException
     */
    public EmoteType send(EmoteType msg) throws MessageException {

        if (StringUtils.isEmpty(msg.getPayload())) {
            throw new MessageException("Payload is empty.");
        }

        if (msg.getPayload().chars().anyMatch(Character::isDigit)) {
            throw new MessageException("Payload contains digits.");
        }

        saveMessage(msg);
        return msg;
    }

    /**
     * Message payload is validated here. Should not be null/empty.
     * Length 1-160 is validated by Hibernate.
     * @param msg
     * @return
     * @throws MessageException
     */
    public MessageType send(MessageType msg) throws MessageException {

        if (StringUtils.isEmpty(msg.getPayload())) {
            throw new MessageException("Payload is empty.");
        }

        saveMessage(msg);
        return msg;
    }

    private void saveMessage(Message msg) throws MessageException {
        try {
            messageDAO.save(msg);
        } catch (HibernateException he) {
            throw new MessageException(he.getMessage(), he);
        } catch (ConstraintViolationException cv) {
            String violationMsg = "";
            Set<ConstraintViolation<?>> violations = cv.getConstraintViolations();
            for (ConstraintViolation<?> violation : violations) {
                violationMsg = violation.getMessage();
            }
            throw new MessageException(violationMsg, cv);
        }
    }
}
