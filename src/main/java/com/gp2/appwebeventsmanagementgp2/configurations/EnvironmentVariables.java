package com.gp2.appwebeventsmanagementgp2.configurations;

import org.springframework.security.core.userdetails.UserDetails;


public class EnvironmentVariables {
    private static String eventImages = System.getProperty("user.dir")+"/src/main/resources/static/manager-images";
    private static UserDetails user;

    public static String getEventImages() {
        return eventImages;
    }

    public static UserDetails getUser() {
        return user;
    }

    public static void setUser(UserDetails user) {
        EnvironmentVariables.user = user;
    }
}
