package com.GoatGrammer.LOLTournAPI.QuickPlay;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quickplay")
public class QuickPlayController {

    private final QuickPlayService quickPlayService;

    public QuickPlayController(QuickPlayService quickPlayService) {
        this.quickPlayService = quickPlayService;
    }

    @GetMapping
    public List<QuickPlayDTO> getAllQuickPlays() {
        return quickPlayService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuickPlay> getQuickPlayById(@PathVariable Integer id) {
        return quickPlayService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public QuickPlay createQuickPlay(@RequestBody QuickPlay quickPlay) {
        return quickPlayService.save(quickPlay);
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuickPlay> updateQuickPlay(@PathVariable Integer id, @RequestBody QuickPlay quickPlayDetails) {
        return quickPlayService.findById(id).map(existingQuickPlay -> {
            existingQuickPlay.setHost(quickPlayDetails.getHost());
            existingQuickPlay.setTitle(quickPlayDetails.getTitle());
            existingQuickPlay.setBid(quickPlayDetails.getBid());
            quickPlayService.save(existingQuickPlay);
            return ResponseEntity.ok(existingQuickPlay);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuickPlay(@PathVariable Integer id) {
        if (quickPlayService.findById(id).isPresent()) {
            quickPlayService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
