package com.jansen.sander.thumper.classes;

import com.google.gson.annotations.Expose;

/**
 * Created by Sander on 6/10/2017.
 */

public class Effect extends NeoPixelColor{

    @Expose
    protected int delay;


    public void setDelay(int delay) {
        this.delay = delay;
    }

}
