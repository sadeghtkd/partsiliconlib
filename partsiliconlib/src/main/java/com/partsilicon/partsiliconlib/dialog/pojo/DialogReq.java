
package com.partsilicon.partsiliconlib.dialog.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class DialogReq {

    @SerializedName("package")
    @Expose
    private String _package;
    @SerializedName("market")
    @Expose
    private String market;
    @SerializedName("version")
    @Expose
    private Integer version;
    @SerializedName("deviceId")
    @Expose
    private String deviceId;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("lastSeenId")
    @Expose
    private String lastSeenId;

    public String getPackage() {
        return _package;
    }

    public void setPackage(String _package) {
        this._package = _package;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLastSeenId() {
        return lastSeenId;
    }

    public void setLastSeenId(String lastSeenId) {
        this.lastSeenId = lastSeenId;
    }

}
