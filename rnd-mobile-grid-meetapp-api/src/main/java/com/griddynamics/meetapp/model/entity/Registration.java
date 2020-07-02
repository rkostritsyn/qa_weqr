package com.griddynamics.meetapp.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="registrations")
public class Registration {

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
    private Boolean hasVisited;

    public void setEvent(Event event) {
        this.event = event;
        this.compositeKey.setEventId(event.getId());
    }

    public void setUser(User user) {
        this.user = user;
        this.compositeKey.setUserId(user.getId());
    }
}
