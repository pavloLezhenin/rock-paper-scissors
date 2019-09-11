package com.games.rps.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.games.rps.dto.PlayItemDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * OnePlayResultDTO
 */
@Validated
public class OnePlayResultDTO   {
  @JsonProperty("myItem")
  private PlayItemDTO myItem = null;

  @JsonProperty("computerItem")
  private PlayItemDTO computerItem = null;

  /**
   * Gets or Sets result
   */
  public enum ResultEnum {
    I_WON("I_WON"),
    
    COMPUTER_WON("COMPUTER_WON"),
    
    DRAW("DRAW");

    private String value;

    ResultEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static ResultEnum fromValue(String text) {
      for (ResultEnum b : ResultEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("result")
  private ResultEnum result = null;

  public OnePlayResultDTO myItem(PlayItemDTO myItem) {
    this.myItem = myItem;
    return this;
  }

  /**
   * Get myItem
   * @return myItem
  **/
  @ApiModelProperty(value = "")

  @Valid

  public PlayItemDTO getMyItem() {
    return myItem;
  }

  public void setMyItem(PlayItemDTO myItem) {
    this.myItem = myItem;
  }

  public OnePlayResultDTO computerItem(PlayItemDTO computerItem) {
    this.computerItem = computerItem;
    return this;
  }

  /**
   * Get computerItem
   * @return computerItem
  **/
  @ApiModelProperty(value = "")

  @Valid

  public PlayItemDTO getComputerItem() {
    return computerItem;
  }

  public void setComputerItem(PlayItemDTO computerItem) {
    this.computerItem = computerItem;
  }

  public OnePlayResultDTO result(ResultEnum result) {
    this.result = result;
    return this;
  }

  /**
   * Get result
   * @return result
  **/
  @ApiModelProperty(value = "")


  public ResultEnum getResult() {
    return result;
  }

  public void setResult(ResultEnum result) {
    this.result = result;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OnePlayResultDTO onePlayResult = (OnePlayResultDTO) o;
    return Objects.equals(this.myItem, onePlayResult.myItem) &&
        Objects.equals(this.computerItem, onePlayResult.computerItem) &&
        Objects.equals(this.result, onePlayResult.result);
  }

  @Override
  public int hashCode() {
    return Objects.hash(myItem, computerItem, result);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OnePlayResultDTO {\n");
    
    sb.append("    myItem: ").append(toIndentedString(myItem)).append("\n");
    sb.append("    computerItem: ").append(toIndentedString(computerItem)).append("\n");
    sb.append("    result: ").append(toIndentedString(result)).append("\n");
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

