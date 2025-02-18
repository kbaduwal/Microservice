package com.kj_sport_venue_management_service.controller;

import com.kj_sport_venue_management_service.dto.VenueDto;
import com.kj_sport_venue_management_service.dto.VenueInformationDTo;
import com.kj_sport_venue_management_service.dto.updateVenueDto;
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
        VenueDto createdVenue = venueService.create(venueDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdVenue);
    }

    @GetMapping("/{name}")
    public ResponseEntity<VenueInformationDTo> getVenueByName(@RequestParam("name") String venueName) {
        VenueInformationDTo venueInfo = venueService.getByName(venueName);
        return ResponseEntity.status(HttpStatus.OK).body(venueInfo);
    }

    @GetMapping("/search")
    public ResponseEntity<List<VenueInformationDTo>> getVenuesByPartialName(@RequestParam("name") String venueName) {

        List<VenueInformationDTo> venues = venueService.getByPartialName(venueName);
        return ResponseEntity.status(HttpStatus.OK).body(venues);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VenueInformationDTo> updateVenue(@PathVariable("id") Long id , @RequestBody updateVenueDto updateVenue ) {
        VenueInformationDTo updatedVenue = venueService.updateVenue(id,updateVenue);
        return ResponseEntity.status(HttpStatus.OK).body(updatedVenue);
    }

}
