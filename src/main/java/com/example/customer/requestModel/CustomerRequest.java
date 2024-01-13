package com.example.customer.requestModel;

import com.example.customer.enumeration.CustomerOccupation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * This is CustomerRequest class
 * @author github - manjunathreddy1999
 * created date - 13/01/2024
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "This class representing the request model for customer details")
public class CustomerRequest {

    @ApiModelProperty(notes = "Customer Name", required = true)
    @NotNull
    private String name;

    @ApiModelProperty(notes = "Customer Email Address", required = true)
    private String email;

    @ApiModelProperty(notes = "Customer Date of Birth", required = true)
    private String dob;

    @ApiModelProperty(notes = "Customer's Occupation", required = true)
    @Enumerated(EnumType.STRING)
    private CustomerOccupation occupation;
}
