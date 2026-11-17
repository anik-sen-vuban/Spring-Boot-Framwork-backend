package com.anik.my_portfolio_backend.controller;

import com.anik.my_portfolio_backend.dto.request.CreateProfileRequestDto;
import com.anik.my_portfolio_backend.dto.request.UpdateProfileRequestDto;
import com.anik.my_portfolio_backend.dto.response.ProfileResponseDto;
import com.anik.my_portfolio_backend.entity.Profile;
import com.anik.my_portfolio_backend.service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService){
        this.profileService = profileService;
    }

    // Get All Profiles
    @GetMapping
    public ResponseEntity<List<ProfileResponseDto>> getAllProfiles() {
        List<ProfileResponseDto> profiles = profileService.getAllProfiles();

        return ResponseEntity.ok(profiles);
    }

    // Get Profile By ID
    @GetMapping("/{id}")
    public ResponseEntity<ProfileResponseDto> getProfileById(@PathVariable Long id) {

        return profileService.getProfileById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create Profile
    @PostMapping
    public ResponseEntity<ProfileResponseDto> createProfile(
            @Valid @RequestBody CreateProfileRequestDto requestDto) {

        ProfileResponseDto savedProfile = profileService.createProfile(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedProfile);
    }


    // Update Profile
    @PutMapping("/{id}")
    public ResponseEntity<ProfileResponseDto> updateProfile(
            @PathVariable Long id,
            @Valid @RequestBody UpdateProfileRequestDto requestDto) {

        return profileService.updateProfile(id, requestDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    // Delete Profile
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {

        boolean deleted = profileService.deleteProfile(id);

        if (deleted) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
