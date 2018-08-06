package com.example.sonja.ui.asyncTasks;

import com.example.sonja.ui.NeueFahrt1;

public class PostCacheLocationsParams {
    String userId;
    String homeCoordinates;
    String officeCoordinates;

    /**
     * Bereitet die Parameter CacheLocations für das Posten in die DB vor (Tabelle: CacheLocations).
     * @param userId
     * @param homeCoordinates
     * @param officeCoordinates
     */
    public PostCacheLocationsParams(String userId, String homeCoordinates, String officeCoordinates){
       this.userId=userId;
       this.homeCoordinates=homeCoordinates;
       this.officeCoordinates=officeCoordinates;
    }
}
