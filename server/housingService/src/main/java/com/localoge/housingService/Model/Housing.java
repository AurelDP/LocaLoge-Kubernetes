package com.localoge.housingService.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity // JPA annotation to make this object ready for storage in a JPA-based data store
@Data   // Lombok's annotation to generate getters and setters, toString, equals, and hashCode
@Table(name = "housings") // JPA annotation to specify the name of the table in the database
@Getter @Setter
public class Housing {

    @Id // JPA annotation to specify the primary key for this object (id)
    @GeneratedValue // JPA annotation to specify that the id is auto-generated
    @JsonProperty(access = JsonProperty.Access.READ_ONLY) // Jackson annotation to specify that this property is read-only
    @Schema(description = "The database generated housing ID")
    private Integer id;

    @NotNull // JPA annotation to specify that this property cannot be null
    @Schema(description = "The housing type") // Swagger annotation to describe the property
    private String type;

    @NotNull
    @Schema(description = "The housing's location")
    private String location;

    @NotNull
    @Schema(description = "The owner's ID")
    private Integer ownerId;
}

