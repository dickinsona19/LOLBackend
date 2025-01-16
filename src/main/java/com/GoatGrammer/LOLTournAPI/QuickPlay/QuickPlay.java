package com.GoatGrammer.LOLTournAPI.QuickPlay;

import com.GoatGrammer.LOLTournAPI.user.UserClass.User;
import jakarta.persistence.*;

@Entity
@Table(name = "QUICK_PLAY")
public class QuickPlay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "host_id", nullable = false, unique = true)
    private User host;

    @Column(name = "title", nullable = false, unique = true, length = 50)
    private String title;

    @Column(name = "bid")
    private Double bid;

    @OneToOne
    @JoinColumn(name = "challenger_id", unique = true)
    private User challenger;


    // Getters and Setters


    public User getChallenger() {
        return challenger;
    }

    public void setChallenger(User challenger) {
        this.challenger = challenger;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getHost() {
        return host;
    }

    public void setHost(User host) {
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