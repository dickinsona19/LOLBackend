package com.GoatGrammer.LOLTournAPI.Tournament;


import com.GoatGrammer.LOLTournAPI.Tournament.TournamentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface TournamentTypeRepository extends JpaRepository<TournamentType, Integer> {
    Optional<TournamentType> findByName(String name);
}
