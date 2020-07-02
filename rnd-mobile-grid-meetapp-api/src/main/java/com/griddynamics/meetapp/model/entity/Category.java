package com.griddynamics.meetapp.model.entity;

import com.griddynamics.meetapp.util.EventPublicityType;
import com.griddynamics.meetapp.util.Identifiable;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="categories")
public class Category implements Identifiable {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "com.griddynamics.meetapp.configuration.IdentifiableUUIDGenerator"
    )
    private String id;

    private String title;

    @Enumerated(EnumType.STRING)
    private EventPublicityType publicityType;

    @OneToMany(mappedBy="category", fetch = FetchType.LAZY)
    @Column(insertable = false, updatable = false)
    private List<Event> events;
}
