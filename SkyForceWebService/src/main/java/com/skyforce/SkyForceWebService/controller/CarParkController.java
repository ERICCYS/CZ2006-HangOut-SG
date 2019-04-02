package com.skyforce.SkyForceWebService.controller;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
public class CarParkController {

    @GetMapping("/carpark")
    public String getCarParkAvailability(
            @RequestParam String carParkNumber
    ) throws IOException, ParseException  {
        URL url = new URL("https://api.data.gov.sg/v1/transport/carpark-availability");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        connection.disconnect();

        String data = content.toString();
        JSONParser jsonParser = new JSONParser();
        JSONObject object = (JSONObject)jsonParser.parse(data);
        JSONArray items = (JSONArray) object.get("items");
        JSONObject carPark = (JSONObject)items.get(0);
        JSONArray carParkData = (JSONArray)carPark.get("carpark_data");
        JSONObject carParkInfo;
        String cpNumber;
        Integer totalLots = 0;
        Integer lotsAvailable = 0;
        JSONArray info;
        for (int i = 0; i < carParkData.size(); i++) {
            carParkInfo = (JSONObject)carParkData.get(i);
            cpNumber = (String)carParkInfo.get("carpark_number");
            if (cpNumber.equals(carParkNumber)) {
                info = (JSONArray) carParkInfo.get("carpark_info");
                for (int j = 0; j  < info.size(); j++) {
                    JSONObject obj = (JSONObject)info.get(j);
                    lotsAvailable += Integer.parseInt((String)obj.get("lots_available"));
                    totalLots += Integer.parseInt((String)obj.get("total_lots"));
                }
            }
        }
        return totalLots + " / " + lotsAvailable;
    }
}
