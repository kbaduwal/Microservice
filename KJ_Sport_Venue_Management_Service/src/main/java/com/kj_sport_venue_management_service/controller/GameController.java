package com.kj_sport_venue_management_service.controller;

import com.kj_sport_venue_management_service.dto.GameDto;
import com.kj_sport_venue_management_service.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/game/")
public class GameController {
    @Autowired
    private GameService gameService;

    @PostMapping("/new")
    public ResponseEntity<GameDto> addGame(@RequestBody GameDto gameDto) {
        GameDto saveGame = gameService.addGame(gameDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveGame);
    }

    @GetMapping("/all")
    public ResponseEntity<List<GameDto>> getAllGames() {
        List<GameDto> allGames = gameService.getAllGames();
        return ResponseEntity.status(HttpStatus.OK).body(allGames);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameDto> getGameById(@PathVariable Long id) {
        Optional<GameDto> gameById = gameService.getGameById(id);
        return ResponseEntity.status(HttpStatus.OK).body(gameById.get());
    }

    @GetMapping("/gameName")
    public ResponseEntity<GameDto> getGameByName(@PathVariable String gameName) {
        Optional<GameDto> gameByName = gameService.getGameByName(gameName);
        return ResponseEntity.status(HttpStatus.OK).body(gameByName.get());
    }

}
