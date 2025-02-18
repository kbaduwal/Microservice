package com.kj_sport_venue_management_service.service;

import com.kj_sport_venue_management_service.dto.VenueDto;
import com.kj_sport_venue_management_service.dto.VenueInformationDTo;
import com.kj_sport_venue_management_service.dto.updateVenueDto;
import com.kj_sport_venue_management_service.entity.Venue;

import java.util.List;

public interface VenueService {
    VenueDto create(VenueDto venueDto);
    VenueInformationDTo getByName(String venueName);
    List<VenueInformationDTo> getByPartialName(String partialName);
    VenueInformationDTo updateVenue(Long id, updateVenueDto updateVenueDto);
}
