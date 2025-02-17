package com.kj_sport_venue_management_service.repository;

import com.kj_sport_venue_management_service.entity.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FacilityRepository extends JpaRepository<Facility, Long> {
}
