package com.kj_sport_venue_management_service.dto;

import com.kj_sport_venue_management_service.entity.Facility;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VenueDto {
    private String venueName;
    private String venueAddress;
    private String phoneNumber;
    private Double latitude;
    private Double longitude;
    private Integer capacity;
    private String openingHours;
    private BigDecimal pricing;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Set<Facility> facilities;
}
