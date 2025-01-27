package com.GoatGrammer.LOLTournAPI.user.UserClass;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Integer> {
    Activity findByName(String name);
}