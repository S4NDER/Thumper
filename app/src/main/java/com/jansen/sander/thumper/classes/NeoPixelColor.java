package com.jansen.sander.thumper.classes;

import com.google.gson.annotations.Expose;

/**
 * Created by Sander on 4/10/2017.
 */

public class NeoPixelColor {

    @Expose
    protected int red;

    @Expose
    protected int green;

    @Expose
    protected int blue;

    public void setRGB(int red, int green, int blue){
        setRed(red);
        setGreen(green);
        setBlue(blue);
    }

    public void setRed(int red) {
        this.red = red;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }
}
