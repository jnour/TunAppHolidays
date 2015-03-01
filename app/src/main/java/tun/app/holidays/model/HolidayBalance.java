package tun.app.holidays.model;

/**
 * Created by nourjbel on 28/02/2015.
 */
public class HolidayBalance {


    private double balanceAcquired;

    private double balanceUsed;

    private double nbrHolidayByMonth;

    private HolidayType holidayType;

    public double getNbrHolidayByMonth() {
        return nbrHolidayByMonth;
    }

    public void setNbrHolidayByMonth(double nbrHolidayByMonth) {
        this.nbrHolidayByMonth = nbrHolidayByMonth;
    }

    public double getBalanceAcquired() {
        return balanceAcquired;
    }

    public void setBalanceAcquired(double balanceAcquired) {
        this.balanceAcquired = balanceAcquired;
    }

    public double getBalanceUsed() {
        return balanceUsed;
    }

    public void setBalanceUsed(double balanceUsed) {
        this.balanceUsed = balanceUsed;
    }

    public HolidayType getHolidayType() {
        return holidayType;
    }

    public void setHolidayType(HolidayType holidayType) {
        this.holidayType = holidayType;
    }

    @Override
    public String toString() {
        return "HolidayBalance{" +
                "balanceAcquired=" + balanceAcquired +
                ", balanceUsed=" + balanceUsed +
                ", holidayType=" + holidayType +
                '}';
    }
}
