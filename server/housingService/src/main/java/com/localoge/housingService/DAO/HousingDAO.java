package com.localoge.housingService.DAO;

import com.localoge.housingService.Model.Housing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HousingDAO extends JpaRepository<Housing, Integer> {
}
