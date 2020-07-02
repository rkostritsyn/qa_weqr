package com.griddynamics.meetapp.model.entity;

import com.griddynamics.meetapp.model.exception.NotFoundException;
import com.griddynamics.meetapp.model.exception.QrParsingException;
import com.griddynamics.meetapp.util.Constant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.*;
import java.util.Base64;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class CompositeKey implements Serializable {

    @Column(name = "event_id", insertable = false, updatable = false)
    private String eventId;

    @Column(name = "user_id", insertable = false, updatable = false)
    private Long userId;

    public CompositeKey(String qrCode){
        try {
            byte [] data = Base64.getDecoder().decode(qrCode);
            ObjectInputStream ois = new ObjectInputStream(
                    new ByteArrayInputStream(data));
            CompositeKey o = (CompositeKey) ois.readObject();
            ois.close();
            this.eventId = o.getEventId();
            this.userId = o.getUserId();
        } catch (IOException | ClassNotFoundException e) {
            throw new QrParsingException(Constant.ERRMSG_QR_PARSING, e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompositeKey)) return false;
        CompositeKey that = (CompositeKey) o;
        return Objects.equals(getEventId(), that.getEventId()) &&
                Objects.equals(getUserId(), that.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEventId(), getUserId());
    }

    @Override
    public String toString() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);
            oos.close();
            return Base64.getEncoder().encodeToString(baos.toByteArray());
        } catch (IOException e) {
            throw new QrParsingException(Constant.ERRMSG_QR_GENERATION, e);
        }
    }
}



