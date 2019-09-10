package com.games.rps.service;

import com.games.rps.dto.UserDTO;
import com.games.rps.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserApiService implements IUserApiService {
    @Override
    public UserDTO getUserById(Integer id) {
        return new UserDTO();
    }
}
