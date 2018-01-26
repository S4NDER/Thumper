package com.jansen.sander.thumper.fragments;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.jansen.sander.thumper.MainActivity;
import com.jansen.sander.thumper.R;
import com.jansen.sander.thumper.classes.Effect;
import com.jansen.sander.thumper.classes.NeoPixelColor;
import com.jansen.sander.thumper.classes.Speed;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ControlFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ControlFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ControlFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private SeekBar seekbarSpeedLeft;
    private SeekBar seekbarSpeedRight;

    private Button bttnStopThumper;

    private Speed speed = new Speed();







    private OnFragmentInteractionListener mListener;

    public ControlFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ControlFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ControlFragment newInstance(String param1, String param2) {
        ControlFragment fragment = new ControlFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        seekbarSpeedLeft = getView().findViewById(R.id.seekbarSpeedLeft);
        seekbarSpeedRight = getView().findViewById(R.id.seekbarSpeedRight);
        bttnStopThumper = getView().findViewById(R.id.bttnStopThumper);

        bttnStopThumper.setOnClickListener(bttnListener);
        seekbarSpeedLeft.setOnSeekBarChangeListener(seekBarListener);
        seekbarSpeedRight.setOnSeekBarChangeListener(seekBarListener);
    }

    private void setSpeed (){
        speed.setRight_speed(((seekbarSpeedLeft.getProgress()-50)*(512/100)));
        speed.setLeft_speed(((seekbarSpeedRight.getProgress()-50)*(512/100)));

        final Call<Speed> speedCall = ((MainActivity)getActivity()).getRetrofitService().setSpeed(speed);
        speedCall.enqueue(new Callback<Speed>() {
            @Override
            public void onResponse(Call<Speed> call, Response<Speed> response) {

            }

            @Override
            public void onFailure(Call<Speed> call, Throwable t) {

            }
        });
        try {
            Thread.sleep(50); //1000 milliseconds is one second.
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public SeekBar.OnSeekBarChangeListener seekBarListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            setSpeed();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            setSpeed();
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            seekbarSpeedLeft.setProgress(50);
            seekbarSpeedRight.setProgress(50);
            setSpeed();
        }
    };

    public Button.OnClickListener bttnListener = new Button.OnClickListener(){

        @Override
        public void onClick(View v) {

            if(seekbarSpeedRight.getProgress() < 50 && seekbarSpeedLeft.getProgress() < 50){


            }
            if(seekbarSpeedRight.getProgress() > 50 && seekbarSpeedLeft.getProgress() > 50){

            }

            if(seekbarSpeedRight.getProgress() > 50 && seekbarSpeedLeft.getProgress() < 50){

            }
            if(seekbarSpeedRight.getProgress() < 50 && seekbarSpeedLeft.getProgress() > 50){

            }

            speed.setRight_speed(((seekbarSpeedLeft.getProgress()-50)*(512/100)));
            speed.setLeft_speed(((seekbarSpeedRight.getProgress()-50)*(512/100)));


            seekbarSpeedLeft.setProgress(50);
            seekbarSpeedRight.setProgress(50);
            setSpeed();
        }
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_control, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

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
        void onFragmentInteraction(Uri uri);
    }
}
