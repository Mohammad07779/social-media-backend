package com.Mohammad.CareerXpert.Services;

import com.Mohammad.CareerXpert.DTOS.ProfileDTO;

public interface ProfileService {

    public ProfileDTO updateUserDetails(ProfileDTO dto, String userName);

    public ProfileDTO getProfile(String username);
}
