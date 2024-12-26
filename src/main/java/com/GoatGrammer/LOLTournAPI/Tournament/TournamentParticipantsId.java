package com.GoatGrammer.LOLTournAPI.Tournament;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TournamentParticipantsId implements Serializable {

    @Column(name = "tournament_id")
    private Integer tournamentId;

    @Column(name = "user_id")
    private Integer userId;

    // Default constructor
    public TournamentParticipantsId() {}

    public TournamentParticipantsId(Integer tournamentId, Integer userId) {
        this.tournamentId = tournamentId;
        this.userId = userId;
    }

    // Getters and Setters
    public Integer getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(Integer tournamentId) {
        this.tournamentId = tournamentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    // hashCode and equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TournamentParticipantsId that = (TournamentParticipantsId) o;
        return Objects.equals(tournamentId, that.tournamentId) &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tournamentId, userId);
    }
}