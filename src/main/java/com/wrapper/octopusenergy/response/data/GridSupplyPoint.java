package com.wrapper.octopusenergy.response.data;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class GridSupplyPoint {

    @SerializedName("group_id")
    @Expose
    private String groupId;

    public String toString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this);
    }
}