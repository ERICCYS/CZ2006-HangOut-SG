package com.skyforce.SkyForceWebService.service;

import com.skyforce.SkyForceWebService.model.Plan;

import java.util.List;

public interface PlanService {

    List<Plan> findAll();

    Plan findPlanById(Long id);

    Plan save(Plan plan);

    void delete(Plan plan);

}
