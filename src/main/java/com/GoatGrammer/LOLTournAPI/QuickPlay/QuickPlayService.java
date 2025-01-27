package com.GoatGrammer.LOLTournAPI.QuickPlay;

import com.GoatGrammer.LOLTournAPI.user.UserClass.User;
import com.GoatGrammer.LOLTournAPI.user.UserClass.UserDTO;
import com.GoatGrammer.LOLTournAPI.user.UserClass.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuickPlayService {

    private final QuickPlayRepository quickPlayRepository;

    @Autowired
    private UserRepository userRepository;

    public QuickPlayService(QuickPlayRepository quickPlayRepository) {
        this.quickPlayRepository = quickPlayRepository;
    }

    public List<QuickPlayDTO> findAll() {
        return quickPlayRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<QuickPlay> findById(Integer id) {
        return quickPlayRepository.findById(id);
    }

    public QuickPlay save(QuickPlay quickPlay) {
        return quickPlayRepository.save(quickPlay);
    }

    public void deleteById(Integer id) {
        quickPlayRepository.deleteById(id);
    }

    private QuickPlayDTO convertToDTO(QuickPlay quickPlay) {
        QuickPlayDTO dto = new QuickPlayDTO();
        dto.setId(quickPlay.getId());
        dto.setTitle(quickPlay.getTitle());
        dto.setBid(quickPlay.getBid());

        // Convert Host User to UserDTO
        UserDTO userDTO = UserDTO.convertToDTO(quickPlay.getHost());
        userDTO.setId(quickPlay.getHost().getId());
        userDTO.setUsername(quickPlay.getHost().getUsername());
        dto.setHost(userDTO);


        // Convert Challenger User to UserDTO if it exists
        if (quickPlay.getChallenger() != null) {
            UserDTO challengerDTO = UserDTO.convertToDTO(quickPlay.getChallenger());
            challengerDTO.setId(quickPlay.getChallenger().getId());
            challengerDTO.setUsername(quickPlay.getChallenger().getUsername());
            dto.setChallenger(challengerDTO);
        } else {
            dto.setChallenger(null);
        }
        return dto;
    }

    public QuickPlay addChallenger(Integer quickPlayId, Integer challengerId) {
        // Fetch the QuickPlay instance
        Optional<QuickPlay> quickPlayOptional = quickPlayRepository.findById(quickPlayId);
        if (!quickPlayOptional.isPresent()) {
            throw new RuntimeException("QuickPlay not found for ID: " + quickPlayId);
        }

        // Fetch the User instance for the challenger
        Optional<User> challengerOptional = userRepository.findById(challengerId);
        if (!challengerOptional.isPresent()) {
            throw new RuntimeException("User not found for ID: " + challengerId);
        }

        // Set the challenger and save the QuickPlay instance
        QuickPlay quickPlay = quickPlayOptional.get();
        quickPlay.setChallenger(challengerOptional.get());
        return quickPlayRepository.save(quickPlay);
    }

}
