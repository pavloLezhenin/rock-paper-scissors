package com.games.rps.service;

import com.games.rps.dto.OnePlayResultDTO;
import com.games.rps.dto.PlayItemDTO;
import com.games.rps.dto.RpcStatDTO;
import com.games.rps.dto.StartResultDTO;

public interface IMeApiService {
    OnePlayResultDTO playRpc(PlayItemDTO item);
    RpcStatDTO stopRpc();
    StartResultDTO startRpc();
}
