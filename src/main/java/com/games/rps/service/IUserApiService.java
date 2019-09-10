package com.games.rps.service;

import com.games.rps.dto.UserDTO;

public interface IUserApiService {
    UserDTO getUserById(Integer id);
}
