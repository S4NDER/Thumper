package com.jansen.sander.thumper.classes;

import com.google.gson.annotations.Expose;

/**
 * Created by Sander on 6/10/2017.
 */

public class ShiftEffect extends Effect {

    @Expose
    protected int groupsize;


    public void setGroupsize(int groupsize) {
        this.groupsize = groupsize;
    }
}
