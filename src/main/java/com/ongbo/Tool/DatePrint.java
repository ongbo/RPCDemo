package com.ongbo.Tool;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DatePrint {
    public static void printdate(){
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(now));
    }
}
