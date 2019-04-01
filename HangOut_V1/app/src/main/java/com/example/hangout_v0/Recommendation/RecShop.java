package com.example.hangout_v0.Recommendation;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Random;


public class RecShop {

    String id;
    String name;
    private String[] descriptionTemp={
            "Description is the pattern of narrative development that aims to make vivid a place, ",
            "object, character, or group. Description is one of four rhetorical modes, along with exposition",
            "or group. Description is one of four rhetorical modes, along with exposition, argumentation, and narration.","" +
            "In practice it would be difficult to write literature that drew on just one of the four basic modes."
    };
    String description;
    String contactNumber;
    String contactEmail;
    String category;
    String location;
    String carParkNumbers;
    Float rating;
    String distance;


    public RecShop(String id, String name, String contactNumber, String contactEmail, String category, String location, String carParkNumbers) {
        this.id = id;
        this.name = name;
        this.contactNumber = contactNumber;
        this.contactEmail = contactEmail;
        this.category = category;
        this.location = location;
        this.carParkNumbers = carParkNumbers;

        this.description = descriptionTemp[new Random().nextInt(4)];

        DecimalFormat df = new DecimalFormat("#.#");
        df.setRoundingMode(RoundingMode.CEILING);
        rating = Float.parseFloat(df.format(Math.random()*5));

        distance = df.format(Math.random()*20);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public String getCategory() {
        return category;
    }

    public String getLocation() {
        return location;
    }

    public String getCarParkNumbers() {
        return carParkNumbers;
    }

    public Float getRating() {
        return rating;
    }

    public String getDistance() {
        return distance;
    }

    public String getDescription() {
        return description;
    }

}
