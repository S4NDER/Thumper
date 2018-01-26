package com.jansen.sander.thumper.interfaces;

import com.jansen.sander.thumper.classes.Alarm;
import com.jansen.sander.thumper.classes.BatteryInformation;
import com.jansen.sander.thumper.classes.Effect;
import com.jansen.sander.thumper.classes.ErrorFlag;
import com.jansen.sander.thumper.classes.NeoPixelColor;
import com.jansen.sander.thumper.classes.NeoPixelString;
import com.jansen.sander.thumper.classes.ShiftEffect;
import com.jansen.sander.thumper.classes.Speed;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Sander on 23/10/2017.
 */

public interface CommunicationService {
    String ROOT_PATH = "neopixels";
    String STRINGS_PATH = "/strings";
    String EFFECTS_PATH = "/effects";

    @GET("batteryvoltage")
    Call<BatteryInformation> getBatteryData();

    @GET("errors")
    Call<ErrorFlag> getErrorFlags();

    @GET(ROOT_PATH + STRINGS_PATH +"/{id}")
    Call<NeoPixelString> getNumberOfPixels(
            @Path("id") int id
    );

    @GET(ROOT_PATH + STRINGS_PATH)
    Call<NeoPixelString> getNumberOfAttachedPixels(
    );



    @POST(ROOT_PATH + STRINGS_PATH +"/{id}")
    Call<NeoPixelColor> setColor(
            @Path("id") int id,
            @Body NeoPixelColor color
    );

    @POST(ROOT_PATH + EFFECTS_PATH +"/strobe/{id}")
    Call<NeoPixelColor> setStrobe(
            @Path("id") int id,
            @Body Effect strobe
    );

    @POST(ROOT_PATH + EFFECTS_PATH +"/shift/{id}")
    Call<NeoPixelColor> setShift(
            @Path("id") int id,
            @Body ShiftEffect shift
    );

    @POST("speed")
    Call<Speed> setSpeed(
            @Body Speed speed
    );

    @POST("alarm")
    Call<Alarm> setAlarm(
            @Body Alarm alarm
    );
}
