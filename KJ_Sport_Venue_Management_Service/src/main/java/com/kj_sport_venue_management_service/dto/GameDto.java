package com.kj_sport_venue_management_service.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GameDto {
    private Long gameId;
    private String gameName;
    private String iconUrl;
    private String description;
    private Double price;
}
