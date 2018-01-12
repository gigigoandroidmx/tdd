package com.gigigoandroidmx.people.data.entity;

import com.gigigoandroidmx.people.common.net.error.ResponseError;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author Omar Bacilio - January 11, 2018
 * @version 0.0.1
 * @since 0.0.1
 */
public class SimpleResponseError implements Serializable, ResponseError {

    @SerializedName("error")
    private String error;

    @Override
    public String getError() {
        return error;
    }
}