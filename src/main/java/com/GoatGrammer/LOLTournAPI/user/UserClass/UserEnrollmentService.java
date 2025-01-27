package com.GoatGrammer.LOLTournAPI.user.UserClass;

import com.GoatGrammer.LOLTournAPI.QuickPlay.QuickPlay;
import com.GoatGrammer.LOLTournAPI.QuickPlay.QuickPlayRepository;
import com.GoatGrammer.LOLTournAPI.Tournament.Tournament;
import com.GoatGrammer.LOLTournAPI.Tournament.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserEnrollmentService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private UserEnrollmentRepository userEnrollmentRepository;

    @Autowired
    private QuickPlayRepository quickPlayRepository;

    @Autowired
    private TournamentRepository tournamentRepository;

    public void enrollUserInActivity(Integer userId, String activityType, Integer activityId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Activity> activity = activityRepository.findById(activityId);

        if (user.isPresent() && activity.isPresent()) {
            UserEnrollment enrollment = new UserEnrollment();
            enrollment.setUser(user.get());
            enrollment.setActivity(activity.get());
            // Add logic to set start and end dates
            userEnrollmentRepository.save(enrollment);

        }
    }
}
