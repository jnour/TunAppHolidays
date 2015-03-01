package tun.app.holidays.square;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;

import com.squareup.timessquare.CalendarCellDecorator;
import com.squareup.timessquare.CalendarCellView;
import com.squareup.timessquare.MonthCellDescriptor;

import java.util.Date;

import tun.app.holidays.R;
import tun.app.holidays.data.SharedPreferenceManager;
import tun.app.holidays.utils.CalendarUtils;
import tun.app.library.utils.JFLog;

public class SampleDecorator implements CalendarCellDecorator {
    @Override
    public void decorate(CalendarCellView cellView, Date date,Context context) {
        String dateString = Integer.toString(date.getDate());
        SpannableString string = new SpannableString(dateString + "\n");
        string.setSpan(new RelativeSizeSpan(0.5f), 0, dateString.length(),
                Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        cellView.setText(string);
        cellView.setRangeState(MonthCellDescriptor.RangeState.LAST);

        JFLog.e(SampleDecorator.class.getName(),""+date.getHours());
        boolean isInHoliday = false;
        for (int i =0;i<SharedPreferenceManager.getInstance().getHolidayArrayList().size();i++){
            if(SharedPreferenceManager.getInstance().getHolidayArrayList().get(i).getListDateHoliday().contains(date)){
                isInHoliday = true;
                break;
            }
        }
        if(isInHoliday&&date.getHours()==12 && cellView.isCurrentMonth()){
            cellView.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.rect_first_half_day));
        }else if(isInHoliday&&date.getHours()==14 && cellView.isCurrentMonth()){
            cellView.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.rect_last_half_day));
        }else if(isInHoliday && cellView.isCurrentMonth()) {
            cellView.setBackgroundColor(context.getResources().getColor(R.color.calendar_highlighted_day_bg));
        }else if(!isInHoliday&& CalendarUtils.isDateInWeekend(date)) {
            cellView.setBackgroundColor(Color.LTGRAY);
        }else if(!isInHoliday) {
            cellView.setBackgroundDrawable(null);
        }


    }
}
