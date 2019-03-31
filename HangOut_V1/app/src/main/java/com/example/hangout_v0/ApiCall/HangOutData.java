package com.example.hangout_v0.ApiCall;

import org.json.JSONArray;
import org.json.JSONObject;

public class HangOutData {
    public static JSONObject customer;
    public static JSONObject vendor;
    public static JSONObject shop;
    public static String accessToken;

    public static String getAccessToken() {
        return accessToken;
    }

    public static void setAccessToken(String accessToken) {
        HangOutData.accessToken = accessToken;
    }

    public static JSONObject getShop() {
        return shop;
    }

    public static void setShop(JSONObject shop) {
        HangOutData.shop = shop;
    }

    public static JSONArray planList;
    public static JSONArray shopList;
    public static JSONArray reservationList;

    public static JSONObject getCustomer() {
        return customer;
    }

    public static void setCustomer(JSONObject customer) {
        HangOutData.customer = customer;
    }

    public static JSONObject getVendor() {
        return vendor;
    }

    public static void setVendor(JSONObject vendor) {
        HangOutData.vendor = vendor;
    }

    public static JSONArray getPlanList() {
        return planList;
    }

    public static void setPlanList(JSONArray planList) {
        HangOutData.planList = planList;
    }

    public static JSONArray getShopList() {
        return shopList;
    }

    public static void setShopList(JSONArray shopList) {
        HangOutData.shopList = shopList;
    }

    public static JSONArray getReservationList() {
        return reservationList;
    }

    public static void setReservationList(JSONArray reservationList) {
        HangOutData.reservationList = reservationList;
    }
}
