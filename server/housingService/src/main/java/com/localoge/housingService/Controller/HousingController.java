package com.localoge.housingService.Controller;

import com.localoge.housingService.Model.Housing;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation(value = "Create a housing", response = Housing.class) // Swagger annotation to document this method
    @ApiParam(name = "housing", value = "Housing object", required = true) // Swagger annotation to document this method
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully created housing"),
        @ApiResponse(code = 404, message = "Housing not found"),
        @ApiResponse(code = 409, message = "Housing already exists"),
        @ApiResponse(code = 500, message = "Internal server error")
    }) // Swagger annotation to document the possible responses
    @PostMapping("/create")
    public Housing createHousing(@RequestBody Housing housing) throws Exception {
        return housingService.createHousing(housing);
    }

    @ApiOperation(value = "Delete a housing", response = void.class)
    @ApiParam(name = "id", value = "Housing id", required = true)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully deleted housing"),
        @ApiResponse(code = 404, message = "Housing not found"),
        @ApiResponse(code = 409, message = "Housing is rented"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    @PostMapping("/delete")
    public void deleteHousing(@RequestParam Integer id) throws Exception {
        housingService.deleteHousing(id);
    }

    @ApiOperation(value = "Get a housing", response = Housing.class)
    @ApiParam(name = "id", value = "Housing id", required = true)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved housing"),
        @ApiResponse(code = 404, message = "Housing not found"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping("/get")
    public Housing getHousing(@RequestParam Integer id) throws Exceptions.HousingNotFoundException {
        return housingService.getHousing(id);
    }

    @ApiOperation(value = "Get all housings", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved all housings"),
        @ApiResponse(code = 404, message = "No housings found"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping
    public List<Housing> getAllHousings() throws Exceptions.HousingNotFoundException {
        return housingService.getAllHousings();
    }

}
