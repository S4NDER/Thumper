package com.jansen.sander.thumper.classes;

import com.google.gson.annotations.Expose;

/**
 * Created by Sander on 6/10/2017.
 */

public class ErrorFlag {
    @Expose
    private boolean motor_speed;

    @Expose
    private boolean low_battery_threshold;

    @Expose
    private String status;

    public boolean isMotor_speed() {
        return motor_speed;
    }

    public boolean isLow_battery_threshold() {
        return low_battery_threshold;
    }

    public String getStatus() {
        return status;
    }
}
