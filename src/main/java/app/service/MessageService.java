package app.service;

import app.dao.MessageDAO;
import app.exception.MessageException;
import app.model.Message;
import app.validation.Validator;
import app.validation.ValidatorFactory;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Where message related business logic is based.
 */
@Service
public class MessageService {

    private final MessageDAO messageDAO;
    private final ValidatorFactory validatorFactory;

    @Autowired
    public MessageService(MessageDAO messageDAO, ValidatorFactory validatorFactory) {
        this.messageDAO = messageDAO;
        this.validatorFactory = validatorFactory;
    }

    /**
     * Emoiton payload is validated here. Should not be null/empty or containing digits.
     * Length 2-10 is validated by Hibernate.
     * @param msg
     * @return
     * @throws MessageException
     */
    public Message send(Message msg) throws MessageException {
        System.out.println("SEND METHOD IS CALLED !");
        ValidatorFactory validatorFactory = new ValidatorFactory();
        Validator validator = validatorFactory.getValidator(msg);
        //handle validation
        validator.validate(msg);

        saveMessage(msg);
        return msg;
    }

    private void saveMessage(Message msg) throws MessageException {
        try {
            messageDAO.save(msg);
        } catch (HibernateException he) {
            throw new MessageException(he.getMessage(), he);
        }
    }
}
