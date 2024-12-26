package com.GoatGrammer.LOLTournAPI.Tournament;

import com.GoatGrammer.LOLTournAPI.user.UserClass.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TournamentParticipantsRepository extends JpaRepository<com.GoatGrammer.LOLTournAPI.Tournament.TournamentParticipants, Integer> {

    @Query("SELECT tp.user FROM TournamentParticipants tp WHERE tp.tournament.id = ?1")
    List<com.GoatGrammer.LOLTournAPI.user.UserClass.User> findUsersByTournamentId(Integer tournamentId);
}