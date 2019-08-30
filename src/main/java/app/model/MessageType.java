package app.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "message")
public class MessageType extends Message {

    public MessageType() {
        super();
    }

    public MessageType(String payload, String createdAt) {
        super(MessageEnum.SEND_MESSAGE, payload, createdAt);
        this.payload = payload;
    }
}
