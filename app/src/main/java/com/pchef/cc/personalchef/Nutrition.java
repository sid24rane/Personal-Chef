package com.pchef.cc.personalchef;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sarthak on 3/25/2017.
 */

public class Nutrition implements Parcelable{
    protected Nutrition(Parcel in) {
        carbohydrateContent = in.readString();
        cholesterolContent = in.readString();
        fatContent = in.readString();
        fiberContent = in.readString();
        proteinContent = in.readString();
        saturatedFatContent = in.readString();
        sodiumContent = in.readString();
        sugarContent = in.readString();
    }

    public static final Creator<Nutrition> CREATOR = new Creator<Nutrition>() {
        @Override
        public Nutrition createFromParcel(Parcel in) {
            return new Nutrition(in);
        }

        @Override
        public Nutrition[] newArray(int size) {
            return new Nutrition[size];
        }
    };

    public String getCarbohydrateContent() {
        return carbohydrateContent;
    }

    public void setCarbohydrateContent(String carbohydrateContent) {
        this.carbohydrateContent = carbohydrateContent;
    }

    public String getCholesterolContent() {
        return cholesterolContent;
    }

    public void setCholesterolContent(String cholesterolContent) {
        this.cholesterolContent = cholesterolContent;
    }

    public String getFatContent() {
        return fatContent;
    }

    public void setFatContent(String fatContent) {
        this.fatContent = fatContent;
    }

    public String getFiberContent() {
        return fiberContent;
    }

    public void setFiberContent(String fiberContent) {
        this.fiberContent = fiberContent;
    }

    public String getProteinContent() {
        return proteinContent;
    }

    public void setProteinContent(String proteinContent) {
        this.proteinContent = proteinContent;
    }

    public String getSaturatedFatContent() {
        return saturatedFatContent;
    }

    public void setSaturatedFatContent(String saturatedFatContent) {
        this.saturatedFatContent = saturatedFatContent;
    }

    public String getSodiumContent() {
        return sodiumContent;
    }

    public void setSodiumContent(String sodiumContent) {
        this.sodiumContent = sodiumContent;
    }

    public String getSugarContent() {
        return sugarContent;
    }

    public void setSugarContent(String sugarContent) {
        this.sugarContent = sugarContent;
    }

    public Nutrition(String carbohydrateContent, String cholesterolContent, String fatContent, String fiberContent, String proteinContent, String saturatedFatContent, String sodiumContent, String sugarContent) {
        this.carbohydrateContent = carbohydrateContent;
        this.cholesterolContent = cholesterolContent;
        this.fatContent = fatContent;
        this.fiberContent = fiberContent;
        this.proteinContent = proteinContent;
        this.saturatedFatContent = saturatedFatContent;
        this.sodiumContent = sodiumContent;
        this.sugarContent = sugarContent;
    }

    public Nutrition()
    {}
    String carbohydrateContent, cholesterolContent, fatContent, fiberContent, proteinContent, saturatedFatContent, sodiumContent ,sugarContent;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(carbohydrateContent);
        dest.writeString(cholesterolContent);
        dest.writeString(fatContent);
        dest.writeString(fiberContent);
        dest.writeString(proteinContent);
        dest.writeString(saturatedFatContent);
        dest.writeString(sodiumContent);
        dest.writeString(sugarContent);
    }
}
