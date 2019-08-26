package app.dao;

import app.model.Message;
import org.springframework.stereotype.Repository;

/**
 * Use MessageDAO to access operations regarding the message database table
 */
@Repository
public class MessageDAO extends AbstractHibernateDAO<Message> {

    /**
     * Saves different types of messages
     * @see app.model.EmoteType
     * @see app.model.MessageType
     */
    public void save(Message message) {
        create(message);
    }
}
