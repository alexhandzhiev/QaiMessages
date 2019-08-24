package app.model;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@Table(name = "message")
public class Message implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    @Column(name = "message_type")
    protected String type;
    @Column(name = "created_at")
    protected String createdAt;

    public Message() {}

    public Message(String type, String createdAt) {
        this.type = type;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
