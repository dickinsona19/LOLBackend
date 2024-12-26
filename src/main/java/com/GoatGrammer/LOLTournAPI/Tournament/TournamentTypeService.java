package com.GoatGrammer.LOLTournAPI.Tournament;

import java.util.List;
import java.util.Optional;

public interface TournamentTypeService {

    List<TournamentType> getAllTournamentTypes();
    Optional<TournamentType> findByName(String name);
    TournamentType createTournamentType(TournamentType tournamentType);
}
