package com.GoatGrammer.LOLTournAPI.Tournament;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tournamentTypes")
@CrossOrigin
public class TournamentTypeController {

    @Autowired
    private TournamentTypeService tournamentTypeService;

    // Endpoint to get all tournament types
    @GetMapping("/all")
    public ResponseEntity<List<TournamentType>> getAllTournamentTypes() {
        List<TournamentType> tournamentTypes = tournamentTypeService.getAllTournamentTypes();
        if (!tournamentTypes.isEmpty()) {
            return new ResponseEntity<>(tournamentTypes, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    // Endpoint to find a tournament type by name
    @GetMapping("/findByName")
    public ResponseEntity<TournamentType> getTournamentTypeByName(@RequestParam String name) {
        Optional<TournamentType> tournamentType = tournamentTypeService.findByName(name);
        if (tournamentType.isPresent()) {
            return new ResponseEntity<>(tournamentType.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint to create a new tournament type
    @PostMapping
    public ResponseEntity<TournamentType> createTournamentType(@RequestBody TournamentType tournamentType) {
        TournamentType createdTournamentType = tournamentTypeService.createTournamentType(tournamentType);
        return new ResponseEntity<>(createdTournamentType, HttpStatus.CREATED);
    }
}