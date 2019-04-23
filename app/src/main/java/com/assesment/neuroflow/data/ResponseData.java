package com.assesment.neuroflow.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseData {

    @SerializedName(value="males", alternate={"females"})
    @Expose
    private List<Gender> gender;

    public ResponseData(List<Gender> gender) {
        this.gender = gender;
    }

    public List<Gender> getGender() {
        return gender;
    }

    public void setGender(List<Gender> gender) {
        this.gender = gender;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [males = "+gender+"]";
    }


}
