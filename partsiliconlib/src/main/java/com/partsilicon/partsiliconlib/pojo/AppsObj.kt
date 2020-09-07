package com.partsilicon.partsiliconlib.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AppsObj {
    @SerializedName("res")
    @Expose
    private var res: List<App>? = null

    fun getres(): List<App>? {
        return res
    }

    fun setres(res: List<App>?) {
        this.res = res
    }
}