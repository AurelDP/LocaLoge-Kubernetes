package com.localoge.rentalService.model;

import javax.persistence.*;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Entity // JPA annotation to make this object ready for storage in a JPA-based data store
@Data   // Lombok's annotation to generate getters and setters, toString, equals, and hashCode
@Table(name = "rentals") // JPA annotation to specify the name of the table in the database
public class Rental {

    @Id // JPA annotation to specify the primary key for this object (id)
    @GeneratedValue // JPA annotation to specify that the id is auto-generated
    @JsonProperty(access = JsonProperty.Access.READ_ONLY) // Jackson annotation to specify that this property is read-only
    @ApiModelProperty(notes = "The database generated rental ID", required = true)
    private Integer id;

    @NotNull // JPA annotation to specify that this property cannot be null
    @ApiModelProperty(notes = "The property id", required = true) // Swagger annotation to describe the property
    private Integer propertyId;

    @NotNull
    @ApiModelProperty(notes = "The duration in days", required = true)
    private Integer durationInDays;

    @NotNull
    @ApiModelProperty(notes = "The daily price", required = true, example = "100.0")
    private Double dailyPrice;

    @NotNull
    @ApiModelProperty(notes = "The description of the property and some other information", required = true)
    private String description;
}
