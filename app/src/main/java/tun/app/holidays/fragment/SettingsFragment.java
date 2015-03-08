package tun.app.holidays.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import tun.app.holidays.R;
import tun.app.holidays.data.SharedPreferenceManager;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link tun.app.holidays.fragment.SettingsFragment.OnFragmentSettingsListener interface
 * to handle interaction events.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class SettingsFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentSettingsListener mListener;

    private TextView mTxtHoliCP;
    private Button mButtMoreHoliCP;
    private Button mButtLessHoliCP;

    private TextView mTxtHoliRTT;
    private Button mButtMoreHoliRTT;
    private Button mButtLessHoliRTT;

    private TextView mTxtHoliMater;
    private Button mButtMoreHoliMater;
    private Button mButtLessHoliMater;

    private TextView mTxtHoliCPater;
    private Button mButtMoreHoliPater;
    private Button mButtLessHoliPater;

    private LinearLayout mLayoutEdit;
    private Button mButtConfirm;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);



        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fgView = inflater.inflate(R.layout.fragment_settings, container, false);

        mTxtHoliCP =(TextView)fgView.findViewById(R.id.settings_txt_nbr_cp);
        mButtLessHoliCP = (Button)fgView.findViewById(R.id.settings_butt_holi_payed_less);
        mButtLessHoliCP.setOnClickListener(this);
        mButtMoreHoliCP = (Button)fgView.findViewById(R.id.settings_butt_holi_payed_more);
        mButtMoreHoliCP.setOnClickListener(this);


        mTxtHoliRTT =(TextView)fgView.findViewById(R.id.settings_txt_nbr_rtt);
        mButtLessHoliRTT = (Button)fgView.findViewById(R.id.settings_butt_holi_rtt_less);
        mButtLessHoliRTT.setOnClickListener(this);
        mButtMoreHoliRTT = (Button)fgView.findViewById(R.id.settings_butt_holi_rtt_more);
        mButtMoreHoliRTT.setOnClickListener(this);



        mLayoutEdit = (LinearLayout)fgView.findViewById(R.id.settings_layout_butt_edit);

        mButtConfirm =(Button)fgView.findViewById(R.id.settings_butt_confirm);
        mButtConfirm.setOnClickListener(this);

        updateView();


        return fgView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentSettingsChanged(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentSettingsListener) activity;
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
    public void onClick(View view) {
        if(view ==mButtMoreHoliCP){
            int nbr = Integer.parseInt(mTxtHoliCP.getText().toString());
                mTxtHoliCP.setText(""+(nbr+1));


        }
        if(view ==mButtLessHoliCP){
            int nbr = Integer.parseInt(mTxtHoliCP.getText().toString());
            if(nbr>0){
                mTxtHoliCP.setText("" + (nbr - 1));
            }
        }
        if(view ==mButtMoreHoliRTT){
            int nbr = Integer.parseInt(mTxtHoliRTT.getText().toString());
            mTxtHoliRTT.setText("" + (nbr + 1));
        }
        if(view ==mButtLessHoliRTT){
            int nbr = Integer.parseInt(mTxtHoliRTT.getText().toString());
            if(nbr>0){
                mTxtHoliRTT.setText("" + (nbr - 1));
            }
        }


        if (view==mButtConfirm){
            int nbrRtt = Integer.parseInt(mTxtHoliRTT.getText().toString());
            int nbrCP = Integer.parseInt(mTxtHoliCP.getText().toString());
            SharedPreferenceManager.getInstance().setNbrPayedHoliday(nbrCP);
            SharedPreferenceManager.getInstance().setNbrRttHoliday(nbrRtt);
            SharedPreferenceManager.getInstance().savePrefsData();
            mLayoutEdit.setVisibility(View.GONE);
        }else{
            mLayoutEdit.setVisibility(View.VISIBLE);
        }


    }

    private void updateView(){

        mTxtHoliRTT.setText(""+SharedPreferenceManager.getInstance().getNbrRttHoliday());
        mTxtHoliCP.setText(""+SharedPreferenceManager.getInstance().getNbrPayedHoliday());
    }
    public interface OnFragmentSettingsListener {
        // TODO: Update argument type and name
        public void onFragmentSettingsChanged(Uri uri);
    }

}
