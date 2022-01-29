package app.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "message")
public class EmoteType extends Message {

    public EmoteType() {
        super();
    }

    public EmoteType(String payload, String createdAt) {
        super(MessageEnum.SEND_EMOTION, payload, createdAt);
    }

    public String getPayload() {
        return payload;
    }
}
