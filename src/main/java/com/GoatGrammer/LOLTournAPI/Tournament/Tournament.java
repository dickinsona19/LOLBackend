package com.GoatGrammer.LOLTournAPI.Tournament;

import com.GoatGrammer.LOLTournAPI.user.UserClass.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "TOURNAMENT")
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "tournament_type_id", nullable = false)
    private TournamentType tournamentType;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "description")
    private String description;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "max_participants", nullable = false)
    private int maxParticipants;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @ManyToOne
    @JoinColumn(name = "host_id", nullable = false)
    User host;

    @OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<TournamentParticipants> participants;

    public Tournament() {}

    public Tournament(String name, TournamentType tournamentType, Date startDate, String description, Date endDate, int maxParticipants, Game game) {
        this.name = name;
        this.tournamentType = tournamentType;
        this.startDate = startDate;
        this.description = description;
        this.endDate = endDate;
        this.maxParticipants = maxParticipants;
        this.game = game;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public TournamentType getTournamentType() {
        return tournamentType;
    }

    public void setTournamentType(TournamentType tournamentType) {
        this.tournamentType = tournamentType;
    }
    public com.GoatGrammer.LOLTournAPI.user.UserClass.User getHost(){
        return host;
    }
    public void setHost(com.GoatGrammer.LOLTournAPI.user.UserClass.User host){
        this.host = host;
    }
    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public List<TournamentParticipants> getParticipants() {
        return participants;
    }

    public void setParticipants(List<TournamentParticipants> participants) {
        this.participants = participants;
    }
}