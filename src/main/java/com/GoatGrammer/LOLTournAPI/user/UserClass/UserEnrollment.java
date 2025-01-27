package com.GoatGrammer.LOLTournAPI.user.UserClass;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class UserEnrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "activity_id")
    private Activity activity;

    // Getters and setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }



    @Override
    public String toString() {
        return "UserEnrollment{" +
                "id=" + id +
                ", user=" + user +
                ", activity=" + activity +
                '}';
    }
}
