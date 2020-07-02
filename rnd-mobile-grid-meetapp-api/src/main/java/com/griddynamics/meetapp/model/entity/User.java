package com.griddynamics.meetapp.model.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.google.common.base.MoreObjects;
import com.griddynamics.meetapp.security.GridMeetupUserAuthority;
import com.griddynamics.meetapp.security.Role;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Email
    private String email;

    private String name;

    private String password;

    private String phoneNumber; //TODO check for phoneNumber type? or add regex

    private String position;

    private String experiencedIn;

    @JsonAlias({"photo", "picture"})
    private String photo;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthenticationType authenticationType;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    @Column(insertable = false, updatable = false)
    private List<Registration> registrations;

    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    @Column(insertable = false, updatable = false)
    private List<Rate> rates;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "speakers",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "event_id") }
    )
    @Column(insertable = false, updatable = false)
    private List<Event> speakerAt;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "favorites",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "event_id") }
    )
    @Column(insertable = false, updatable = false)
    private List<Event> favorites;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new GridMeetupUserAuthority(getRole()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("email", email)
                .add("role", role)
                .toString();
    }

    public boolean isGridEmail() {
        String domain = this.email.substring(this.email.indexOf("@") + 1);
        return domain.equals("griddynamics.com");
    }

    public enum AuthenticationType {
        CUSTOM,
        GOOGLE
    }
}
