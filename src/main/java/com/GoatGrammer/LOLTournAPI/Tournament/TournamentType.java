package com.GoatGrammer.LOLTournAPI.Tournament;

import jakarta.persistence.*;

@Entity
@Table(name = "TOURNAMENT_TYPE")
public class TournamentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    public TournamentType() {}

    public TournamentType(String name) {
        this.name = name;
    }

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
}