/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fitnessapp.utilities;

import java.io.File;
import java.util.List;

/**
 *
 * @author orion90
 */
public class Constants {
    public static final String API_URL="http://localhost:8080/api";
    //Activities
    public static final String ACTIVITY_URL_PATH="/activity";
    public static final String ACTIVITY_ADD_URL_PATH="/activity/add";
    public static final String ACTIVITY_FETCH_URL_PATH=ACTIVITY_URL_PATH+"/fetchAll";
    public static final String ACTIVITY_DELETE_MANY_URL_PATH =ACTIVITY_URL_PATH+"/deleteMany";
    public static final String ACTIVITY_SEARCH_URL_PATH =ACTIVITY_URL_PATH+"/search?";
    public static final String ACTIVITY_UPDATE_URL_PATH=ACTIVITY_URL_PATH+"/update/";
    //Customer
    public static final String CUSTOMER_URL_PATH="/customer";
    public static final String CUSTOMER_UPDATE_URL_PATH=CUSTOMER_URL_PATH+"/update/";
    public static final String CUSTOMER_ADD_URL_PATH=CUSTOMER_URL_PATH+"/add/";
    public static final String CUSTOMER_FETCH_URL_PATH=CUSTOMER_URL_PATH+"/fetchAll";
    //Coach
    public static final String COACH_URL_PATH="/coach";
    public static final String COACH_ADD_URL_PATH=COACH_URL_PATH+"/add/";
    public static final String COACH_UPDATE_URL_PATH=COACH_URL_PATH+"/update/";
    public static final String COACH_FETCH_URL_PATH=COACH_URL_PATH+"/fetchAll";
    public static final String COACH_DELETE_MANY_URL_PATH=COACH_URL_PATH+"/delete";
    //Room
    public static final String ROOM_URL_PATH="/room";
    public static final String ROOM_ADD_URL_PATH="/room/add";
    public static final String ROOM_FETCH_URL_PATH=ROOM_URL_PATH+"/fetchAll";
    public static final String ROOM_FETCH_SUBSC_URL_PATH=ROOM_URL_PATH+"/fetchWithTotalSubscribe";
    public static String ROOM_DELETE_URL_PATH=ROOM_URL_PATH+"/delete/";
    public static String ROOM_UPDATE_URL_PATH=ROOM_URL_PATH+"/update/";
    //Planning 
    public static final String PLANNING_URL_PATH="/planning";
    public static final String PLANNING_DELETE_MANY_URL_PATH=PLANNING_URL_PATH+"/delete";
    public static final String PLANNING_SEARCH_URL_PATH=PLANNING_URL_PATH+"/search?";
    public static final String PLANNING_UPDATE_URL_PATH="/planning/update/";
    public static final String PLANNING_FETCH_URL_PATH=PLANNING_URL_PATH+"/fetchAll";
    public static final String PLANNING_ADD_URL_PATH=PLANNING_URL_PATH+"/add/";
    
    
    //Subscription
    public static String SUBSCRIPTION_URL_PATH="/subscription";
    public static String SUBSCRIPTION_FETCH_URL_PATH=SUBSCRIPTION_URL_PATH+"/fetchAll";
    //Subscription
    public static String SUBSCRIBE_URL_PATH="/subscribe";
    public static String SUBSCRIBE_FETCH_URL_PATH=SUBSCRIBE_URL_PATH+"/fetchAll";
    
    
    public static final String ICONS_PATH="fitnessapp/icons/";
    private static final File file=new File("");
    public static final List<String> COLORS=List.of(
        "#1A237E",
        "#0D47A1",
        "#311B92",
        "#01579B",
        "#006064",
        "#004D40",
        "#1B5E20",
        "#9E9D24",
        "#E65100",
        "#BF360C",
        "#4E342E",
        "#424242",
        "#37474F",
        "#4527A0",
        "#1565C0",
        "#C62828",
        "#6A1B9A"
    );
    public static final int COLORS_SIZE=COLORS.size()-1;
    public static final String URL_DB = file.getAbsolutePath()+"/src/fitnessdb.sqlite";
    
}
