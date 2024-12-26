package com.GoatGrammer.LOLTournAPI.Tournament;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Integer> {
    List<Tournament> findByGame_Name(String gameName); // Method to filter by game name

}