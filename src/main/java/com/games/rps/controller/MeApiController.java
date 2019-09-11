package com.games.rps.controller;

import com.games.rps.dto.OnePlayResultDTO;
import com.games.rps.dto.PlayItemDTO;
import com.games.rps.dto.RpcStatDTO;
import com.games.rps.dto.StartResultDTO;
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

import com.games.rps.service.IMeApiService;
@RestController
public class MeApiController implements MeApi {
    private static final Logger log = LoggerFactory.getLogger(MeApiController.class);
    private final IMeApiService service;

    @Autowired
    public MeApiController(IMeApiService service) {
        this.service = service;
    }
    public OnePlayResultDTO playRpc(@ApiParam(value = "The one from Rocket, Paper or Scissors."  )  @Valid @RequestBody PlayItemDTO item) {
        return service.playRpc(item);
    }
    public StartResultDTO startRpc() {
        return service.startRpc();
    }
    public RpcStatDTO stopRpc() {
        return service.stopRpc();
    }
}
