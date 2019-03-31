package com.example.hangout_v0.Me.plan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlanDataStub {
    public PlanDataStub(String user)
    {
        //get user
    }

    public String[] getAllPlanName(){
        String[] name={"Plan A", "Plan B","Plan C"};
        return name;
    }

    public String[] getAllPlanDescription(){
        String[] description={"Description A", "Description B","Description C"};
        return description;
    }
    public String[] getAllPlanDateTime(){
        String[] dateTime={"May 4", "March 9","March 1"};
        return dateTime;
    }

    public ArrayList<String> getAllShopName(String planName){
        if(planName.equals("New Plan")){
            ArrayList<String> shopName = new ArrayList<String>(
                    Arrays.asList("Shop A1", "Shop A2","Shop A3"));
            return shopName;
        }
        if(planName.equals("Plan A")){
            ArrayList<String> shopName = new ArrayList<String>(
                    Arrays.asList("Shop A1", "Shop A2","Shop A3"));
            return shopName;
        }
        if(planName.equals("Plan B")){
            ArrayList<String> shopName = new ArrayList<String>(
                    Arrays.asList("Shop B1", "Shop B2","Shop B3"));
            return shopName;
        }
        if(planName.equals("Plan C")){
        ArrayList<String> shopName = new ArrayList<String>(
                Arrays.asList("Shop C1", "Shop C2","Shop C3"));
            return shopName;
        }
        ArrayList<String> shopName = new ArrayList<String>();
        return shopName;

    }
    public ArrayList<String> getAllShopAddress(String planName){

        ArrayList<String> shopDT = new ArrayList<String>(
                Arrays.asList("NTU", "JE","NTU"));
        return shopDT;

    }
    public ArrayList<String> getAllShopDateTime(String planName){

        ArrayList<String> shopDT = new ArrayList<String>(
                Arrays.asList("Mar 8  10:00", "Mar 8  10:00","Mar 8  10:00"));
//        return shopName;
//        if(planName.equals("Plan A")){
//            String[] shopDT={"Mar 8  10:00 Am", "Marc 8  10:00 Am","Mar 8  10:00 Am"};
//            return shopDT;
//        }
//        if(planName.equals("Plan B")){
//            String[] shopDT={"Mar 8  10:00 Am", "Marc 8  10:00 Am","Mar 8  10:00 Am"};
//            return shopDT;
//        }
//        if(planName.equals("Plan C")){
//            String[] shopDT={"Mar 8  10:00 Am", "Marc 8  10:00 Am","Mar 8  10:00 Am"};
//            return shopDT;
//        }
//        String[] shopDT={};
        return shopDT;

    }
}
