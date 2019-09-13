package com.games.rps.entity;

import com.games.rps.dto.OnePlayResultDTO;
import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.games.rps.dto.OnePlayResultDTO.ResultEnum.*;

@Entity
@Table(name = "game_rpc")
@DynamicUpdate
public class GameRPC {
    @Id
    @GeneratedValue(generator = "UUID")
    UUID id;

    @Column
    boolean started;
    @Column
    int totalWins;
    @Column
    int totalDraws;
    @Column
    int totalLost;
    @Column
    int totalGames;
    @Column
    int lastSessionWins;
    @Column
    int lastSessionDraws;
    @Column
    int lastSessionLost;
    @Column
    int lastSessionGames;
    @Column
    Item lastItem;

    /**
     * This list of integers is actually a 2 dimensional array and looks like this:
     *              Rocket    Paper    Scissors
     *      Rocket     0        0         0
     *      Paper      0        0         0
     *      Scissors   0        0         0
     * This matrix will store number of transitions from one item to other items made by gamer.
     * As human are not good at generating random number we will try to use this matrix to forsee their next item.
     */
    @Column
    String probabilityMatrix = "0,0,0,0,0,0,0,0,0";

    @OneToOne(mappedBy = "gameRPC")
    private User user;

    @Transient
    private List<Integer> matrix = new ArrayList<>();

    public List<Integer> getProbabilityMatrix() {
        if (CollectionUtils.isEmpty(matrix)) {
            matrix = Arrays.stream(probabilityMatrix.split(","))
                    .map(Integer::valueOf)
                    .collect(Collectors.toList());
        }
        return matrix;
    }

    public void updateCounters(OnePlayResultDTO result) {
        this.totalGames++;
        this.lastSessionGames++;
        OnePlayResultDTO.ResultEnum resultEnum = result.getResult();
        if (resultEnum.equals(DRAW)) {
            this.totalDraws++;
            this.lastSessionDraws++;
        } else if (resultEnum.equals(COMPUTER_WON)) {
            this.totalLost++;
            this.lastSessionLost++;
        } else {
            this.totalWins++;
            this.lastSessionWins++;
        }
        if (CollectionUtils.isNotEmpty(matrix)) {
            int index = 3 * this.lastItem.ordinal() + result.getMyItem().getItem().ordinal();
            matrix.set(index, matrix.get(index) + 1);
            this.probabilityMatrix = matrix.stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(","));
        }
    }

    static private OnePlayResultDTO.ResultEnum[][] results =
                    {{DRAW, COMPUTER_WON, I_WON},
                    {I_WON, DRAW, COMPUTER_WON},
                    {COMPUTER_WON, I_WON, DRAW}};

    public enum Item {
        ROCKET {
            public Item getMyWinner() {
                return PAPER;
            }
        },
        PAPER {
            public Item getMyWinner() {
                return SCISSORS;
            }
        },
        SCISSORS {
            public Item getMyWinner() {
                return ROCKET;
            }
        };

        public static OnePlayResultDTO.ResultEnum getWinner(Item gamerItem, Item computerItem) {
            return results[gamerItem.ordinal()][computerItem.ordinal()];
        }

        public abstract Item getMyWinner();
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public int getTotalWins() {
        return totalWins;
    }

    public void setTotalWins(int totalWins) {
        this.totalWins = totalWins;
    }

    public int getTotalGames() {
        return totalGames;
    }

    public void setTotalGames(int totalGames) {
        this.totalGames = totalGames;
    }

    public int getLastSessionWins() {
        return lastSessionWins;
    }

    public void setLastSessionWins(int lastSessionWins) {
        this.lastSessionWins = lastSessionWins;
    }

    public int getLastSessionGames() {
        return lastSessionGames;
    }

    public void setLastSessionGames(int lastSessionGames) {
        this.lastSessionGames = lastSessionGames;
    }

    public Item getLastItem() {
        return lastItem;
    }

    public void setLastItem(Item lastItem) {
        this.lastItem = lastItem;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setLastSessionDraws(int lastSessionDraws) {
        this.lastSessionDraws = lastSessionDraws;
    }

    public void setLastSessionLost(int lastSessionLost) {
        this.lastSessionLost = lastSessionLost;
    }

    public int getTotalDraws() {
        return totalDraws;
    }

    public int getTotalLost() {
        return totalLost;
    }

    public int getLastSessionDraws() {
        return lastSessionDraws;
    }

    public int getLastSessionLost() {
        return lastSessionLost;
    }

    public void resetLastSessionCounters() {
        this.setLastSessionGames(0);
        this.setLastSessionWins(0);
        this.setLastSessionDraws(0);
        this.setLastSessionLost(0);
    }
}
