package tun.app.holidays.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import tun.app.holidays.R;
import tun.app.holidays.data.SharedPreferenceManager;
import tun.app.holidays.model.Holiday;
import tun.app.holidays.model.HolidayType;
import tun.app.holidays.utils.CalendarUtils;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BalanceFragment.OnFragmentBalanceListener} interface
 * to handle interaction events.
 * Use the {@link BalanceFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class BalanceFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentBalanceListener mListener;
    private TextView mTxtBalanceCp;
    private TextView mTxtBalanceCpUsed;

    private TextView mTxtBalanceRtt;
    private TextView mTxtBalanceRttUsed;

    private TextView mTxtBalanceHoliday;
    private TextView mTxtBalanceHolidayUsed;

    private ArrayList<Holiday> mListHolidays;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BalanceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BalanceFragment newInstance(String param1, String param2) {
        BalanceFragment fragment = new BalanceFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public BalanceFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


        mListHolidays = SharedPreferenceManager.getInstance().getHolidayArrayList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fgView = inflater.inflate(R.layout.fragment_holiday_balance, container, false);

        mTxtBalanceCp = (TextView)fgView.findViewById(R.id.balance_nbr_cp_current);
         mTxtBalanceCpUsed = (TextView)fgView.findViewById(R.id.balance_nbr_cp_used);
         mTxtBalanceRtt = (TextView)fgView.findViewById(R.id.balance_nbr_rtt_current);
        mTxtBalanceRttUsed = (TextView)fgView.findViewById(R.id.balance_nbr_rtt_used);
        mTxtBalanceHoliday = (TextView)fgView.findViewById(R.id.balance_nbr_total_current);
        mTxtBalanceHolidayUsed = (TextView)fgView.findViewById(R.id.balance_nbr_total_used);

        updateView();

        return fgView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentBalanceListener) activity;
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





    private void updateView(){

        int balanceCP = CalendarUtils.getBalanceCurrentCP(mListHolidays)-mListHolidays.size();
        int balanceCPUsed =mListHolidays.size()+ CalendarUtils.getBalanceUsed(mListHolidays,HolidayType.HOLIDAY_PAYED);
        mTxtBalanceCp.setText(""+balanceCP);
        mTxtBalanceCpUsed.setText(""+balanceCPUsed);

        int balanceRTT = CalendarUtils.getBalanceCurrentRtt(mListHolidays);
        int balanceRTTUsed = CalendarUtils.getBalanceUsed(mListHolidays,HolidayType.HOLIDAY_RTT);
        mTxtBalanceRtt.setText(""+ balanceRTT);
        mTxtBalanceRttUsed.setText(""+balanceRTTUsed);


        mTxtBalanceHoliday.setText(""+(balanceCP+balanceRTT));

        mTxtBalanceHolidayUsed.setText(""+(balanceCPUsed+balanceRTTUsed));

    }
    public interface OnFragmentBalanceListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
