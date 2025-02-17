package com.kj_sport_venue_management_service.entity;

import com.kj_sport_venue_management_service.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id" )
    private Long bookingId;

    @Column(name = "user_id")
    private String userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "booking_status")
    private BookingStatus bookingStatus;

    @ManyToOne
    @JoinColumn(name = "venue_id",nullable = false)
    private Venue venue;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;


}
