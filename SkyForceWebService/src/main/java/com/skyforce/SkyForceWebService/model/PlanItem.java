package com.skyforce.SkyForceWebService.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "PlanItem")
public class PlanItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;


    @Column(name = "TIME")
    private Time scheduledVisitTime;

    @Column(name = "SHOP_ID")
    private Long shopId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PLAN_ID")
    @JsonBackReference
    private Plan plan;

    public PlanItem() {
    }

    public PlanItem(Time time) {
        this.scheduledVisitTime = time;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Time getScheduledVisitTime() {
        return this.scheduledVisitTime;
    }

    public void setScheduledVisitTime(Time time) {
        this.scheduledVisitTime = time;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }


}