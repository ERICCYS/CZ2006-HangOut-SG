package com.skyforce.SkyForceWebService.service;

import com.skyforce.SkyForceWebService.model.PlanItem;

import java.util.List;

public interface PlanItemService {
    List<PlanItem> findAll();

    PlanItem findPlanItemById(Long id);

    PlanItem save(PlanItem planItem);

    void delete(PlanItem planItem);

}

