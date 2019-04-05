package com.amanciodrp.yellotaxi.model;

import com.google.gson.annotations.SerializedName;

public class DefaultUseSettings {

    @SerializedName("showOnboarding")
    private boolean showOnboarding;
    @SerializedName("mode")
    private String mode;


    public boolean isShowOnboarding() {
        return showOnboarding;
    }

    public void setShowOnboarding(boolean showOnboarding) {
        this.showOnboarding = showOnboarding;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    @Override
    public String toString() {
        return "DefaultUseSettings{" +
                "showOnboarding=" + showOnboarding +
                ", mode='" + mode + '\'' +
                '}';
    }
}
