package tun.app.holidays.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by nourjbel on 27/02/2015.
 */
public class CalendarUtils {

    public static boolean isDateInWeekend(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        if(c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY||c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
            return true;
        else
            return false;
    }

}
