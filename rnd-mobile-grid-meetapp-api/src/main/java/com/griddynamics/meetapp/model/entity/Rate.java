package com.griddynamics.meetapp.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="rates")
public class Rate {

    @EmbeddedId
    @Builder.Default
    CompositeKey compositeKey = new CompositeKey();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("eventId")
    private Event event;

    @Setter
    private Integer rating; //TODO 1-5? we should add regex here or smth like this

    @Setter
    private String comment;

    public void setEvent(Event event) {
        this.event = event;
        this.compositeKey.setEventId(event.getId());
    }

    public void setUser(User user) {
        this.user = user;
        this.compositeKey.setUserId(user.getId());
    }
}