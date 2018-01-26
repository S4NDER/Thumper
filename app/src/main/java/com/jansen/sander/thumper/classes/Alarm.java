package com.jansen.sander.thumper.classes;

import com.google.gson.annotations.Expose;

/**
 * Created by Sander on 6/10/2017.
 */

public class Alarm {
    @Expose
    private String action;

    private void setAction(String action) {
        this.action = action;
    }

    public void setAlarmOn (){
        setAction("on");
    }

    public void setAlarmOff(){
        setAction("off");
    }

    public void setToggleAlarm(){
        setAction("toggle");
    }
}
