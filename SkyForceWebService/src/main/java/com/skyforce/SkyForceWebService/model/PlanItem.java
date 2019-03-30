package com.skyforce.SkyForceWebService.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PlanItem")
public class PlanItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date scheduledVisitTime;

    @Column(name = "SHOP_ID")
    private Long shopId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PLAN_ID")
    @JsonBackReference
    private Plan plan;

    public PlanItem() {
    }

    public PlanItem(Date time) {
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

    public Date getScheduledVisitTime() {
        return this.scheduledVisitTime;
    }

    public void setScheduledVisitTime(Date time) {
        this.scheduledVisitTime = time;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }
}