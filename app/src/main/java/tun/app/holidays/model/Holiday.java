package tun.app.holidays.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by nourjbel on 28/02/2015.
 */
public class Holiday implements Serializable{

    private Date dateStart;

    private Date dateEnd;

    private List<Date> listDateHoliday;

    private boolean isBeginDayDateStart;

    private boolean isEndDayDateEnd;

    private HolidayType holidayType;

    private double nbrDays;

    private String description;

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public boolean isBeginDayDateStart() {
        return isBeginDayDateStart;
    }

    public void setBeginDayDateStart(boolean isBeginDayDateStart) {
        this.isBeginDayDateStart = isBeginDayDateStart;
    }

    public boolean isEndDayDateEnd() {
        return isEndDayDateEnd;
    }

    public void setEndDayDateEnd(boolean isEndDayDateEnd) {
        this.isEndDayDateEnd = isEndDayDateEnd;
    }

    public HolidayType getHolidayType() {
        return holidayType;
    }

    public void setHolidayType(HolidayType holidayType) {
        this.holidayType = holidayType;
    }

    public double getNbrDays() {
        return nbrDays;
    }

    public void setNbrDays(double nbrDays) {
        this.nbrDays = nbrDays;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Date> getListDateHoliday() {
        if(listDateHoliday==null)
            listDateHoliday = new ArrayList<Date>();
        return listDateHoliday;
    }

    public void setListDateHoliday(List<Date> listDateHoliday) {
        this.listDateHoliday = listDateHoliday;
    }

    @Override
    public String toString() {
        return "Holiday{" +
                "dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                ", isBeginDayDateStart=" + isBeginDayDateStart +
                ", isEndDayDateEnd=" + isEndDayDateEnd +
                ", holidayType=" + holidayType +
                ", nbrDays=" + nbrDays +
                ", description='" + description + '\'' +
                '}';
    }



}
