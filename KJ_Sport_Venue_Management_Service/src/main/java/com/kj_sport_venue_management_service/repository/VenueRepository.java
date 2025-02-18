package com.kj_sport_venue_management_service.repository;

import com.kj_sport_venue_management_service.entity.Venue;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VenueRepository extends JpaRepository<Venue, Long> {
    Venue findByVenueName(String venueName);
    Venue findByVenueId(Long venueId);
    boolean existsByVenueNameAndVenueAddress(String venueName, String venueAddress);

    @Query("SELECT v FROM Venue v WHERE LOWER(v.venueName) LIKE %:partialName%")
    List<Venue> findByNameContainingIgnoreCase(@Param("partialName") String partialName);
}
