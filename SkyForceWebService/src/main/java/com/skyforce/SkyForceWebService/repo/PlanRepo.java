package com.skyforce.SkyForceWebService.repo;

import com.skyforce.SkyForceWebService.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepo extends JpaRepository<Plan, Integer> {

    Plan findById(Long id);

    Plan save(Plan plan);

    void delete(Plan plan);

}
