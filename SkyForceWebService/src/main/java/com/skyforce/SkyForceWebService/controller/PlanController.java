package com.skyforce.SkyForceWebService.controller;

import com.skyforce.SkyForceWebService.config.JSONConvert;
import com.skyforce.SkyForceWebService.model.Customer;
import com.skyforce.SkyForceWebService.model.Plan;
import com.skyforce.SkyForceWebService.model.PlanItem;
import com.skyforce.SkyForceWebService.service.CustomerService;
import com.skyforce.SkyForceWebService.service.PlanService;
import com.skyforce.SkyForceWebService.service.PlanItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class PlanController {
    @Autowired
    PlanService planService;

    @Autowired
    PlanItemService planItemService;

    @Autowired
    CustomerService customerService;
    private AtomicLong nextId = new AtomicLong();

    @GetMapping("/customer/plan")
    public String getAllPlan(){
        List<Plan> planItemList = planService.findAll();
        return JSONConvert.JSONConverter(planItemList);
    }

    @GetMapping("/customer/plan/{id}")
    public String getPlanById(@PathVariable("id") Long id){
        Plan plan = planService.findPlanById(id);
        return JSONConvert.JSONConverter(plan);
    }
    // Customer add plan
    @PostMapping("/customer/plan")
    public String createPlan(
            @RequestParam Long customerId,
            @Valid @RequestBody Plan plan
    ) {
        Customer oldCustomer = customerService.findCustomerById(customerId);
        plan.setId(nextId.incrementAndGet());

        oldCustomer.addPlan(new Plan(plan.getDate(), plan.getPlanItems()));
        return JSONConvert.JSONConverter(customerService.save(oldCustomer));
    }

    // Customer delete plan
    @DeleteMapping("/customer/plan")
    public String deletePlan(
            @RequestParam Long customerId,
            @RequestParam Long planId
    ) {
        Customer oldCustomer = customerService.findCustomerById(customerId);
        Plan plan = planService.findPlanById(planId);
        oldCustomer.removePlan(plan);
        return JSONConvert.JSONConverter(customerService.save(oldCustomer));
    }

    @PutMapping("/customer/plan/addPlanItem")
    public String addPlanItem (@RequestParam Long customerId, @RequestParam Long planId, @Valid @RequestBody PlanItem planItem) {
        Plan oldPlan = planService.findPlanById(planId);
        oldPlan.addPlanItem(planItem);
        return  JSONConvert.JSONConverter(planService.save(oldPlan)) ;
    }

    @PutMapping("/customer/plan/delItem/{itemId}")
    public String deleteItem (@RequestParam Long customerId, @RequestParam Long planId, @PathVariable("itemId") Long itemId) {
        Plan oldPlan = planService.findPlanById(planId);
        PlanItem item = planItemService.findPlanItemById(itemId);
        System.out.println(item);
        oldPlan.removePlanItem(item);
        return JSONConvert.JSONConverter(planService.save(oldPlan));

    }


}
