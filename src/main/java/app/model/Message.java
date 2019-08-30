package app.model;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@Table(name = "message")
public class Message implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "message_type")
    MessageEnum type;
    @Column(name = "payload")
    String payload;
    @Column(name = "created_at")
    String createdAt;

    public Message() {}

    public Message(MessageEnum type, String payload, String createdAt) {
        this.type = type;
        this.payload = payload;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public MessageEnum getType() {
        return type;
    }

    public String getPayload() {
        return payload;
    };

    public String getCreatedAt() {
        return createdAt;
    }
}
