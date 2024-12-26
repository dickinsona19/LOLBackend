package com.GoatGrammer.LOLTournAPI.QuickPlay;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuickPlayRepository extends JpaRepository<QuickPlay, Integer> {
}
