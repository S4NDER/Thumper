package com.jansen.sander.thumper.classes;

import com.google.gson.annotations.Expose;

/**
 * Created by Sander on 6/10/2017.
 */

public class Speed {
    @Expose
    private int left_speed;

    @Expose
    private int right_speed;

    public void setLeft_speed(int left_speed) {
        this.left_speed = left_speed;
    }

    public void setRight_speed(int right_speed) {
        this.right_speed = right_speed;
    }
}
