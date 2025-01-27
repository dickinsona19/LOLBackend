package com.GoatGrammer.LOLTournAPI.user.UserClass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enrollment")
public class UserEnrollmentController {

    @Autowired
    private UserEnrollmentService userEnrollmentService;

    @PostMapping("/enroll")
    public void enrollUser(@RequestParam Integer userId, @RequestParam String activityType, @RequestParam Integer activityId) {
        userEnrollmentService.enrollUserInActivity(userId, activityType, activityId);
    }
}