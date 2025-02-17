package com.kj_sport_venue_management_service.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FacilityDto {
    private Long facilityId;
    private String name;
    private String iconUrl;
}
