package com.torque.app.server.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class ClubId {
    @NonNull
    private String name;
    public ClubId(String name){
        this.name = name.trim().toUpperCase();
    }
    public void setName(String name){
        this.name = name.trim().toUpperCase();
    }
}
