
package com.partsilicon.partsiliconlib.dialog.pojo;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ButtonText implements Parcelable {

    @SerializedName("lang")
    @Expose
    private String lang;
    @SerializedName("value")
    @Expose
    private String value;

    protected ButtonText(Parcel in) {
        lang = in.readString();
        value = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(lang);
        dest.writeString(value);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ButtonText> CREATOR = new Creator<ButtonText>() {
        @Override
        public ButtonText createFromParcel(Parcel in) {
            return new ButtonText(in);
        }

        @Override
        public ButtonText[] newArray(int size) {
            return new ButtonText[size];
        }
    };

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
