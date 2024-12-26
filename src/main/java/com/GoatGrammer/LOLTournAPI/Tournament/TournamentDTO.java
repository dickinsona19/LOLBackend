package com.GoatGrammer.LOLTournAPI.Tournament;


import java.util.Date;

public class TournamentDTO {
    private String gameName;
    private String tournamentTypeName;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private int maxParticipants;
    private Integer hostId;



    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getTournamentTypeName() {
        return tournamentTypeName;
    }

    public void setTournamentTypeName(String tournamentTypeName) {
        this.tournamentTypeName = tournamentTypeName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public Integer getHostId() {
        return hostId;
    }

    public void setHostId(Integer hostId) {
        this.hostId = hostId;
    }
}
