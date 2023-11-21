package com.localoge.housingService.Service;

import com.localoge.housingService.Exceptions.Exceptions;
import com.localoge.housingService.Model.Housing;

import java.util.List;

public interface IHousingService {
    Housing createHousing(Housing housing) throws Exception;
    void deleteHousing(Integer id) throws Exception;
    Housing getHousing(Integer id) throws Exceptions.HousingNotFoundException;
    List<Housing> getAllHousings() throws Exceptions.HousingNotFoundException;
}
