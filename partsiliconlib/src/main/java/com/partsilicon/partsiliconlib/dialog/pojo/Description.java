
package com.partsilicon.partsiliconlib.dialog.pojo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Description {

    @SerializedName("lang")
    @Expose
    private String lang;
    @SerializedName("value")
    @Expose
    private String value;

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
