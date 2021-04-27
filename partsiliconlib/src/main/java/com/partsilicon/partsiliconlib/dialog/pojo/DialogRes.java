
package com.partsilicon.partsiliconlib.dialog.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class DialogRes {

    @SerializedName("result")
    @Expose
    private Result result;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

}
