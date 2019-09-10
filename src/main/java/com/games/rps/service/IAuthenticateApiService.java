package com.games.rps.service;

import com.games.rps.dto.JwtTokenDTO;
import com.games.rps.dto.LoginUserDTO;

public interface IAuthenticateApiService {
    JwtTokenDTO authenticate(LoginUserDTO loginUser);
}
