package com.example.sonja.ui.asyncTasks;

public class GetRequestParams {

    String rideTime;
    String uuid;
    String order;

    public GetRequestParams(String rideTime, String uuid, String order){
        this.rideTime = rideTime;
        this.uuid = uuid;
        this.order = order;
    }
}
