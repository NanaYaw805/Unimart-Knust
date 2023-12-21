package com.example.unimart;

public class NotificationModel {
    String food_name_notification_x,total_cost_notification_x;
    int notification_generic_image;

    public NotificationModel(String food_name_notification_x, String total_cost_notification_x, int notification_generic_image) {
        this.food_name_notification_x = food_name_notification_x;
        this.total_cost_notification_x = total_cost_notification_x;
        this.notification_generic_image = notification_generic_image;
    }

    public String getFood_name_notification_x() {
        return food_name_notification_x;
    }

    public void setFood_name_notification_x(String food_name_notification_x) {
        this.food_name_notification_x = food_name_notification_x;
    }

    public String getTotal_cost_notification_x() {
        return total_cost_notification_x;
    }

    public void setTotal_cost_notification_x(String total_cost_notification_x) {
        this.total_cost_notification_x = total_cost_notification_x;
    }

    public int getNotification_generic_image() {
        return notification_generic_image;
    }

    public void setNotification_generic_image(int notification_generic_image) {
        this.notification_generic_image = notification_generic_image;
    }
}
