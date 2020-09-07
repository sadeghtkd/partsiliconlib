package com.partsilicon.partsiliconlib.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class App {
    @SerializedName("Id")
    @Expose
    private var id: String? = null
    @SerializedName("target")
    @Expose
    private var target: String? = null
    @SerializedName("message")
    @Expose
    private var message: String? = null
    @SerializedName("targetGift")
    @Expose
    private var targetGift: String? = null
    @SerializedName("hostGift")
    @Expose
    private var hostGift: String? = null
    @SerializedName("hostApp")
    @Expose
    private var hostApp: String? = null
    @SerializedName("title")
    @Expose
    private var title: String? = null
    @SerializedName("targetAppIcon")
    @Expose
    private var targetAppIcon: String? = null
    @SerializedName("sendingMessage")
    @Expose
    private var sendingMessage: String? = null
    @SerializedName("link")
    @Expose
    private var link: String? = null

    fun getId(): String? {
        return id
    }

    fun setId(id: String?) {
        this.id = id
    }

    fun getTarget(): String? {
        return target
    }

    fun setTarget(target: String?) {
        this.target = target
    }

    fun getMessage(): String? {
        return message
    }

    fun setMessage(message: String?) {
        this.message = message
    }

    fun getTargetGift(): String? {
        return targetGift
    }

    fun setTargetGift(targetGift: String?) {
        this.targetGift = targetGift
    }

    fun getHostGift(): String? {
        return hostGift
    }

    fun setHostGift(hostGift: String?) {
        this.hostGift = hostGift
    }

    fun getHostApp(): String? {
        return hostApp
    }

    fun setHostApp(hostApp: String?) {
        this.hostApp = hostApp
    }

    fun getTitle(): String? {
        return title
    }

    fun setTitle(title: String?) {
        this.title = title
    }

    fun getTargetAppIcon(): String? {
        return targetAppIcon
    }

    fun setTargetAppIcon(targetAppIcon: String?) {
        this.targetAppIcon = targetAppIcon
    }

    fun getSendingMessage(): String? {
        return sendingMessage
    }

    fun setSendingMessage(sendingMessage: String?) {
        this.sendingMessage = sendingMessage
    }

    fun getLink(): String? {
        return link
    }

    fun setLink(link: String?) {
        this.link = link
    }
}
