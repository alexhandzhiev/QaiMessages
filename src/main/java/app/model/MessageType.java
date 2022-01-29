package app.model;

import javax.persistence.Entity;
import javax.persistence.Table;

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
