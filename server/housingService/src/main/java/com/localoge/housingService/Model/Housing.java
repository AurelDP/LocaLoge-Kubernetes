package com.localoge.housingService.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
    @ApiModelProperty(notes = "The database generated housing ID", required = true)
    private Integer id;

    @NotNull // JPA annotation to specify that this property cannot be null
    @ApiModelProperty(notes = "The housing type", required = true) // Swagger annotation to describe the property
    private String type;

    @NotNull
    @ApiModelProperty(notes = "The housing's location", required = true)
    private String location;

    @NotNull
    @ApiModelProperty(notes = "The owner's ID", required = true)
    private Integer ownerId;
}

