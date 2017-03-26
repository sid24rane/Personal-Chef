package com.pchef.cc.personalchef;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by sarthak on 3/25/2017.
 */

public class Recipe implements Parcelable{

    private String name;
    private String img;

    public Recipe()
    {

    }

    public Recipe(String name, String img, String desc, String category, String cusine, String[] ingridient, Nutrition nutrition, String url, int rating) {
        this.name = name;
        this.img = img;
        this.desc = desc;
        this.category = category;
        this.cusine = cusine;
        this.ingridient = ingridient;
        this.nutrition = nutrition;
        this.url = url;
        this.rating=rating;
    }

    private String desc;
    private String category;
    private String cusine;
    private String [] ingridient;

    protected Recipe(Parcel in) {
        name = in.readString();
        img = in.readString();
        desc = in.readString();
        category = in.readString();
        cusine = in.readString();
        ingridient = in.createStringArray();
        rating = in.readInt();
        url = in.readString();
       // nutrition = in.readTypedObject(Nutrition);
    }


    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    private int rating;
    Nutrition nutrition;



    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCusine() {
        return cusine;
    }

    public void setCusine(String cusine) {
        this.cusine = cusine;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String[] getIngridient() {
        return ingridient;
    }

    public void setIngridient(String[] ingridient) {
        this.ingridient = ingridient;
    }

    public Nutrition getNutrition() {
        return nutrition;
    }

    public void setNutrition(Nutrition nutrition) {
        this.nutrition = nutrition;
    }

    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(img);
        dest.writeString(desc);
        dest.writeString(category);
        dest.writeString(cusine);
        dest.writeStringArray(ingridient);
        dest.writeInt(rating);
        dest.writeString(url);
    }
}
