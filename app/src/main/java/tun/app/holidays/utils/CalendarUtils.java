package tun.app.holidays.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import tun.app.holidays.data.SharedPreferenceManager;
import tun.app.holidays.model.Holiday;
import tun.app.holidays.model.HolidayType;

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


    public  static int getBalanceCurrentCP(ArrayList<Holiday> listHoliday){
        int nbrCp = SharedPreferenceManager.getInstance().getNbrPayedHoliday();
        return nbrCp - getBalanceUsed(listHoliday,HolidayType.HOLIDAY_PAYED);
    }




    public  static int getBalanceCurrentRtt(ArrayList<Holiday> listHoliday){
        int nbrCp = SharedPreferenceManager.getInstance().getNbrRttHoliday();
        return nbrCp - getBalanceUsed(listHoliday,HolidayType.HOLIDAY_RTT);
    }


    public  static int getBalanceUsed(ArrayList<Holiday> listHoliday,HolidayType typeHoliday){
        int nbrCp = 0;

        for (int i=0;i<listHoliday.size();i++){
            if(listHoliday.get(i).getHolidayType()== typeHoliday){
                nbrCp++;
            }
        }
        return nbrCp;
    }
}
