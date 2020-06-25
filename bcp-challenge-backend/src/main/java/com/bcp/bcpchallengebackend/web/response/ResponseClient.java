package com.bcp.bcpchallengebackend.web.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Response in error cases")
public class ResponseClient implements Serializable {

  private static final long serialVersionUID = 1870275340489232291L;

  @ApiModelProperty(required = true, value = "The error code", example = "E001")
  private String errorCode;

  @ApiModelProperty(required = true, value = "The description of the error",
      example = "Error saving the client information")
  private String description;

}
