package app.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "message")
public class MessageType extends Message {

    @Length(min = 1, max = 160)
    @Column(name = "message_payload")
    protected String payload;

    @Transient
    protected int payloadMin = 1;
    @Transient
    protected int payloadMax = 160;

    public MessageType() {
        super();
    }

    public MessageType(String payload, String createdAt) {
        super("send_message", createdAt);
        this.payload = payload;
    }

    public int getPayloadMin() {
        return payloadMin;
    }

    public int getPayloadMax() {
        return payloadMax;
    }

    public String getPayload() {
        return payload;
    }
}
