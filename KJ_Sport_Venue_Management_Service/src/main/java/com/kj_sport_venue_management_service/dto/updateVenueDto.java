package com.kj_sport_venue_management_service.dto;

import com.kj_sport_venue_management_service.entity.Facility;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@RequiredArgsConstructor
@Getter
@Setter
public class updateVenueDto {
    private String venueName;
    private String venueAddress;
    private String phoneNumber;
    private Integer capacity;
    private String operatingHours;
    private BigDecimal pricing;
    private Set<FacilityDto> facilities;
}
