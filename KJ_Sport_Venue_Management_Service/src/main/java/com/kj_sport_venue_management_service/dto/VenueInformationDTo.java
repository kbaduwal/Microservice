package com.kj_sport_venue_management_service.dto;

import com.kj_sport_venue_management_service.entity.Facility;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class VenueInformationDTo {
    private String venueName;
    private String venueAddress;
    private String phoneNumber;
    private Integer capacity;
    private String operatingHours;
    private BigDecimal pricing;
    private Set<Facility> facilities;
}
