
package com.partsilicon.partsiliconlib.dialog.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Result implements Parcelable {

    @SerializedName("titles")
    @Expose
    private List<Title> titles = null;
    @SerializedName("package")
    @Expose
    private String _package;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("market")
    @Expose
    private String market;
    @SerializedName("version")
    @Expose
    private Integer version;
    @SerializedName("description")
    @Expose
    private List<Description> description = null;
    @SerializedName("photo")
    @Expose
    private List<Photo> photo = null;
    @SerializedName("targetUrl")
    @Expose
    private String targetUrl;
    @SerializedName("force")
    @Expose
    private Boolean force;
    @SerializedName("expire")
    @Expose
    private Expire expire;
    @SerializedName("buttonText")
    @Expose
    private List<ButtonText> buttonText = null;
    @SerializedName("objectId")
    @Expose
    private String objectId;
    @SerializedName("__type")
    @Expose
    private String type;
    @SerializedName("className")
    @Expose
    private String className;
    @SerializedName("actionType")
    @Expose
    private Integer actionType;

    @SerializedName("dialogType")
    @Expose
    private Integer dialogType;
    protected Result() {}


    protected Result(Parcel in) {
        titles = in.createTypedArrayList(Title.CREATOR);
        _package = in.readString();
        createdAt = in.readString();
        updatedAt = in.readString();
        market = in.readString();
        if (in.readByte() == 0) {
            version = null;
        } else {
            version = in.readInt();
        }
        description = in.createTypedArrayList(Description.CREATOR);
        photo = in.createTypedArrayList(Photo.CREATOR);
        targetUrl = in.readString();
        byte tmpForce = in.readByte();
        force = tmpForce == 0 ? null : tmpForce == 1;
        expire = in.readParcelable(Expire.class.getClassLoader());
        buttonText = in.createTypedArrayList(ButtonText.CREATOR);
        objectId = in.readString();
        type = in.readString();
        className = in.readString();
        if (in.readByte() == 0) {
            actionType = null;
        } else {
            actionType = in.readInt();
        }
        if (in.readByte() == 0) {
            dialogType = null;
        } else {
            dialogType = in.readInt();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(titles);
        dest.writeString(_package);
        dest.writeString(createdAt);
        dest.writeString(updatedAt);
        dest.writeString(market);
        if (version == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(version);
        }
        dest.writeTypedList(description);
        dest.writeTypedList(photo);
        dest.writeString(targetUrl);
        dest.writeByte((byte) (force == null ? 0 : force ? 1 : 2));
        dest.writeParcelable(expire, flags);
        dest.writeTypedList(buttonText);
        dest.writeString(objectId);
        dest.writeString(type);
        dest.writeString(className);
        if (actionType == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(actionType);
        }
        if (dialogType == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(dialogType);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Result> CREATOR = new Creator<Result>() {
        @Override
        public Result createFromParcel(Parcel in) {
            return new Result(in);
        }

        @Override
        public Result[] newArray(int size) {
            return new Result[size];
        }
    };

    public List<Title> getTitles() {
        return titles;
    }

    public void setTitles(List<Title> titles) {
        this.titles = titles;
    }

    public String getPackage() {
        return _package;
    }

    public void setPackage(String _package) {
        this._package = _package;
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

    public List<Description> getDescription() {
        return description;
    }

    public void setDescription(List<Description> description) {
        this.description = description;
    }

    public List<Photo> getPhoto() {
        return photo;
    }

    public void setPhoto(List<Photo> photo) {
        this.photo = photo;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public Boolean getForce() {
        return force;
    }

    public void setForce(Boolean force) {
        this.force = force;
    }

    public Expire getExpire() {
        return expire;
    }

    public void setExpire(Expire expire) {
        this.expire = expire;
    }

    public List<ButtonText> getButtonText() {
        return buttonText;
    }

    public void setButtonText(List<ButtonText> buttonText) {
        this.buttonText = buttonText;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getActionType() {
        return actionType;
    }

    public void setActionType(Integer actionType) {
        this.actionType = actionType;
    }
    public Integer getDialogType() {
        return dialogType;
    }

    public void setDialogType(Integer dialogType) {
        this.dialogType = dialogType;
    }
}
