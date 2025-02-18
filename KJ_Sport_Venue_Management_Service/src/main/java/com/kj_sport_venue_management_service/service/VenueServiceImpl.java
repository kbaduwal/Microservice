package com.kj_sport_venue_management_service.service;

import com.kj_sport_venue_management_service.dto.VenueDto;
import com.kj_sport_venue_management_service.dto.VenueInformationDTo;
import com.kj_sport_venue_management_service.entity.Venue;
import com.kj_sport_venue_management_service.repository.VenueRepository;
import com.sun.jdi.request.DuplicateRequestException;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class VenueServiceImpl implements VenueService {

    @Autowired
    VenueRepository venueRepository;

    @Override
    public VenueDto addVenue(VenueDto venueDto) {

        Optional<Venue> existingVenue = Optional.ofNullable(venueRepository.findByVenueName(venueDto.getVenueName()));

        if(existingVenue.isPresent()){
            throw new IllegalArgumentException("Venue with name " + venueDto.getVenueName() + " already exists");
        }

        //Dto to Entity
        Venue venue = createVenue(venueDto);

        //Save the entity
        Venue savedVenue = venueRepository.save(venue);

        //return the converted value venue entity to DTO
        return venueEntityToDto(savedVenue);
    }

    @Override
    public VenueDto getByName(String venueName) {
       Venue venue = venueRepository.findByVenueName(venueName);

       if (venue == null) {
           throw new NotFoundException("Venue with name " + venueName + " not found");
       }

       return venueEntityToDto(venue);
    }

    private Venue createVenue(VenueDto venueDto) {
        return Venue.builder()
                .venueName(venueDto.getVenueName())
                .venueAddress(venueDto.getVenueAddress())
                .phoneNumber(venueDto.getPhoneNumber())
                .latitude(venueDto.getLatitude())
                .longitude(venueDto.getLongitude())
                .capacity(venueDto.getCapacity())
                .operatingHours(venueDto.getOpeningHours())
                .build();
    }

    private VenueDto venueEntityToDto(Venue venue) {
        return VenueDto.builder()
                .venueName(venue.getVenueName())
                .venueAddress(venue.getVenueAddress())
                .phoneNumber(venue.getPhoneNumber())
                .latitude(venue.getLatitude())
                .longitude(venue.getLongitude())
                .openingHours(venue.getOperatingHours())
                .build();
    }


    @Override
    public List<VenueDto> getByPartialName(String partialName) {
        //Converting input to lowercase for case-insensitive search
        String searchPattern = "%" + partialName.toLowerCase()+ "%";

        List<Venue> venues = venueRepository.findByNameContainingIgnoreCase(searchPattern);

        return venues.stream()
                .map(this::venueEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public VenueDto updateVenue(VenueDto venueDto) {
        Venue existingVenue = venueRepository.findById(venueDto.getVenueId()).orElseThrow(
                ()-> new RuntimeException("Venue not found with ID: "+venueDto.getVenueId()));

        Venue venue = createVenue(venueDto);
        Venue updatedVenue = venueRepository.save(venue);
        return venueEntityToDto(updatedVenue);
    }


}
