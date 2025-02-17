package com.kj_sport_venue_management_service.service;

import com.kj_sport_venue_management_service.dto.FacilityDto;
import com.kj_sport_venue_management_service.dto.VenueDto;
import com.kj_sport_venue_management_service.dto.VenueInformationDTo;
import com.kj_sport_venue_management_service.dto.updateVenueDto;
import com.kj_sport_venue_management_service.entity.Facility;
import com.kj_sport_venue_management_service.entity.Venue;
import com.kj_sport_venue_management_service.repository.FacilityRepository;
import com.kj_sport_venue_management_service.repository.VenueRepository;
import com.sun.jdi.request.DuplicateRequestException;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class VenueServiceImpl implements VenueService {
    @Autowired
    VenueRepository venueRepository;

    @Autowired
    FacilityRepository facilityRepository;

    @Override
    @Transactional
    public VenueDto create(VenueDto venueDto) {
        if(venueDto == null) {
            throw new IllegalArgumentException("venueDto must not be null");
        }

        validateDuplicateVenue(venueDto);

        // Save facilities first
        Set<Facility> savedFacilities = new HashSet<>();
        if (venueDto.getFacilities() != null) {
            for (Facility facility : venueDto.getFacilities()) {
                // Clear any provided ID since we're creating new facilities
                facility.setFacilityId(null);
                savedFacilities.add(facilityRepository.save(facility));
            }
        }


        Venue venue = createVenue(venueDto, savedFacilities);
        Venue savedVenue = venueRepository.save(venue);

        return venueEntityToDto(savedVenue);
    }

    @Override
    public VenueInformationDTo getByName(String venueName) {
        if(StringUtils.isEmpty(venueName)){
            throw new IllegalArgumentException("venueName must not be empty");
        }

        List<Venue> venues = venueRepository.findByVenueName(venueName);
        if(venues.isEmpty()){
            throw  new NotFoundException("Venue with name " + venueName + " not found");
        }
        return venueToInformationDto(venues.get(0));
    }

    private void validateDuplicateVenue(VenueDto venueDto) {
        if(venueRepository.existsByVenueNameAndVenueAddress(venueDto.getVenueName(),venueDto.getVenueAddress())){
            throw new DuplicateRequestException("Venue name already exists");
        }
    }


    private Venue createVenue(VenueDto venueDto, Set<Facility> savedFacilities) {
        return Venue.builder()
                .venueName(venueDto.getVenueName())
                .venueAddress(venueDto.getVenueAddress())
                .phoneNumber(venueDto.getPhoneNumber())
                .latitude(venueDto.getLatitude())
                .longitude(venueDto.getLongitude())
                .capacity(venueDto.getCapacity())
                .operatingHours(venueDto.getOpeningHours())
                .pricing(venueDto.getPricing())
                .createdAt(venueDto.getCreatedAt())
                .updatedAt(venueDto.getUpdatedAt())
                .facilities(savedFacilities)
                .build();
    }

    private VenueDto venueEntityToDto(Venue venue) {
        return new VenueDto(
                venue.getVenueName(),
                venue.getVenueAddress(),
                venue.getPhoneNumber(),
                venue.getLatitude(),
                venue.getLongitude(),
                venue.getCapacity(),
                venue.getOperatingHours(),
                venue.getPricing(),
                venue.getCreatedAt(),
                venue.getUpdatedAt(),
                venue.getFacilities()
        );
    }

    private VenueInformationDTo venueToInformationDto(Venue venue) {
        return VenueInformationDTo.builder()
                .venueName(venue.getVenueName())
                .venueAddress(venue.getVenueAddress())
                .phoneNumber(venue.getPhoneNumber())
                .capacity(venue.getCapacity())
                .operatingHours(venue.getOperatingHours())
                .pricing(venue.getPricing())
                .facilities(venue.getFacilities().stream()
                        .map(facility -> FacilityDto.builder()
                                .facilityId(facility.getFacilityId())
                                .name(facility.getName())
                                .iconUrl(facility.getIconUrl())
                                .build()
                        ).collect(Collectors.toSet()))
                .build();
    }


    @Override
    public List<VenueInformationDTo> getByPartialName(String partialName) {
        //Converting input to lowercase for case-insensitive search
        String searchPattern = "%" + partialName.toLowerCase()+ "%";

        List<Venue> venues = venueRepository.findByNameContainingIgnoreCase(searchPattern);

        return venues.stream()
                .map(this::venueToInformationDto)
                .collect(Collectors.toList());

    }

    @Override
    public VenueInformationDTo updateVenue(Long id, updateVenueDto updateVenueDto) {
        //Fetch the existing venue by Id
        Venue venue = venueRepository.findById(id).orElseThrow(()
                ->new NotFoundException("Venue with id " + id + " not found"));

        if(updateVenueDto.getVenueName() != null){
            venue.setVenueName(updateVenueDto.getVenueName());
        }

        if(updateVenueDto.getVenueAddress() != null){
            venue.setVenueAddress(updateVenueDto.getVenueAddress());
        }
        if(updateVenueDto.getPhoneNumber() != null){
            venue.setPhoneNumber(updateVenueDto.getPhoneNumber());
        }

        if (updateVenueDto.getCapacity() != null){
            venue.setCapacity(updateVenueDto.getCapacity());
        }

        if(updateVenueDto.getOperatingHours() != null){
            venue.setOperatingHours(updateVenueDto.getOperatingHours());
        }

        if (updateVenueDto.getPricing() != null){
            venue.setPricing(updateVenueDto.getPricing());
        }

        if(updateVenueDto.getFacilities() != null && updateVenueDto.getFacilities().isEmpty()){
            Set<Facility> facilities = updateVenueDto.getFacilities().stream()
                    .map(facilityDto -> facilityRepository.findById(facilityDto.getFacilityId())
                            .orElseThrow(()-> new RuntimeException("Facility not found with ID: "+facilityDto.getFacilityId()))
                    ).collect(Collectors.toSet());

            venue.getFacilities().clear();
            venue.getFacilities().addAll(facilities);
        }

        Venue updatedVenue = venueRepository.save(venue);
        return venueToInformationDto(updatedVenue);
    }


}
