package com.kj_sport_venue_management_service.repository;

import com.kj_sport_venue_management_service.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    Game findByGameName(String gameName);
}
