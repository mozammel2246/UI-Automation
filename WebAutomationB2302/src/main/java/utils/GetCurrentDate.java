package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public  class GetCurrentDate {

    public static String getCurrentDate(){
        // Get current date
        Date currentDate = new Date();

        // Format date as a string
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(currentDate);
        System.out.println("Current Date: " + formattedDate);
        return formattedDate;
    }

    public  static String getCurrentDateWithTimeStamp(){
        // Get current date
        Date currentDate = new Date();

        Date currentDateTime = new Date();

        // Format date and time as a string with timestamp
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH mm ss SSS");
        String formattedDateTime = dateFormat.format(currentDateTime);
        System.out.println("Current Date: " + formattedDateTime);
        return formattedDateTime;
    }

 /*   public static void main(String[] args) {

        GetCurrentDate data = new GetCurrentDate();
        data.getCurrentDate();
        data.getCurrentDateWithTimeStamp();

    }*/

}
