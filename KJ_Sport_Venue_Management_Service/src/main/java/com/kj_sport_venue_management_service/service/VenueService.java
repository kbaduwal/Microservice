package com.kj_sport_venue_management_service.service;

import com.kj_sport_venue_management_service.dto.VenueDto;
import com.kj_sport_venue_management_service.dto.VenueInformationDTo;

import java.util.List;

public interface VenueService {
    VenueDto addVenue(VenueDto venueDto);
    VenueDto getByName(String venueName);
    List<VenueDto> getByPartialName(String partialName);
    VenueDto updateVenue(VenueDto venueDto);
}
