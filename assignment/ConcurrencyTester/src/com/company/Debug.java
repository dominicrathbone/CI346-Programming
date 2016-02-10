package com.company;

/**
 * Created by drathbone on 10/02/16.
 */
public class Debug {
    public static boolean DEBUG = false;

    public static void log(String text) {
        if(DEBUG == true) {
            System.out.println(text);
        }
    }
}
