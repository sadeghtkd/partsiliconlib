
package com.partsilicon.partsiliconlib.dialog.pojo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Expire {

    @SerializedName("__type")
    @Expose
    private String type;
    @SerializedName("iso")
    @Expose
    private String iso;

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
