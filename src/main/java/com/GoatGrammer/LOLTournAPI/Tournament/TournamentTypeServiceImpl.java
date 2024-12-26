package com.GoatGrammer.LOLTournAPI.Tournament;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TournamentTypeServiceImpl implements TournamentTypeService {

    @Autowired
    private TournamentTypeRepository tournamentTypeRepository;

    @Override
    public List<TournamentType> getAllTournamentTypes() {
        return tournamentTypeRepository.findAll();
    }
    @Override
    public Optional<TournamentType> findByName(String name) {
        return tournamentTypeRepository.findByName(name);
    }

    @Override
    public TournamentType createTournamentType(TournamentType tournamentType) {
        return tournamentTypeRepository.save(tournamentType);
    }
}