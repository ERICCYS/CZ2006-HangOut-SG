package com.skyforce.SkyForceWebService.repo;

import com.skyforce.SkyForceWebService.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepo extends JpaRepository<Plan, Integer> {
    // find all the plan
    Plan findById(Long id);

    // insert one plan
    Plan save(Plan plan);

    // delete one plan
    void delete(Plan plan);

}
