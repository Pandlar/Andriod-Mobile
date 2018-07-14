package com.example.sonja.ui.asyncTasks;

import com.example.sonja.ui.NeueFahrt1;

public class PostRequestParams {
    String id;
    int earliest_minute;
    int earliest_hour;
    int latest_minute;
    int latest_hour;
    NeueFahrt1.RequestRole requestRole;
    String direction;

    public PostRequestParams(String id, int earliest_minute, int earliest_hour,
                             int latest_minute, int latest_hour,
                             NeueFahrt1.RequestRole requestRole, String direction){
        this.id=id;
        this.earliest_minute=earliest_minute;
        this.earliest_hour=earliest_hour;
        this.latest_hour=latest_hour;
        this.latest_minute=latest_minute;
        this.requestRole=requestRole;
        this.direction=direction;
    }
}
