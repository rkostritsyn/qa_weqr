package com.griddynamics.meetapp.model.entity;

import com.griddynamics.meetapp.util.Identifiable;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="events")
public class Event implements Identifiable {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "com.griddynamics.meetapp.configuration.IdentifiableUUIDGenerator"
            )
    private String id;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    private Date startDate;

    @Column(name="description", columnDefinition="LONGTEXT")
    private String description;

    private String address;

    private String image;

    @OneToMany(mappedBy="event", fetch = FetchType.LAZY)
    @Column(insertable=false, updatable=false)
    private List<Registration> registrations;

    @OneToMany(mappedBy="event", fetch = FetchType.LAZY)
    @Column(insertable=false, updatable=false)
    private List<Rate> rates;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "favorites",
            joinColumns = { @JoinColumn(name = "event_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    @Column(insertable = false, updatable = false)
    private List<User> favorites;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "speakers",
            joinColumns = { @JoinColumn(name = "event_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    @Column(insertable = false, updatable = false)
    private List<User> speakers;
}
