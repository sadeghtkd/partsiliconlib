
package com.partsilicon.partsiliconlib.notification.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class Notif {

    @NonNull
    @PrimaryKey
    @SerializedName("objectId")
    @Expose
    private String objectId;
    @ColumnInfo(name = "buttonLabel")
    @SerializedName("buttonLabel")
    @Expose
    private String buttonLabel;
    @ColumnInfo(name ="visitCount")
    @SerializedName("visitCount")
    @Expose
    private Integer visitCount;
    @ColumnInfo(name ="clickCount")
    @SerializedName("clickCount")
    @Expose
    private Integer clickCount;
    @ColumnInfo(name ="createdAt")
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @ColumnInfo(name ="updatedAt")
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    /*@ColumnInfo(name ="targetApps")
    @SerializedName("targetApps")
    @Expose
    private List<String> targetApps = null;*/
    @ColumnInfo(name ="shortDesc")
    @SerializedName("shortDesc")
    @Expose
    private String shortDesc;
    @ColumnInfo(name ="longText")
    @SerializedName("longText")
    @Expose
    private String longText;
    @ColumnInfo(name ="actionType")
    @SerializedName("actionType")
    @Expose
    private Integer actionType;
    @ColumnInfo(name ="action")
    @SerializedName("action")
    @Expose
    private String action;
    @ColumnInfo(name = "title")
    @SerializedName("title")
    @Expose
    private String title;
    @ColumnInfo(name = "picURL")
    @SerializedName("picURL")
    @Expose
    private String picUrl;
    @ColumnInfo(name = "showNotif")
    @SerializedName("showNotif")
    @Expose
    private Boolean showNotif;
    @ColumnInfo(name ="displayType")
    @SerializedName("displayType")
    @Expose
    private Integer displayType;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getButtonLabel() {
        return buttonLabel;
    }

    public void setButtonLabel(String buttonLabel) {
        this.buttonLabel = buttonLabel;
    }

    public Integer getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(Integer visitCount) {
        this.visitCount = visitCount;
    }

    public Integer getClickCount() {
        return clickCount;
    }

    public void setClickCount(Integer clickCount) {
        this.clickCount = clickCount;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

/*    public List<String> getTargetApps() {
        return targetApps;
    }

    public void setTargetApps(List<String> targetApps) {
        this.targetApps = targetApps;
    }*/

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public Integer getActionType() {
        return actionType;
    }

    public void setActionType(Integer actionType) {
        this.actionType = actionType;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getLongText() {
        return longText;
    }

    public void setLongText(String longText) {
        this.longText = longText;
    }

    public Boolean getShowNotif() {
        return showNotif;
    }

    public void setShowNotif(Boolean showNotif) {
        this.showNotif = showNotif;
    }

    public Integer getDisplayType() {
        return displayType;
    }

    public void setDisplayType(Integer displayType) {
        this.displayType = displayType;
    }
}
