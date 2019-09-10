package com.games.rps.controller;

import com.games.rps.dto.UserDTO;
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

import com.games.rps.service.IUserApiService;
@RestController
public class UserApiController implements UserApi {
    private static final Logger log = LoggerFactory.getLogger(UserApiController.class);
    private final IUserApiService service;

    @Autowired
    public UserApiController(IUserApiService service) {
        this.service = service;
    }
    public UserDTO getUserById(@ApiParam(value = "retrieves user by user id",required=true) @PathVariable("id") Integer id) {
        return service.getUserById(id);
    }
}
