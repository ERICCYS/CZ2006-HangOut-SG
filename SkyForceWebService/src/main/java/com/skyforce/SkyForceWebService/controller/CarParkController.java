package com.skyforce.SkyForceWebService.controller;

import com.skyforce.SkyForceWebService.config.JSONConvert;
import com.skyforce.SkyForceWebService.model.CarParkAvailability;
import com.skyforce.SkyForceWebService.model.CarParkInfo;
import com.skyforce.SkyForceWebService.service.CarParkService;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CarParkController {

    @Autowired
    CarParkService carParkService;

    @GetMapping("/carpark")
    public String getCarParkAvailability() throws IOException, ParseException {
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
        System.out.println(carParkData);
        System.out.println(carParkData.size());
        for (int i = 0; i < carParkData.size(); i++) {
//            System.out.println(carParkData.get(i));
            saveCarParkAvailability((JSONObject)carParkData.get(i));
        }

        return content.toString();
    }

    public void saveCarParkAvailability(JSONObject carParkInfo) {
        String number = (String)carParkInfo.get("carpark_number");
        System.out.println(number);
        JSONArray info = (JSONArray) carParkInfo.get("carpark_info");
        System.out.println(info);
        String updatedTime = (String) carParkInfo.get("update_datetime");
        System.out.println(updatedTime);
        System.out.println();
        CarParkAvailability carParkAvailability = new CarParkAvailability();
        carParkAvailability.setId(number);
        carParkAvailability.setUpdateTime(updatedTime);
        List<CarParkInfo> carParkInfos = new ArrayList<CarParkInfo>();
        for (int i = 0; i  < info.size(); i++) {
            JSONObject obj = (JSONObject)info.get(i);
            Long lotsAvailable = (Long)obj.get("lots_available");
            Long totalLots = (Long)obj.get("total_lots");
            String lotType = (String)obj.get("lot_type");
            carParkInfos.add(new CarParkInfo(lotsAvailable,totalLots,lotType));
        }
        carParkAvailability.setCarParkInfos(carParkInfos);
        // TODO: save the carpark object
        // TODO: update the carpark object every 2 mins

        carParkService.save(carParkAvailability);
        System.out.println("saving info");
        System.out.println(carParkAvailability);
    }
}
