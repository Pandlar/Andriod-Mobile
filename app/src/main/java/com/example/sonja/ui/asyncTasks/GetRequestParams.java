package com.example.sonja.ui.asyncTasks;

/**
 * Klasse zur Übertragung der Parameter in den gleichnamigen asynchronen Task
 */
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
