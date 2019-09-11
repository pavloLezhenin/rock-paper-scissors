package com.games.rps.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * RpcStatDTO
 */
@Validated
public class RpcStatDTO   {
  @JsonProperty("totalGames")
  private Integer totalGames = null;

  @JsonProperty("totalWins")
  private Integer totalWins = null;

  @JsonProperty("totalLost")
  private Integer totalLost = null;

  @JsonProperty("totalDraws")
  private Integer totalDraws = null;

  @JsonProperty("lastSessionGames")
  private Integer lastSessionGames = null;

  @JsonProperty("lastSessionWins")
  private Integer lastSessionWins = null;

  @JsonProperty("lastSessionLost")
  private Integer lastSessionLost = null;

  @JsonProperty("lastSessionDraws")
  private Integer lastSessionDraws = null;

  public RpcStatDTO totalGames(Integer totalGames) {
    this.totalGames = totalGames;
    return this;
  }

  /**
   * Get totalGames
   * @return totalGames
  **/
  @ApiModelProperty(value = "")


  public Integer getTotalGames() {
    return totalGames;
  }

  public void setTotalGames(Integer totalGames) {
    this.totalGames = totalGames;
  }

  public RpcStatDTO totalWins(Integer totalWins) {
    this.totalWins = totalWins;
    return this;
  }

  /**
   * Get totalWins
   * @return totalWins
  **/
  @ApiModelProperty(value = "")


  public Integer getTotalWins() {
    return totalWins;
  }

  public void setTotalWins(Integer totalWins) {
    this.totalWins = totalWins;
  }

  public RpcStatDTO totalLost(Integer totalLost) {
    this.totalLost = totalLost;
    return this;
  }

  /**
   * Get totalLost
   * @return totalLost
  **/
  @ApiModelProperty(value = "")


  public Integer getTotalLost() {
    return totalLost;
  }

  public void setTotalLost(Integer totalLost) {
    this.totalLost = totalLost;
  }

  public RpcStatDTO totalDraws(Integer totalDraws) {
    this.totalDraws = totalDraws;
    return this;
  }

  /**
   * Get totalDraws
   * @return totalDraws
  **/
  @ApiModelProperty(value = "")


  public Integer getTotalDraws() {
    return totalDraws;
  }

  public void setTotalDraws(Integer totalDraws) {
    this.totalDraws = totalDraws;
  }

  public RpcStatDTO lastSessionGames(Integer lastSessionGames) {
    this.lastSessionGames = lastSessionGames;
    return this;
  }

  /**
   * Get lastSessionGames
   * @return lastSessionGames
  **/
  @ApiModelProperty(value = "")


  public Integer getLastSessionGames() {
    return lastSessionGames;
  }

  public void setLastSessionGames(Integer lastSessionGames) {
    this.lastSessionGames = lastSessionGames;
  }

  public RpcStatDTO lastSessionWins(Integer lastSessionWins) {
    this.lastSessionWins = lastSessionWins;
    return this;
  }

  /**
   * Get lastSessionWins
   * @return lastSessionWins
  **/
  @ApiModelProperty(value = "")


  public Integer getLastSessionWins() {
    return lastSessionWins;
  }

  public void setLastSessionWins(Integer lastSessionWins) {
    this.lastSessionWins = lastSessionWins;
  }

  public RpcStatDTO lastSessionLost(Integer lastSessionLost) {
    this.lastSessionLost = lastSessionLost;
    return this;
  }

  /**
   * Get lastSessionLost
   * @return lastSessionLost
  **/
  @ApiModelProperty(value = "")


  public Integer getLastSessionLost() {
    return lastSessionLost;
  }

  public void setLastSessionLost(Integer lastSessionLost) {
    this.lastSessionLost = lastSessionLost;
  }

  public RpcStatDTO lastSessionDraws(Integer lastSessionDraws) {
    this.lastSessionDraws = lastSessionDraws;
    return this;
  }

  /**
   * Get lastSessionDraws
   * @return lastSessionDraws
  **/
  @ApiModelProperty(value = "")


  public Integer getLastSessionDraws() {
    return lastSessionDraws;
  }

  public void setLastSessionDraws(Integer lastSessionDraws) {
    this.lastSessionDraws = lastSessionDraws;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RpcStatDTO rpcStat = (RpcStatDTO) o;
    return Objects.equals(this.totalGames, rpcStat.totalGames) &&
        Objects.equals(this.totalWins, rpcStat.totalWins) &&
        Objects.equals(this.totalLost, rpcStat.totalLost) &&
        Objects.equals(this.totalDraws, rpcStat.totalDraws) &&
        Objects.equals(this.lastSessionGames, rpcStat.lastSessionGames) &&
        Objects.equals(this.lastSessionWins, rpcStat.lastSessionWins) &&
        Objects.equals(this.lastSessionLost, rpcStat.lastSessionLost) &&
        Objects.equals(this.lastSessionDraws, rpcStat.lastSessionDraws);
  }

  @Override
  public int hashCode() {
    return Objects.hash(totalGames, totalWins, totalLost, totalDraws, lastSessionGames, lastSessionWins, lastSessionLost, lastSessionDraws);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RpcStatDTO {\n");
    
    sb.append("    totalGames: ").append(toIndentedString(totalGames)).append("\n");
    sb.append("    totalWins: ").append(toIndentedString(totalWins)).append("\n");
    sb.append("    totalLost: ").append(toIndentedString(totalLost)).append("\n");
    sb.append("    totalDraws: ").append(toIndentedString(totalDraws)).append("\n");
    sb.append("    lastSessionGames: ").append(toIndentedString(lastSessionGames)).append("\n");
    sb.append("    lastSessionWins: ").append(toIndentedString(lastSessionWins)).append("\n");
    sb.append("    lastSessionLost: ").append(toIndentedString(lastSessionLost)).append("\n");
    sb.append("    lastSessionDraws: ").append(toIndentedString(lastSessionDraws)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

