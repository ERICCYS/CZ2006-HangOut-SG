package com.skyforce.SkyForceWebService.model;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "PlanItem")
public class PlanItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;


    @Column(name = "Time")
    private Time scheduledVisitTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ShopId")
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PlanId")
    private Plan plan;

    public PlanItem() {}

    public PlanItem(Time time) {
        this.scheduledVisitTime = time;
    }

    public Long getId() {return this.id;}

    public void setId(Long id) {this.id = id;}

    public Shop getShop() {return this.shop;}

    public void setShopId(Shop shop) {this.shop = shop;}

    public Time getScheduledVisitTime() {return this.scheduledVisitTime;}

    public void setScheduledVisitTime(Time time){this.scheduledVisitTime = time;}

    public Plan getPlan(){return plan;}

    public void setPlan(Plan plan){this.plan = plan;}


}