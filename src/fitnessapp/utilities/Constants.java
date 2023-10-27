/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fitnessapp.utilities;

import java.io.File;
import java.nio.file.Path;

/**
 *
 * @author orion90
 */
public class Constants {
    public static final String API_URL="http://localhost:8080/api";
    public static final String ACTIVITY_URL_PATH="/activity";
    private static final File file=new File("");
    public static final String URL_DB = file.getAbsolutePath()+"/src/fitnessdb.sqlite";
}
