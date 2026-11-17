package com.anik.my_portfolio_backend.service;

import com.anik.my_portfolio_backend.dto.request.CreateProfileRequestDto;
import com.anik.my_portfolio_backend.dto.request.UpdateProfileRequestDto;
import com.anik.my_portfolio_backend.dto.response.ProfileResponseDto;
import com.anik.my_portfolio_backend.entity.Profile;
import com.anik.my_portfolio_backend.repository.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository){
        this.profileRepository = profileRepository;
    }

    //Create Profile
    public ProfileResponseDto createProfile(CreateProfileRequestDto requestDto){
        Profile profile = new Profile();

        profile.setName(requestDto.getName());
        profile.setProfessionalTitle(requestDto.getProfessionalTitle());
        profile.setAboutMe(requestDto.getAboutMe());
        profile.setEmail(requestDto.getEmail());
        profile.setGithubUrl(requestDto.getGithubUrl());
        profile.setLinkedInUrl(requestDto.getLinkedInUrl());
        profile.setLocation(requestDto.getLocation());

        Profile savedProfile = profileRepository.save(profile);

        return mapToResponseDto(savedProfile);
    }

    //Get all profiles
    public List<ProfileResponseDto> getAllProfiles(){
        return profileRepository.findAll()
                .stream()
                .map(this::mapToResponseDto)
                .toList();
    }

    //Get Profile By ID
    public Optional<ProfileResponseDto> getProfileById(Long id){
        return profileRepository.findById(id)
                .map(this::mapToResponseDto);
    }


    // Update Profile
    public Optional<ProfileResponseDto> updateProfile(Long id, UpdateProfileRequestDto requestDto) {

        Optional<Profile> existingProfile = profileRepository.findById(id);

        if (existingProfile.isPresent()) {

            Profile profile = existingProfile.get();

            profile.setName(requestDto.getName());
            profile.setProfessionalTitle(requestDto.getProfessionalTitle());
            profile.setAboutMe(requestDto.getAboutMe());
            profile.setEmail(requestDto.getEmail());
            profile.setGithubUrl(requestDto.getGithubUrl());
            profile.setLinkedInUrl(requestDto.getLinkedInUrl());
            profile.setLocation(requestDto.getLocation());

            Profile updatedProfile = profileRepository.save(profile);

            return Optional.of(mapToResponseDto(updatedProfile));
        }

        return Optional.empty();
    }


    // Delete Profile
    public boolean deleteProfile(Long id) {

        if (profileRepository.existsById(id)) {

            profileRepository.deleteById(id);

            return true;
        }

        return false;
    }

    //Entity to Response Dto Mapping
    private ProfileResponseDto mapToResponseDto(Profile profile){
        ProfileResponseDto responseDto = new ProfileResponseDto();

        responseDto.setId(profile.getId());
        responseDto.setName(profile.getName());
        responseDto.setProfessionalTitle(profile.getProfessionalTitle());
        responseDto.setAboutMe(profile.getAboutMe());
        responseDto.setEmail(profile.getEmail());
        responseDto.setGithubUrl(profile.getGithubUrl());
        responseDto.setLinkedInUrl(profile.getLinkedInUrl());
        responseDto.setLocation(profile.getLocation());

        return responseDto;
    }
}
