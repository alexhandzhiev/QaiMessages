package app.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "message")
public class EmoteType extends Message {

    @Length(min = 2, max = 10)
    @Column(name = "message_payload")
    protected String payload;

    @Transient
    private int payloadMin = 2;
    @Transient
    private int payloadMax = 10;

    public EmoteType() {
        super();
    }

    public EmoteType(String payload, String createdAt) {
        super("send_emotion", createdAt);
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
