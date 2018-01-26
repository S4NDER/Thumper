package com.jansen.sander.thumper.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.jansen.sander.thumper.MainActivity;
import com.jansen.sander.thumper.R;
import com.jansen.sander.thumper.classes.Effect;
import com.jansen.sander.thumper.classes.NeoPixelColor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NeoPixelFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NeoPixelFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NeoPixelFragment extends android.support.v4.app.Fragment {

    private SeekBar seekBarRed;
    private SeekBar seekBarGreen;
    private SeekBar seekBarBlue;
    private SeekBar seekBarDelay;

    private TextView labelMs;

    private Switch swStrobe;
    private Switch swRGB;

    private ColorDrawable colorDrawable = new ColorDrawable();
    private String hexColor;

    private NeoPixelColor neoPixelColor = new NeoPixelColor();
    private Effect effects = new Effect();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "frag_Neo";
    private static final String ARG_PARAM2 = "param2";
    private int minSeekBarDelay = 10;


    private OnFragmentInteractionListener mListener;

    public NeoPixelFragment() {
        // Required empty public constructor
    }

    public SeekBar.OnSeekBarChangeListener seekBarListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if(seekBar.getId() == seekBarDelay.getId()) {
                labelMs.setText(minSeekBarDelay + seekBar.getProgress()+" ms");
            } else {
                setNeoPixelColor(seekBarRed.getProgress(), seekBarGreen.getProgress(), seekBarBlue.getProgress());
                changeActionBarColor();

            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {


        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            if(seekBar.getId() == seekBarDelay.getId()) {
                setDelay(seekBarDelay.getProgress());
                labelMs.setText(minSeekBarDelay + seekBar.getProgress()+" ms");
            }else{
                onToggleStrobeOnOffSwitch();
            }

        }
    };

    public Switch.OnCheckedChangeListener switchListener = new Switch.OnCheckedChangeListener(){

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(buttonView.getId() == swStrobe.getId()){
                onToggleStrobeOnOffSwitch();
            } else if(buttonView.getId() == swRGB.getId()){
               cycleRGBColors();
            }
        }
    };

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment NeoPixelFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NeoPixelFragment newInstance(int param1) {
        NeoPixelFragment fragment = new NeoPixelFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_neo_pixel, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        seekBarRed = getView().findViewById(R.id.seekBarRed);
        seekBarGreen = getView().findViewById(R.id.seekBarGreen);
        seekBarBlue = getView().findViewById(R.id.seekBarBlue);
        seekBarDelay = getView().findViewById(R.id.seekBarDelay);
        swStrobe = getView().findViewById(R.id.swOnOff);
        labelMs = getView().findViewById(R.id.txtLabelMs);
        swRGB = getView().findViewById(R.id.swRGB);

        seekBarRed.setOnSeekBarChangeListener(seekBarListener);
        seekBarGreen.setOnSeekBarChangeListener(seekBarListener);
        seekBarBlue.setOnSeekBarChangeListener(seekBarListener);
        seekBarDelay.setOnSeekBarChangeListener(seekBarListener);
        swStrobe.setOnCheckedChangeListener(switchListener);
        swRGB.setOnCheckedChangeListener(switchListener);
        onToggleStrobeOnOffSwitch();
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

    private void setNeoPixelColor (int red, int green, int blue){
        neoPixelColor.setRGB(red, green, blue);
        final Call<NeoPixelColor> neoPixelColorCall = ((MainActivity)getActivity()).getRetrofitService().setColor(0, neoPixelColor);
        neoPixelColorCall.enqueue(new Callback<NeoPixelColor>() {
            @Override
            public void onResponse(Call<NeoPixelColor> call, Response<NeoPixelColor> response) {

            }

            @Override
            public void onFailure(Call<NeoPixelColor> call, Throwable t) {

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

    private void changeActionBarColor (){
        hexColor = String.format( "#%02x%02x%02x", seekBarRed.getProgress(), seekBarGreen.getProgress(), seekBarBlue.getProgress() );
        colorDrawable.setColor(Color.parseColor(hexColor));
        ((AppCompatActivity)getActivity()).getSupportActionBar().setBackgroundDrawable(colorDrawable);
    }

    private void setDelay(int progress){
        if(swStrobe.isChecked()){
            effects.setRGB(seekBarRed.getProgress(), seekBarGreen.getProgress(), seekBarBlue.getProgress());
            effects.setDelay(progress+minSeekBarDelay);
            final Call<NeoPixelColor> effectCall = ((MainActivity)getActivity()).getRetrofitService().setStrobe(1, effects);
            effectCall.enqueue(new Callback<NeoPixelColor>() {
                @Override
                public void onResponse(Call<NeoPixelColor> call, Response<NeoPixelColor> response) {

                }

                @Override
                public void onFailure(Call<NeoPixelColor> call, Throwable t) {

                }
            });
        }
        try {
            Thread.sleep(50); //1000 milliseconds is one second.
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    private void onToggleStrobeOnOffSwitch(){
        if(swStrobe.isChecked()){
            seekBarDelay.setEnabled(true);
            setDelay(seekBarDelay.getProgress());
        } else {
            labelMs.setText(minSeekBarDelay +seekBarDelay.getProgress()+" ms");
            seekBarDelay.setEnabled(false);
            setNeoPixelColor(seekBarRed.getProgress(), seekBarGreen.getProgress(), seekBarBlue.getProgress());
        }
    }

    private void cycleRGBColors(){

        new Thread(new Runnable() {
            public void run() {
                rgbLoop:
                while (swRGB.isChecked()) {
                    int red = 0;
                    int green = 0;
                    int blue = 0;
                    // a potentially  time consuming task
                    for (red = 0; red <= 255; red++) {
                        if (!swRGB.isChecked()){
                            Log.e("bttn", "break");
                            break rgbLoop;
                        }
                        setNeoPixelColor(red, green, blue);

                        Log.e("Color: ", red + ", " + green + ", " + blue);
                        if (red == 255) {
                            red = 255;
                        }
                    }

                    if (!swRGB.isChecked()){
                        Log.e("bttn", "break");
                        break rgbLoop;
                    }
                    for (green = 0; green <= 255; green++) {
                        red--;
                        if (!swRGB.isChecked()){
                            Log.e("bttn", "break");
                            break rgbLoop;
                        }
                        setNeoPixelColor(red, green, blue);
                        Log.e("Color: ", red + ", " + green + ", " + blue);
                        if (green == 255) {
                            green = 255;
                        }
                    }

                    if (!swRGB.isChecked()){
                        Log.e("bttn", "break");
                        break rgbLoop;
                    }
                    for (blue = 0; blue <= 255; blue++) {
                        if (!swRGB.isChecked()){
                            Log.e("bttn", "break");
                            break rgbLoop;
                        }
                        green--;
                        setNeoPixelColor(red, green, blue);
                        Log.e("Color: ", red + ", " + green + ", " + blue);
                    }
                }
            }
        }).start();

    }

}
