package com.localoge.reservationService.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ApiModelProperty(notes = "The database generated reservation ID", required = true)
    private Integer id;

    @NotNull
    @ApiModelProperty(notes = "The id of the rental that is being reserved", required = true)
    private Integer rentalId;

    @NotNull
    @ApiModelProperty(notes = "The name of the person who made the reservation", required = true)
    private String name;

    @NotNull
    @ApiModelProperty(notes="The email of the person who made the reservation", required = true)
    private String email;

    @NotNull
    @ApiModelProperty(notes="The phone number of the person who made the reservation", required = true)
    private String phone;

}
