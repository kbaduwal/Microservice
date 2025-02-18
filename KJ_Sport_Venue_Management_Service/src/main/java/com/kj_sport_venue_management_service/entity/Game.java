package com.kj_sport_venue_management_service.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "game_id")
    private Long gameId;

    @Column(name = "name", nullable = false, unique = true)
    private String gameName;

    @Column(name = "icon_url", nullable = false, unique = true)
    private String iconUrl;

    @Column(name = "description")
    private String description;

    @Column(name = "price_per_hour")
    private Double pricePerHour;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

}
