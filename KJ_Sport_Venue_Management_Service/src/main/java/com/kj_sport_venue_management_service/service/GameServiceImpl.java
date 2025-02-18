package com.kj_sport_venue_management_service.service;

import com.kj_sport_venue_management_service.dto.GameDto;
import com.kj_sport_venue_management_service.entity.Game;
import com.kj_sport_venue_management_service.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    @Override
    public List<GameDto> getAllGames() {
        List<Game> games = gameRepository.findAll();
        return games.stream()
                .map(this::gameEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<GameDto> getGameById(Long gameId) {
        Optional<Game> gameById = gameRepository.findById(gameId);
        return gameById.stream()
                .map(this::gameEntityToDto)
                .findFirst();
    }

    @Override
    public Optional<GameDto> getGameByName(String gameName) {
        Optional<Game> byGameName = Optional.ofNullable(gameRepository.findByGameName(gameName));
        return byGameName.stream()
                .map(this::gameEntityToDto).findFirst();
    }

}
