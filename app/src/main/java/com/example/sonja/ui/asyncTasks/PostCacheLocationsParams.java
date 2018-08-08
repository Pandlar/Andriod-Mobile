package com.example.sonja.ui.asyncTasks;


/**
 * Klasse zur Übertragung der Parameter in den gleichnamigen asynchronen Task
 */
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
