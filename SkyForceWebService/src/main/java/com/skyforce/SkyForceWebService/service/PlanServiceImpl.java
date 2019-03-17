package com.skyforce.SkyForceWebService.service;

import com.skyforce.SkyForceWebService.model.Plan;
import com.skyforce.SkyForceWebService.repo.PlanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public class PlanServiceImpl implements PlanService{
    @Autowired
    PlanRepo planRepo;

    @Override
    public List<Plan> findAll(){return planRepo.findAll();}

    @Override
    public Plan findPlanById(Long id){return planRepo.findById(id);}

    @Override
    public Plan save(Plan plan){return planRepo.save(plan);}

    @Override
    public void delete(Plan plan){planRepo.delete(plan);}

}
