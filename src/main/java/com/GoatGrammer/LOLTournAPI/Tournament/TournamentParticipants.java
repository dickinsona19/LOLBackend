package com.GoatGrammer.LOLTournAPI.Tournament;

import com.GoatGrammer.LOLTournAPI.user.UserClass.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TOURNAMENT_PARTICIPANTS")
public class TournamentParticipants implements Serializable {

    @EmbeddedId
    private TournamentParticipantsId id;

    @ManyToOne
    @MapsId("tournamentId")
    @JoinColumn(name = "tournament_id")
    @JsonBackReference
    private Tournament tournament;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    // Default constructor
    public TournamentParticipants() {}

    public TournamentParticipants(Tournament tournament, User user) {
        this.id = new TournamentParticipantsId(tournament.getId(), user.getId());
        this.tournament = tournament;
        this.user = user;
    }

    // Getters and Setters
    public TournamentParticipantsId getId() {
        return id;
    }

    public void setId(TournamentParticipantsId id) {
        this.id = id;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public com.GoatGrammer.LOLTournAPI.user.UserClass.User getUser() {
        return user;
    }

    public void setUser(com.GoatGrammer.LOLTournAPI.user.UserClass.User user) {
        this.user = user;
    }
}
