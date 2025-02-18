package com.kj_sport_venue_management_service.service;

import com.kj_sport_venue_management_service.dto.GameDto;
import com.kj_sport_venue_management_service.dto.VenueDto;
import com.kj_sport_venue_management_service.dto.VenueInformationDTo;
import com.kj_sport_venue_management_service.entity.Game;
import com.kj_sport_venue_management_service.entity.Venue;
import com.kj_sport_venue_management_service.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService{

    @Autowired
    private GameRepository gameRepository;

    @Override
    public GameDto addGame(GameDto gameDto) {
        Optional<Game> existingGame = Optional.ofNullable(gameRepository.findByGameName(gameDto.getGameName()));

        if (existingGame.isPresent()) {
            throw new IllegalArgumentException(gameDto.getGameName() + " already exists");
        }

        Game game = gameDtoToEntity(gameDto);

        Game savedGame = gameRepository.save(game);
        return gameEntityToDto(savedGame);
    }

    private Game gameDtoToEntity(GameDto gameDto) {
        return Game.builder()
                .gameName(gameDto.getGameName())
                .description(gameDto.getDescription())
                .iconUrl(gameDto.getIconUrl())
                .pricePerHour(gameDto.getPrice())
                .build();
    }

    private GameDto gameEntityToDto(Game game) {
        return GameDto.builder()
                .gameId(game.getGameId())
                .gameName(game.getGameName())
                .description(game.getDescription())
                .iconUrl(game.getIconUrl())
                .price(game.getPricePerHour())
                .build();

    }

    public List<Game> getAllGames() {
        return (gameRepository.findAll());
    }

    Optional<Game> getGameById(Long gameId) {
        return gameRepository.findById(gameId);
    }

    public Optional<Game> getGameByName(String gameName) {
        return Optional.ofNullable(gameRepository.findByGameName(gameName));
    }


}
