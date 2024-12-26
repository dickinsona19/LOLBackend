package com.GoatGrammer.LOLTournAPI.Tournament;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

    @Override
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @Override
    public Optional<Game> getGameById(Integer id) {
        return gameRepository.findById(id);
    }

    @Override
    public Game createGame(Game game) {
        return gameRepository.save(game);
    }

    @Override
    public Game updateGame(Integer id, Game updatedGame) {
        Optional<Game> gameOptional = gameRepository.findById(id);
        if (gameOptional.isPresent()) {
            Game existingGame = gameOptional.get();
            existingGame.setName(updatedGame.getName());
            return gameRepository.save(existingGame);
        } else {
            return null; // Handle case where game doesn't exist, or throw exception.
        }
    }

    @Override
    public void deleteGame(Integer id) {
        gameRepository.deleteById(id);
    }

    @Override
    public Optional<Game> findByName(String name) {
        return gameRepository.findByName(name);
    }
}
