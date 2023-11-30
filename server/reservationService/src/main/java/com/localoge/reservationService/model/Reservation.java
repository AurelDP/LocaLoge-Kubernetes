package com.localoge.reservationService.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Data
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(description = "The database generated reservation ID")
    private Integer id;

    @NotNull
    @Schema(description = "The id of the rental that is being reserved")
    private Integer rentalId;

    @NotNull
    @Schema(description = "The name of the person who made the reservation")
    private String name;

    @NotNull
    @Schema(description="The email of the person who made the reservation")
    private String email;

    @NotNull
    @Schema(description="The phone number of the person who made the reservation")
    private String phone;

}
