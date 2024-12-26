package com.GoatGrammer.LOLTournAPI.Tournament;

import java.util.List;
import java.util.Optional;

public interface GameService {
    List<Game> getAllGames();
    Optional<Game> getGameById(Integer id);
    Game createGame(Game game);
    Game updateGame(Integer id, Game updatedGame);
    void deleteGame(Integer id);
    Optional<Game> findByName(String name);
}
