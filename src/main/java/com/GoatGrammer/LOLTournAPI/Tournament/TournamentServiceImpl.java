package com.GoatGrammer.LOLTournAPI.Tournament;

import com.GoatGrammer.LOLTournAPI.user.UserClass.User;
import com.GoatGrammer.LOLTournAPI.user.UserClass.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TournamentServiceImpl implements TournamentService {

    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private TournamentParticipantsRepository tournamentParticipantsRepository;
    @Autowired
    private com.GoatGrammer.LOLTournAPI.user.UserClass.UserRepository userRepository;
    @Override
    public List<Tournament> getAllTournaments() {
        return tournamentRepository.findAll();
    }

    @Override
    public Optional<Tournament> getTournamentById(Integer id) {
        return tournamentRepository.findById(id);
    }

    @Override
    public Tournament createTournament(Tournament tournament) {
        return tournamentRepository.save(tournament);
    }

    @Override
    public List<com.GoatGrammer.LOLTournAPI.user.UserClass.User> getCurrentUsers(Integer tournamentId) {
        return tournamentParticipantsRepository.findUsersByTournamentId(tournamentId);
    }
    @Override
    public List<Tournament> getTournamentsByGameName(String gameName) {
        return tournamentRepository.findByGame_Name(gameName); // Query based on game name
    }
    @Override
    public com.GoatGrammer.LOLTournAPI.user.UserClass.User setHost(Integer userId) {

        com.GoatGrammer.LOLTournAPI.user.UserClass.User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        return user; // return the host User object with ID
    }
}