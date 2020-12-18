package com.androidarchitecture.learn.noteapplication.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Crop implements Serializable {
    @PrimaryKey
    @NonNull
    @SerializedName("crop")
    private  String crop;
    @SerializedName("cropYear")
    private  String cropYear;
    @SerializedName("status")
    private  String status;

    public Crop(String crop, String cropYear, String status) {
        this.crop = crop;
        this.cropYear = cropYear;
        this.status = status;
    }

    public String getCrop() {
        return crop;
    }

    public void setCrop(String crop) {
        this.crop = crop;
    }

    public String getCropYear() {
        return cropYear;
    }

    public void setCropYear(String cropYear) {
        this.cropYear = cropYear;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Crop{" +
                "crop='" + crop + '\'' +
                ", cropYear='" + cropYear + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
