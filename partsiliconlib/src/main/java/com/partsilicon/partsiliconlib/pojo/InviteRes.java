package com.partsilicon.partsiliconlib.pojo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class InviteRes {


        @SerializedName("status")
        @Expose
        private Boolean status;

        public Boolean getStatus() {
            return status;
        }

        public void setStatus(Boolean status) {
            this.status = status;
        }

    }
