package com.jansen.sander.thumper.classes;

import com.google.gson.annotations.Expose;

/**
 * Created by Sander on 4/10/2017.
 */

public class NeoPixelString {
    @Expose
    private String string_id;

    @Expose
    private int number_of_pixels;

    @Expose
    private int number_of_string;

    @Expose
    private int[] string_ids;

    @Expose
    private String status;

    public String getString_id() {
        return string_id;
    }

    public int getNumber_of_pixels() {
        return number_of_pixels;
    }

    public int getNumber_of_string() {
        return number_of_string;
    }

    public int[] getString_ids() {
        return string_ids;
    }

    public String getStatus() {
        return status;
    }
}
