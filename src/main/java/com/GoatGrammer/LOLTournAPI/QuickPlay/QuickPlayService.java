package com.GoatGrammer.LOLTournAPI.QuickPlay;

import com.GoatGrammer.LOLTournAPI.user.UserClass.User;
import com.GoatGrammer.LOLTournAPI.user.UserClass.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuickPlayService {

    private final QuickPlayRepository quickPlayRepository;

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
}
