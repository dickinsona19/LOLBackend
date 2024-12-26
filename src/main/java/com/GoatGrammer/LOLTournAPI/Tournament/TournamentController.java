package com.GoatGrammer.LOLTournAPI.Tournament;

import com.GoatGrammer.LOLTournAPI.user.UserClass.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import org.springframework.http.MediaType;
import java.time.Duration;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/tournaments")
public class TournamentController {

    @Autowired
    private TournamentParticipantsService tournamentParticipantsService;
    @Autowired
    private TournamentService tournamentService;

    @Autowired
    private GameService gameService;
    @Autowired
    private TournamentTypeService tournamentTypeService;
    @GetMapping
    public ResponseEntity<List<Tournament>> getAllTournaments() {
        List<Tournament> tournaments = tournamentService.getAllTournaments();
        return new ResponseEntity<>(tournaments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tournament> getTournamentById(@PathVariable Integer id) {
        Optional<Tournament> tournament = tournamentService.getTournamentById(id);
        return tournament.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Tournament> createTournament(@RequestBody TournamentDTO tournamentDTO) {
        // Retrieve the game by name
        Game game = gameService.findByName(tournamentDTO.getGameName())
                .orElseThrow(() -> new RuntimeException("Game not found"));

        // Retrieve the tournament type by name
        TournamentType tournamentType = tournamentTypeService.findByName(tournamentDTO.getTournamentTypeName())
                .orElseThrow(() -> new RuntimeException("Tournament type not found"));

        // Create and set up the Tournament entity
        Tournament tournament = new Tournament();
        tournament.setGame(game);
        tournament.setTournamentType(tournamentType);
        tournament.setName(tournamentDTO.getName());
        tournament.setDescription(tournamentDTO.getDescription());
        tournament.setStartDate(tournamentDTO.getStartDate());
        tournament.setEndDate(tournamentDTO.getEndDate());
        tournament.setMaxParticipants(tournamentDTO.getMaxParticipants());
        tournament.setHost(tournamentService.setHost(tournamentDTO.getHostId()));
        // Set other properties like host if needed

        // Save the tournament
        Tournament createdTournament = tournamentService.createTournament(tournament);

        return new ResponseEntity<>(createdTournament, HttpStatus.CREATED);
    }


    @GetMapping("/{id}/currentUsers")
    public ResponseEntity<List<com.GoatGrammer.LOLTournAPI.user.UserClass.User>> getCurrentUsers(@PathVariable Integer id) {
        List<com.GoatGrammer.LOLTournAPI.user.UserClass.User> users = tournamentService.getCurrentUsers(id);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/{tournamentId}/addUser/{userId}")
    public ResponseEntity<String> addUserToTournament(@PathVariable Integer tournamentId, @PathVariable Integer userId) {
        boolean isAdded = tournamentParticipantsService.addUserToTournament(tournamentId, userId);
        if (isAdded) {
            return new ResponseEntity<>("User added to tournament successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to add user to tournament.", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/game/{gameName}")
    public ResponseEntity<List<Tournament>> getTournamentsByGameName(@PathVariable String gameName) {
        List<Tournament> tournaments = tournamentService.getTournamentsByGameName(gameName);
        return new ResponseEntity<>(tournaments, HttpStatus.OK);
    }


    @GetMapping(value = "/tournaments/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamTournaments() {
        return Flux.interval(Duration.ofSeconds(5))
                .map(sequence -> {
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();
                        String json = objectMapper.writeValueAsString(tournamentService.getAllTournaments());
                        return json; // SSE format
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException("Error serializing tournaments", e);
                    }
                });
    }

}