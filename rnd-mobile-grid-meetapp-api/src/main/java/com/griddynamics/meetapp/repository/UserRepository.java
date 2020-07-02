package com.griddynamics.meetapp.repository;

import com.griddynamics.meetapp.model.entity.Rate;
import com.griddynamics.meetapp.model.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    Optional<User> findByEmail(String email);

    List<User> findAllByRegistrationsEventIdAndRegistrationsHasVisitedIsTrue(String eventId);
}
