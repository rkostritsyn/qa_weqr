package com.griddynamics.meetapp.security;

import org.springframework.security.core.GrantedAuthority;

import java.util.Objects;

public class GridMeetupUserAuthority implements GrantedAuthority {
    private Role authority;

    public GridMeetupUserAuthority(Role authority) {
        this.authority = authority;
    }

    public GridMeetupUserAuthority(String authority) {
        if (authority == null || authority.isEmpty()) {
            return;
        }
        this.authority = Role.valueOf(authority);
    }

    public String getAuthority() {
        return authority.name();
    }

    public String toString() {
        return authority.name();
    }

    public int compareTo(Object o) {
        return String.valueOf(this).compareTo(String.valueOf(o));
    }

    @Override
    public boolean equals(Object o) {
        return compareTo(o) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(authority);
    }
}
