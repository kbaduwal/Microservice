package com.kj_sport_venue_management_service.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VenueDto {
    private Long venueId;
    private String venueName;
    private String venueAddress;
    private String phoneNumber;
    private Double latitude;
    private Double longitude;
    private Integer capacity;
    private String openingHours;
}
