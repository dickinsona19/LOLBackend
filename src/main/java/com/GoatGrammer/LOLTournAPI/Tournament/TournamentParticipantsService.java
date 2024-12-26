package com.GoatGrammer.LOLTournAPI.Tournament;

import com.GoatGrammer.LOLTournAPI.user.UserClass.User;
import com.GoatGrammer.LOLTournAPI.user.UserClass.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TournamentParticipantsService {

    @Autowired
    private TournamentParticipantsRepository tournamentParticipantsRepository;

    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private UserRepository userRepository;

    public boolean addUserToTournament(Integer tournamentId, Integer userId) {
        Tournament tournament = tournamentRepository.findById(tournamentId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);

        if (tournament != null && user != null) {
            TournamentParticipants tournamentParticipant = new TournamentParticipants(tournament, user);
            tournamentParticipantsRepository.save(tournamentParticipant);
            return true;
        } else {
            return false; // Either the tournament or user was not found
        }
    }
}