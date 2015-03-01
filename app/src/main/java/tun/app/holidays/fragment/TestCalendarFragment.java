package tun.app.holidays.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.squareup.timessquare.CalendarCellDecorator;
import com.squareup.timessquare.CalendarPickerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import tun.app.holidays.data.SharedPreferenceManager;
import tun.app.holidays.model.Holiday;
import tun.app.holidays.utils.CalendarUtils;
import tun.app.holidays.R;
import tun.app.holidays.square.SampleDecorator;
import tun.app.library.utils.JFLog;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link tun.app.holidays.fragment.TestCalendarFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link tun.app.holidays.fragment.TestCalendarFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class TestCalendarFragment extends Fragment implements View.OnClickListener,CalendarPickerView.OnInvalidDateSelectedListener,CalendarPickerView.OnDateSelectedListener{
    // TODO: Rename parameter arguments, choose names that match

    private static String TAG = TestCalendarFragment.class.getName();

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /************ StartDeclare Views ************/
    private OnFragmentInteractionListener mListener;
    private AlertDialog theDialog;
    private CalendarPickerView calendarDialogView;
    private LinearLayout dialogView;
    private Button mButtPopUP;
    private  CalendarPickerView mDisplayOnlyCalendar;
    /************* End Declare Views ***********/

    /************ Start Declare Variables ************/
    private Date mPopUpSelectedDate;
    private ArrayList<Holiday> mHoldayList;
    /************* End Declare Variables ***********/
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CalendarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TestCalendarFragment newInstance(String param1, String param2) {
        TestCalendarFragment fragment = new TestCalendarFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public TestCalendarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        mHoldayList = SharedPreferenceManager.getInstance().getHolidayArrayList();

        //Test Commit Git Android Studio
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fgView = inflater.inflate(R.layout.fragment_calendar, container, false);

        mButtPopUP =(Button)fgView.findViewById(R.id.fg_butt_popup_calendar);
        mButtPopUP.setOnClickListener(this);
        final Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 2);

        final Calendar lastYear = Calendar.getInstance();
        lastYear.add(Calendar.YEAR, -1);

        mDisplayOnlyCalendar = (CalendarPickerView) fgView.findViewById(R.id.calendar_view);
        List<CalendarCellDecorator> decorators = new ArrayList<CalendarCellDecorator>();
        decorators.add(new SampleDecorator());
        mDisplayOnlyCalendar.setDecorators(decorators);
        mDisplayOnlyCalendar.init(lastYear.getTime(), nextYear.getTime()) //
                .inMode(CalendarPickerView.SelectionMode.SINGLE) //
                .withSelectedDate(new Date())
                .displayOnly();

        mDisplayOnlyCalendar.setDateSelectableFilter(filter);
        mDisplayOnlyCalendar.setOnDateSelectedListener(this);
        mDisplayOnlyCalendar.setOnInvalidDateSelectedListener(this);

        for(int i =0; i<mHoldayList.size();i++){
            ArrayList<Date> listHolidays = new ArrayList<Date>();
            listHolidays.addAll(mHoldayList.get(i).getListDateHoliday());

            mDisplayOnlyCalendar.highlightDates(listHolidays);
        }
        //calendar.setCellClickInterceptor(this);
        return fgView;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void onPopUpCalClick() {

        dialogView = (LinearLayout) getActivity().getLayoutInflater().inflate(R.layout.dialog, null, false);
        calendarDialogView =(CalendarPickerView) dialogView.findViewById(R.id.calendar_view);

        calendarDialogView.setDateSelectableFilter(filter);

        final Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 2);

        final Calendar lastYear = Calendar.getInstance();
        lastYear.add(Calendar.YEAR, -1);
        calendarDialogView.init(lastYear.getTime(), nextYear.getTime()) //
                .withSelectedDate(new Date())
                .inMode(CalendarPickerView.SelectionMode.RANGE);
        int dialogTheme ;
        if (Build.VERSION.SDK_INT < 11){
            dialogTheme = android.R.style.Theme_DeviceDefault_Dialog;
        }else{
            dialogTheme = android.R.style.Theme_Holo_Light_Dialog;
        }
        theDialog =
                new AlertDialog.Builder(getActivity())
                        .setView(dialogView)
                        .setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                            @Override public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();

                            }
                        })
                        .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int j) {
                                dialogInterface.dismiss();
                                mPopUpSelectedDate = calendarDialogView.getSelectedDate();
                                List<Date> rangeDate = calendarDialogView.getSelectedDates();
                                if(mPopUpSelectedDate!=null && mDisplayOnlyCalendar!=null){
                                    ArrayList<Date> listOldHolidays = new ArrayList<Date>();
                                    listOldHolidays.add(mPopUpSelectedDate);
                                    mDisplayOnlyCalendar.highlightDates(listOldHolidays);
                                }


                                Holiday holiday = new Holiday();
                                if(((RadioButton)theDialog.findViewById(R.id.dialog_radio_start_first_day)).isChecked()){
                                    holiday.setBeginDayDateStart(true);
                                }else{
                                    rangeDate.get(0).setHours(14);
                                }
                                if(((RadioButton)theDialog.findViewById(R.id.dialog_radio_end_last_day)).isChecked()){
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

                                if(rangeDate!=null && mDisplayOnlyCalendar!=null){
                                    mDisplayOnlyCalendar.highlightDates(rangeDate);
                                }

                            }
                        })
                        .create();
        theDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                JFLog.d(TAG, "onShow: fix the dimens!");
                calendarDialogView.fixDialogDimens();
            }
        });
        calendarDialogView.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {
                dialogView.findViewById(R.id.dialog_layout_param_last_holiday).setVisibility(View.VISIBLE);
                dialogView.findViewById(R.id.dialog_layout_param_holiday).setVisibility(View.VISIBLE);
            }

            @Override
            public void onDateUnselected(Date date) {

            }
        });
        theDialog.show();
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onInvalidDateSelected(Date date) {
        JFLog.d(TAG,date.toString());
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
        if(view==mButtPopUP){
            onPopUpCalClick();
        }

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
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
