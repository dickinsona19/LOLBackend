package com.GoatGrammer.LOLTournAPI.QuickPlay;


import com.GoatGrammer.LOLTournAPI.user.UserClass.UserDTO;

public class QuickPlayDTO {

    private Integer id;
    private UserDTO host;
    private String title;
    private Double bid;
    private UserDTO challenger;

    // Getters and Setters
    public UserDTO getChallenger() {
        return challenger;
    }

    public void setChallenger(UserDTO challenger) {
        this.challenger = challenger;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserDTO getHost() {
        return host;
    }

    public void setHost(UserDTO host) {
        this.host = host;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getBid() {
        return bid;
    }

    public void setBid(Double bid) {
        this.bid = bid;
    }
}
