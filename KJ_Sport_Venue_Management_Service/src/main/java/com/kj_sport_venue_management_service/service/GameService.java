package com.kj_sport_venue_management_service.service;

import com.kj_sport_venue_management_service.dto.GameDto;
import com.kj_sport_venue_management_service.entity.Game;

import java.util.List;
import java.util.Optional;

public interface GameService {
    GameDto addGame(GameDto gameDto);
    public List<GameDto> getAllGames();
    public Optional<GameDto> getGameByName(String gameName);
    Optional<GameDto> getGameById(Long gameId);
}
