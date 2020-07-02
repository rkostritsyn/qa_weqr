package com.griddynamics.meetapp.repository;

import com.griddynamics.meetapp.model.entity.Registration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegistrationRepository extends PagingAndSortingRepository<Registration, Long> {

    Optional<Registration> findByUserIdAndEventId(long userId, String eventId);

    Page<Registration> findAllByUserId(Pageable p, long userId);
}
