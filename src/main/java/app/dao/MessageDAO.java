package app.dao;

import app.model.Message;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MessageDAO extends AbstractHibernateDAO<Message> {

    public void save(Message message) {
        create(message);
    }
    public List<Message> findAll() { return findAll(); };
}
