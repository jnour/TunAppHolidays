package tun.app.holidays.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import tun.app.holidays.R;
import tun.app.holidays.model.Holiday;
import tun.app.library.utils.JFLog;
import tun.app.library.utils.ObjectSerializer;

/**
 * Created by nourjbel on 28/02/2015.
 */
public class SharedPreferenceManager {


    public static final String PREFS_NAME = "HOLIDAY_PREFS";

    private static final String PREFS_KEY_NBR_HOLIDAY_PAYED = "HOLIDAY_PAYED_NBR";
    private static final String PREFS_KEY_NBR_HOLIDAY_NOT_PAYED = "HOLIDAY_NOT_PAYED_NBR";
    private static final String PREFS_KEY_NBR_HOLIDAY_RTT = "HOLIDAY_RTT_NBR";
    private static final String PREFS_KEY_NBR_HOLIDAY_RTT_EMPLOYEE = "HOLIDAY_RTT_EMPLOYEE_NBR";
    private static final String PREFS_KEY_NBR_HOLIDAY_RTT_EMPLOYER = "HOLIDAY_RTT_EMPLOYER_NBR";
    private static final String PREFS_KEY_NBR_HOLIDAY_PATERNITY = "HOLIDAY_PATERNITY_NBR";
    private static final String PREFS_KEY_NBR_HOLIDAY_MATERNITY = "HOLIDAY_MATERNITY_NBR";

    private static final String PREFS_KEY_LIST_HOLIDAY = "HOLIDAY_LIST";

    private int nbrPayedHoliday;
    private int nbrNotPayedHoliday;
    private int nbrRttHoliday;
    private int nbrRttEmployeeHoliday;
    private int nbrRttEmployerHoliday;
    private int nbrMaternityHoliday;

    private ArrayList<Holiday> holidayArrayList;

    /*
    HOLIDAY_PAYED,
    HOLIDAY_NOT_PAYED,
    HOLIDAY_RTT,
    HOLIDAY_RTT_EMPLOYEE,
    HOLIDAY_RTT_EMPLOYER,
    HOLIDAY_PATERNITY,
    HOLIDAY_MATERNITY,
    HOLIDAY_DEATH,
    HOLIDAY_HEALTH,
    HOLIDAY_MARRIAGE,
    HOLIDAY_TRAINING,
    HOLIDAY_VIP,
    HOLIDAY_CUSTOM
    */

    public static SharedPreferenceManager instance;
    private SharedPreferences.Editor editor;


    public static SharedPreferenceManager getInstance(){
        if(instance==null){
            instance = new SharedPreferenceManager();
        }
        return instance;
    }

    public void init(Context context){
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        editor = settings.edit();
        Resources resources = context.getResources();

        nbrPayedHoliday = settings.getInt(PREFS_KEY_NBR_HOLIDAY_PAYED,resources.getInteger(R.integer.nbr_payed_holiday));
        nbrNotPayedHoliday = settings.getInt(PREFS_KEY_NBR_HOLIDAY_NOT_PAYED, 0);
        nbrRttHoliday = settings.getInt(PREFS_KEY_NBR_HOLIDAY_RTT,0);
        nbrRttEmployeeHoliday = settings.getInt(PREFS_KEY_NBR_HOLIDAY_RTT_EMPLOYEE,0);
        nbrRttEmployerHoliday = settings.getInt(PREFS_KEY_NBR_HOLIDAY_RTT_EMPLOYER,0);
        nbrMaternityHoliday = settings.getInt(PREFS_KEY_NBR_HOLIDAY_MATERNITY,0);

        String jsonHolidays = settings.getString(PREFS_KEY_LIST_HOLIDAY, "");
        Gson gson = new Gson();
        holidayArrayList = gson.fromJson(jsonHolidays, new TypeToken<List<Holiday>>(){}.getType());


    }

    public  void savePrefsData(){
        editor.putInt(PREFS_KEY_NBR_HOLIDAY_PAYED,getNbrPayedHoliday());
        editor.putInt(PREFS_KEY_NBR_HOLIDAY_NOT_PAYED,getNbrNotPayedHoliday());
        editor.putInt(PREFS_KEY_NBR_HOLIDAY_RTT,getNbrRttHoliday());
        editor.putInt(PREFS_KEY_NBR_HOLIDAY_RTT_EMPLOYEE,getNbrRttEmployeeHoliday());
        editor.putInt(PREFS_KEY_NBR_HOLIDAY_RTT_EMPLOYER,getNbrRttEmployerHoliday());
        editor.putInt(PREFS_KEY_NBR_HOLIDAY_MATERNITY,getNbrMaternityHoliday());

       /* try {
            editor.putString(PREFS_KEY_LIST_HOLIDAY, ObjectSerializer.serialize(holidayArrayList));
        } catch (Exception e) {
           JFLog.d(SharedPreferenceManager.class.getName(),e.getMessage());
        }*/
        Gson gson = new Gson();
        String jsonHolidays = gson.toJson(holidayArrayList);
        editor.putString(PREFS_KEY_LIST_HOLIDAY,jsonHolidays);

        editor.commit();
    }

    public int getNbrPayedHoliday() {
        return nbrPayedHoliday;
    }

    public void setNbrPayedHoliday(int nbrPayedHoliday) {
        this.nbrPayedHoliday = nbrPayedHoliday;
    }

    public int getNbrNotPayedHoliday() {
        return nbrNotPayedHoliday;
    }

    public void setNbrNotPayedHoliday(int nbrNotPayedHoliday) {
        this.nbrNotPayedHoliday = nbrNotPayedHoliday;
    }

    public int getNbrRttHoliday() {
        return nbrRttHoliday;
    }

    public void setNbrRttHoliday(int nbrRttHoliday) {
        this.nbrRttHoliday = nbrRttHoliday;
    }

    public int getNbrRttEmployeeHoliday() {
        return nbrRttEmployeeHoliday;
    }

    public void setNbrRttEmployeeHoliday(int nbrRttEmployeeHoliday) {
        this.nbrRttEmployeeHoliday = nbrRttEmployeeHoliday;
    }

    public int getNbrRttEmployerHoliday() {
        return nbrRttEmployerHoliday;
    }

    public void setNbrRttEmployerHoliday(int nbrRttEmployerHoliday) {
        this.nbrRttEmployerHoliday = nbrRttEmployerHoliday;
    }

    public int getNbrMaternityHoliday() {
        return nbrMaternityHoliday;
    }

    public void setNbrMaternityHoliday(int nbrMaternityHoliday) {
        this.nbrMaternityHoliday = nbrMaternityHoliday;
    }


    public ArrayList<Holiday> getHolidayArrayList() {
        if(holidayArrayList==null)
            holidayArrayList = new ArrayList<Holiday>();
        return holidayArrayList;
    }

    public void setHolidayArrayList(ArrayList<Holiday> holidayArrayList) {
        this.holidayArrayList = holidayArrayList;
    }
}
