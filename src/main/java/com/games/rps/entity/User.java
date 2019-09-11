package com.games.rps.entity;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
@DynamicUpdate
public class User {

    @Id
    @NotNull
    @Column(unique = true)
    private String username;

    @NotNull
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean enabled = true;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "gamerpc_id", referencedColumnName = "id")
    private GameRPC gameRPC;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public GameRPC getGameRPC() {
        return gameRPC;
    }

    public void setGameRPC(GameRPC gameRPC) {
        this.gameRPC = gameRPC;
    }
}
