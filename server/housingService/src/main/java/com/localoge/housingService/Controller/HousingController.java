package com.localoge.housingService.Controller;

import com.localoge.housingService.Model.Housing;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.localoge.housingService.Service.IHousingService;
import com.localoge.housingService.Exceptions.Exceptions;

import java.util.List;

@RestController // This annotation tells Spring that this class is a controller
@RequestMapping("/housings")
public class HousingController {

    @Autowired
    private IHousingService housingService;

    @Operation(summary = "Create a housing", responses = {
        @ApiResponse(responseCode = "200", description = "Successfully created housing", content = @Content(schema = @Schema(implementation = Housing.class))),
        @ApiResponse(responseCode = "404", description = "Housing not found"),
        @ApiResponse(responseCode = "409", description = "Housing already exists"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public Housing createHousing(@Parameter(description = "Housing object", required = true) @RequestBody Housing housing) throws Exception {
        return housingService.createHousing(housing);
    }

    @Operation(summary = "Delete a housing", responses = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted housing", content = @Content(schema = @Schema(implementation = void.class))),
            @ApiResponse(responseCode = "404", description = "Housing not found"),
            @ApiResponse(responseCode = "409", description = "Housing is rented"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public void deleteHousing(@PathVariable Integer id) throws Exception {
        housingService.deleteHousing(id);
    }

    @Operation(summary = "Get a housing", responses = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved housing", content = @Content(schema = @Schema(implementation = Housing.class))),
            @ApiResponse(responseCode = "404", description = "Housing not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public Housing getHousing(@PathVariable Integer id) throws Exceptions.HousingNotFoundException {
        return housingService.getHousing(id);
    }

    @Operation(summary = "Get all housings", responses = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all housings", content = @Content(schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "404", description = "No housings found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public List<Housing> getAllHousings() throws Exceptions.HousingNotFoundException {
        return housingService.getAllHousings();
    }

}
