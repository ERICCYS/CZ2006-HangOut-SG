package com.skyforce.SkyForceWebService.controller;

import com.skyforce.SkyForceWebService.config.JSONConvert;
import com.skyforce.SkyForceWebService.model.Customer;
import com.skyforce.SkyForceWebService.model.Plan;
import com.skyforce.SkyForceWebService.model.PlanItem;
import com.skyforce.SkyForceWebService.service.CustomerService;
import com.skyforce.SkyForceWebService.service.PlanService;
import com.skyforce.SkyForceWebService.service.PlanItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping("/plans")
    public String getAllPlan(){
        List<Plan> planItemList = planService.findAll();
        return JSONConvert.JSONConverter(planItemList);
    }

    @GetMapping("/customer/plan")
    public String getPlanById(
            @RequestParam Long id,
            @RequestHeader(value = "Authorization") String accessToken
    ){
        Plan plan = planService.findPlanById(id);
        String[] info = ValidationController.decryptAccessToken(accessToken);
        if (info.length != 2)
            throw new IllegalArgumentException();

        if (Long.parseLong(info[0]) == plan.getCustomer().getId() && info[1].equals("CUSTOMER")) {
            return JSONConvert.JSONConverter(plan);
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    @PostMapping("/customer/plan")
    public String createPlan(
            @RequestParam Long customerId,
            @RequestHeader (value = "Authorization") String accessToken,
            @Valid @RequestBody Plan plan
    ) {
        String[] info = ValidationController.decryptAccessToken(accessToken);
        System.out.println(info[0]);
        System.out.println(info[1]);
        if (info.length != 2)
            throw new IllegalArgumentException();
        if (Long.parseLong(info[0]) == customerId && info[1].equals("CUSTOMER")) {
            Customer oldCustomer = customerService.findCustomerById(customerId);
            oldCustomer.addPlan(plan);
            return JSONConvert.JSONConverter(customerService.save(oldCustomer));
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    @DeleteMapping("/customer/plan")
    public String deletePlan(
            @RequestParam Long customerId,
            @RequestParam Long planId,
            @RequestHeader (value = "Authorization") String accessToken
    ) {
        String[] info = ValidationController.decryptAccessToken(accessToken);
        if (info.length != 2)
            throw new IllegalArgumentException();
        if (Long.parseLong(info[0]) == customerId && info[1].equals("CUSTOMER")) {
            Customer oldCustomer = customerService.findCustomerById(customerId);
            Plan plan = planService.findPlanById(planId);
            oldCustomer.removePlan(plan);
            return JSONConvert.JSONConverter(customerService.save(oldCustomer));
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    @PostMapping("/customer/plan/addPlanItem")
    public String addPlanItem (
            @RequestParam Long planId,
            @RequestHeader (value = "Authorization") String accessToken,
            @Valid @RequestBody PlanItem planItem
    ) {

        Plan oldPlan = planService.findPlanById(planId);
        String[] info = ValidationController.decryptAccessToken(accessToken);

        if (info.length != 2 || oldPlan == null)
            throw new IllegalArgumentException();

        if (Long.parseLong(info[0]) == oldPlan.getCustomer().getId() && info[1].equals("CUSTOMER")) {
            oldPlan.addPlanItem(planItem);
            return JSONConvert.JSONConverter(planService.save(oldPlan));
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    @DeleteMapping("/customer/plan/delItem/{itemId}")
    public String deleteItem (
            @RequestParam Long customerId,
            @RequestParam Long planId,
            @PathVariable("itemId") Long itemId,
            @RequestHeader (value = "Authorization") String accessToken
            ) {
        Plan plan = planService.findPlanById(planId);
        String[] info = ValidationController.decryptAccessToken(accessToken);

        if (info.length != 2 || plan == null)
            throw new IllegalArgumentException();

        if (Long.parseLong(info[0]) == customerId && info[1].equals("CUSTOMER")) {
            PlanItem item = planItemService.findPlanItemById(itemId);
            plan.removePlanItem(item);
            return JSONConvert.JSONConverter(planService.save(plan));
        }
        else {
            throw new IllegalArgumentException();
        }
    }
}
