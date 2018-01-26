package com.jansen.sander.thumper.classes;

import com.google.gson.annotations.Expose;

/**
 * Created by Sander on 6/10/2017.
 */

public class BatteryInformation {

    @Expose
    private Double battery_voltage;

    @Expose
    private String status;

    public Double getBattery_voltage() {
        return battery_voltage;
    }

    public String getStatus(){
        return status;
    }

}
