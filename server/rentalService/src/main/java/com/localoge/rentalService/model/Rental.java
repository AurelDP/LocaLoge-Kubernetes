package com.localoge.rentalService.model;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Entity // JPA annotation to make this object ready for storage in a JPA-based data store
@Data   // Lombok's annotation to generate getters and setters, toString, equals, and hashCode
@Table(name = "rentals") // JPA annotation to specify the name of the table in the database
public class Rental {

    @Id // JPA annotation to specify the primary key for this object (id)
    @GeneratedValue // JPA annotation to specify that the id is auto-generated
    @JsonProperty(access = JsonProperty.Access.READ_ONLY) // Jackson annotation to specify that this property is read-only
    @Schema(description = "The database generated rental ID")
    private Integer id;

    @NotNull // JPA annotation to specify that this property cannot be null
    @Schema(description = "The property id") // Swagger annotation to describe the property
    private Integer propertyId;

    @NotNull
    @Schema(description = "The duration in days")
    private Integer durationInDays;

    @NotNull
    @Schema(description = "The daily price", example = "100.0")
    private Double dailyPrice;

    @NotNull
    @Schema(description = "The description of the property and some other information")
    private String description;
}
