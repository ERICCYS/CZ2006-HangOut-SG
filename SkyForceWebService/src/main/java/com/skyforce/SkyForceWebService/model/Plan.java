package com.skyforce.SkyForceWebService.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Plan")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="ID")
    private Long id;

    @Column(name="NAME")
    private String name;

    @Column(name="DATE", nullable = false)
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID")
    @JsonBackReference
    private Customer customer;

    @OneToMany(
            mappedBy = "plan",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<PlanItem> planItems = new ArrayList<>();

    public Plan(){}

    public Plan(Date date){
        this.date = date;
    }

    public Plan(Date date, List<PlanItem> planItems){
        this.date = date;
        this.planItems = planItems;
    }

    public Date getDate(){return date;}

    public void setDate(Date date){this.date = date;}

    public Long getId(){return id;}

    public void setId(Long id){this.id = id;}

    public String getName(){return name;}

    public void setName(String s){name = s;}

    public List<PlanItem> getPlanItems(){return planItems;}

    public void setPlanItems(List<PlanItem> planItems){this.planItems = planItems;}

    public void addPlanItem(PlanItem planItem){
        planItems.add(planItem);
        planItem.setPlan(this);
    }

    public void removePlanItem(PlanItem planItem){
        planItems.remove(planItem);
        planItem.setPlan(null);
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }
}
