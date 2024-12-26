package com.GoatGrammer.LOLTournAPI.Tournament;


import com.GoatGrammer.LOLTournAPI.user.UserClass.User;

import java.util.List;
import java.util.Optional;

public interface TournamentService {
    List<Tournament> getAllTournaments();
    Optional<Tournament> getTournamentById(Integer id);
    Tournament createTournament(Tournament tournament);
    List<com.GoatGrammer.LOLTournAPI.user.UserClass.User> getCurrentUsers(Integer tournamentId);
    List<Tournament> getTournamentsByGameName(String gameName);
    com.GoatGrammer.LOLTournAPI.user.UserClass.User setHost(Integer userId);
}