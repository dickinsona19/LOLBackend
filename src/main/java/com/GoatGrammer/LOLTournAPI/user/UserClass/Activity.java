package com.GoatGrammer.LOLTournAPI.user.UserClass;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Inheritance strategy
@DiscriminatorColumn(name = "activity_type", discriminatorType = DiscriminatorType.STRING) // Discriminator for identifying the type
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(name = "activity_type", insertable = false, updatable = false)
    private String activityType; // 'quick_play', 'tournament', etc.

    // Getters and setters
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

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }
}
