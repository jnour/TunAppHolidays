package tun.app.holidays.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.squareup.timessquare.CalendarCellDecorator;
import com.squareup.timessquare.CalendarPickerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import tun.app.holidays.R;
import tun.app.holidays.data.SharedPreferenceManager;
import tun.app.holidays.model.Holiday;
import tun.app.holidays.square.SampleDecorator;
import tun.app.holidays.utils.CalendarUtils;
import tun.app.library.utils.JFLog;

/**
 * Created by nourjbel on 08/03/2015.
 */
public class PickHolidayActivity extends Activity implements View.OnClickListener,CalendarPickerView.OnInvalidDateSelectedListener,CalendarPickerView.OnDateSelectedListener{

    private static String TAG = PickHolidayActivity.class.getName();

    /************ StartDeclare Views ************/
    private LinearLayout layoutOptionHoliday;
    private LinearLayout layoutOptionPickTypeHoliday;
    private Button mButtConfirm;
    private  CalendarPickerView mPickHolidayCalendar;
    /************* End Declare Views ***********/

    /************ Start Declare Variables ************/
    private Date mPopUpSelectedDate;
    private ArrayList<Holiday> mHoldayList;
    /************* End Declare Variables ***********/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog);

        mPickHolidayCalendar = (CalendarPickerView) findViewById(R.id.calendar_view);
        layoutOptionHoliday = (LinearLayout)findViewById(R.id.layout_holiday_option);
        mButtConfirm = (Button)findViewById(R.id.pick_butt_confirm);
        mButtConfirm.setOnClickListener(this);
        mHoldayList = SharedPreferenceManager.getInstance().getHolidayArrayList();
        layoutOptionPickTypeHoliday = (LinearLayout)findViewById(R.id.pick_layout_repartition);

        final Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 2);

        final Calendar lastYear = Calendar.getInstance();
        lastYear.add(Calendar.YEAR, -1);



        onPopUpCalClick();
    }


    CalendarPickerView.DateSelectableFilter filter = new CalendarPickerView.DateSelectableFilter() {
        @Override
        public boolean isDateSelectable(Date date) {

            boolean isInHoliday = false;
            for (int i =0;i<mHoldayList.size();i++){
                if(mHoldayList.get(i).getListDateHoliday().contains(date)){
                    isInHoliday = true;
                    break;
                }
            }
            if(isInHoliday){
                return false;
            }else if(CalendarUtils.isDateInWeekend(date)){
                return false;
            }else{
                return true;
            }

        }


    };


    @Override
    public void onInvalidDateSelected(Date date) {
        JFLog.d(TAG, date.toString());
    }

    @Override
    public void onDateSelected(Date date) {
        JFLog.d(TAG,date.toString());
    }

    @Override
    public void onDateUnselected(Date date) {
        JFLog.d(TAG,date.toString());
    }
    @Override
    public void onClick(View view) {
            if(view==mButtConfirm){
                if(mPickHolidayCalendar.getVisibility()==View.VISIBLE){
                    layoutOptionPickTypeHoliday.setVisibility(View.VISIBLE);
                    //mPickHolidayCalendar.setVisibility(View.GONE);
                }
            }
    }

    private void onPopUpCalClick() {

        mPickHolidayCalendar =(CalendarPickerView) findViewById(R.id.calendar_view);

        mPickHolidayCalendar.setDateSelectableFilter(filter);

        final Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 2);

        final Calendar lastYear = Calendar.getInstance();
        lastYear.add(Calendar.YEAR, -1);
        mPickHolidayCalendar.init(lastYear.getTime(), nextYear.getTime()) //
                .withSelectedDate(new Date())
                .inMode(CalendarPickerView.SelectionMode.RANGE);




        mPickHolidayCalendar.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {
                layoutOptionHoliday.setVisibility(View.VISIBLE);
            }

            @Override
            public void onDateUnselected(Date date) {

            }
        });

    }


    private void onConfirmHolidays(){
        mPopUpSelectedDate = mPickHolidayCalendar.getSelectedDate();
        List<Date> rangeDate = mPickHolidayCalendar.getSelectedDates();
        if(mPopUpSelectedDate!=null && mPickHolidayCalendar!=null){
            ArrayList<Date> listOldHolidays = new ArrayList<Date>();
            listOldHolidays.add(mPopUpSelectedDate);
            mPickHolidayCalendar.highlightDates(listOldHolidays);
        }


        Holiday holiday = new Holiday();
        if(((RadioButton)findViewById(R.id.dialog_radio_start_first_day)).isChecked()){
            holiday.setBeginDayDateStart(true);
        }else{
            rangeDate.get(0).setHours(14);
        }
        if(((RadioButton)findViewById(R.id.dialog_radio_end_last_day)).isChecked()){
            holiday.setEndDayDateEnd(true);
        }else{
            rangeDate.get(rangeDate.size() - 1).setHours(12);
        }

        holiday.setDateStart(rangeDate.get(0));
        holiday.setDateEnd(rangeDate.get(rangeDate.size() - 1));
        holiday.setListDateHoliday(rangeDate);
        mHoldayList.add(holiday);
        SharedPreferenceManager.getInstance().setHolidayArrayList(mHoldayList);
        SharedPreferenceManager.getInstance().savePrefsData();

        if(rangeDate!=null && mPickHolidayCalendar!=null){
            mPickHolidayCalendar.highlightDates(rangeDate);
        }
    }
}
