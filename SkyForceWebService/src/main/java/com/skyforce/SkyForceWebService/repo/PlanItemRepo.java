package com.skyforce.SkyForceWebService.repo;

import com.skyforce.SkyForceWebService.model.PlanItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanItemRepo extends JpaRepository<PlanItem, Integer> {

    PlanItem findById(Long id);

    PlanItem save(PlanItem planItem);

    void delete(PlanItem planItem);

}
