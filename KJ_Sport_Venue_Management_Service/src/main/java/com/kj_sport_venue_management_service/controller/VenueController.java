package com.kj_sport_venue_management_service.controller;

import com.kj_sport_venue_management_service.dto.VenueDto;
import com.kj_sport_venue_management_service.dto.VenueInformationDTo;
import com.kj_sport_venue_management_service.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/venues")
public class VenueController {

    @Autowired
    private VenueService venueService;

    @PostMapping("/create")
    public ResponseEntity<VenueDto> addVenue(@RequestBody VenueDto venueDto) {
        VenueDto createdVenue = venueService.addVenue(venueDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdVenue);
    }

    @GetMapping("/{name}")
    public ResponseEntity<VenueDto> getVenueByName(@RequestParam("name") String venueName) {
        VenueDto venueInfo = venueService.getByName(venueName);
        return ResponseEntity.status(HttpStatus.OK).body(venueInfo);
    }

    @GetMapping("/search")
    public ResponseEntity<List<VenueDto>> getVenuesByPartialName(@RequestParam("name") String venueName) {

        List<VenueDto> venues = venueService.getByPartialName(venueName);
        return ResponseEntity.status(HttpStatus.OK).body(venues);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VenueDto> updateVenue(@PathVariable("id") Long venueId , @RequestBody VenueDto venueDto ) {

        if(!venueId.equals(venueDto.getVenueId())) {
            throw new IllegalArgumentException("Venue id does not match");
        }

        //Call service to update venue
        VenueDto updatedVenueDto = venueService.updateVenue(venueDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedVenueDto);
    }

}
