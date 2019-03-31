package com.skyforce.SkyForceWebService.repo;

import com.skyforce.SkyForceWebService.model.PlanItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanItemRepo extends JpaRepository<PlanItem,Integer>{
    // find a planItem by id
    PlanItem findById(Long id);

    // insert one PlanItem
    PlanItem save(PlanItem planItem);

    // delete one user
    void delete(PlanItem planItem);

}




