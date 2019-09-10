package com.games.rps.controller;

import com.games.rps.dto.JwtTokenDTO;
import com.games.rps.dto.LoginUserDTO;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.games.rps.service.IAuthenticateApiService;
@RestController
public class AuthenticateApiController implements AuthenticateApi {
    private static final Logger log = LoggerFactory.getLogger(AuthenticateApiController.class);
    private final IAuthenticateApiService service;

    @Autowired
    public AuthenticateApiController(IAuthenticateApiService service) {
        this.service = service;
    }
    public JwtTokenDTO authenticate(@ApiParam(value = "The user to create."  )  @Valid @RequestBody LoginUserDTO loginUser) {
        return service.authenticate(loginUser);
    }
}
