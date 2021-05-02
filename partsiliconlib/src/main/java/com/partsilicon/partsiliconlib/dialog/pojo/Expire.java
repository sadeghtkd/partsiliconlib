
package com.partsilicon.partsiliconlib.dialog.pojo;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Expire implements Parcelable {

    @SerializedName("__type")
    @Expose
    private String type;
    @SerializedName("iso")
    @Expose
    private String iso;

    protected Expire(Parcel in) {
        type = in.readString();
        iso = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeString(iso);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Expire> CREATOR = new Creator<Expire>() {
        @Override
        public Expire createFromParcel(Parcel in) {
            return new Expire(in);
        }

        @Override
        public Expire[] newArray(int size) {
            return new Expire[size];
        }
    };

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

}
