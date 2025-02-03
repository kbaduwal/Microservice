package com.kj_sport_venue_management_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class KjSportVenueManagementServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(KjSportVenueManagementServiceApplication.class, args);
    }

}
