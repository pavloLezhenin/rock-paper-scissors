package com.games.rps.service;

import com.games.rps.dto.OnePlayResultDTO;
import com.games.rps.dto.PlayItemDTO;
import com.games.rps.dto.RpcStatDTO;
import com.games.rps.dto.StartResultDTO;
import com.games.rps.entity.GameRPC;
import com.games.rps.entity.User;
import com.games.rps.jpa.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
@Transactional
public class MeApiService implements IMeApiService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Autowired
    public MeApiService(@NonNull UserRepository userRepository,
                        @NonNull ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.mapper = modelMapper;
    }

    @Override
    public RpcStatDTO stopRpc() {
        User user = getLoggedUser();
        GameRPC game = user.getGameRPC();
        RpcStatDTO stat = new RpcStatDTO();
        if (game != null) {
            stat = mapper.map(game, RpcStatDTO.class);
            game.setStarted(false);
            userRepository.save(user);
        }
        return stat;
    }

    @Override
    public OnePlayResultDTO playRpc(PlayItemDTO itemDTO) {
        User user = getLoggedUser();
        GameRPC game = user.getGameRPC();
        if (game == null || !game.isStarted()) {
            throw new RuntimeException("You should first start the game: /api/startrpc");
        }

        GameRPC.Item gamerItem = GameRPC.Item.valueOf(itemDTO.getItem().toString());
        OnePlayResultDTO result = new OnePlayResultDTO();
        result.setMyItem(new PlayItemDTO().item(PlayItemDTO.ItemEnum.valueOf(gamerItem.toString())));

        GameRPC.Item computerItem = getTheBestComputerItem(game);
        result.setComputerItem(new PlayItemDTO().item(PlayItemDTO.ItemEnum.valueOf(computerItem.toString())));
        result.setResult(GameRPC.Item.getWinner(gamerItem, computerItem));

        game.updateCounters(result);
        game.setLastItem(gamerItem);
        userRepository.save(user);
        return result;
    }

    @Override
    public StartResultDTO startRpc() {
        User user = getLoggedUser();
        GameRPC game = user.getGameRPC();
        if (game == null) {
            game = new GameRPC();
            game.setUser(user);
            user.setGameRPC(game);
        }

        game.resetLastSessionCounters();
        game.setStarted(true);
        userRepository.save(user);
        StartResultDTO startResultDTO = new StartResultDTO();
        startResultDTO.setItem(StartResultDTO.ItemEnum.STARTED);
        return startResultDTO;
    }

    /**
     * This method tries to find most probable transition from previous item made by a gamer
     * and returns item which beats this most probable one.
     * @param game
     * @return
     */
    private GameRPC.Item getTheBestComputerItem(GameRPC game) {
        GameRPC.Item computerItem;
        int numberOfItems = GameRPC.Item.values().length;
        if (game.getLastItem() != null) {
            int start = game.getLastItem().ordinal() * numberOfItems;
            int max = Integer.MIN_VALUE;
            GameRPC.Item maxItem = GameRPC.Item.ROCKET;

            //trying to find most probable next item made by a gamer based on previous results
            List<Integer> probabilities = game.getProbabilityMatrix();
            for (int i = start; i < start + numberOfItems; i++) {
                if (probabilities.get(i) > max) {
                    maxItem = GameRPC.Item.values()[i % numberOfItems];
                    max = probabilities.get(i);
                }
            }
            computerItem = maxItem.getMyWinner();
        } else {
            computerItem = getRandomItem();
        }
        return computerItem;
    }

    private String getCurrentUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }

    private User getLoggedUser() {
        return userRepository.findByUsername(getCurrentUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Could not find user"));
    }

    private GameRPC.Item getRandomItem() {
        Random r = new Random();
        int value = r.nextInt(GameRPC.Item.values().length);
        return GameRPC.Item.values()[value];
    }
}
