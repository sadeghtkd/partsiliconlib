
package com.partsilicon.partsiliconlib.notification.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotifList {

    @SerializedName("results")
    @Expose
    private List<Notif> notifs = null;

    public List<Notif> getResults() {
        return notifs;
    }

    public void setResults(List<Notif> notifs) {
        this.notifs = notifs;
    }

}
