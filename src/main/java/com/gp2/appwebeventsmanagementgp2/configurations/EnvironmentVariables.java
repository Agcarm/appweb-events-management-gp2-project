package com.gp2.appwebeventsmanagementgp2.configurations;


public class EnvironmentVariables {
    private static String eventImages = System.getProperty("user.dir")+"/manager-images";

    public static String getEventImages() {
        return eventImages;
    }
}
