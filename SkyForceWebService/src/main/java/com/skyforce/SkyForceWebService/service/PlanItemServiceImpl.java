package com.skyforce.SkyForceWebService.service;
import com.skyforce.SkyForceWebService.model.PlanItem;
import com.skyforce.SkyForceWebService.repo.PlanItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanItemServiceImpl implements PlanItemService{

    @Autowired
    PlanItemRepo planItemRepo;

    @Override
    public List<PlanItem> findAll() {
        return planItemRepo.findAll();
    }

    @Override
    public PlanItem findPlanItemById(Long id) {
        return planItemRepo.findById(id);
    }

    @Override
    public PlanItem save(PlanItem planItem) {
        return planItemRepo.save(planItem);
    }

    @Override
    public void delete(PlanItem planItem) {
        planItemRepo.delete(planItem);
    }
}
