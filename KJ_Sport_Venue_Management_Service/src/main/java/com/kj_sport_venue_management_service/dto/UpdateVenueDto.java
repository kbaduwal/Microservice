package com.kj_sport_venue_management_service.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;

@RequiredArgsConstructor
@Getter
@Setter
public class UpdateVenueDto {
    private String venueName;
    private String venueAddress;
    private String phoneNumber;
    private Integer capacity;
    private String operatingHours;
    private Set<GameDto> games;
}
