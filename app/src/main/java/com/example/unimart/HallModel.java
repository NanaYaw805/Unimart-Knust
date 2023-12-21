package com.example.unimart;

import java.io.Serializable;

public class HallModel{
    String  model_food_name,model_food_location,model_food_available_time,vendor_id;
    int advert_images;

    public HallModel()
    {

    }

    public HallModel(String model_food_name, String model_food_location, String model_food_available_time,int advert_images, String vendor_id) {
        this.model_food_name = model_food_name;
        this.model_food_location = model_food_location;
        this.model_food_available_time = model_food_available_time;
        this.vendor_id = vendor_id;
        this.advert_images=advert_images;
    }


}
