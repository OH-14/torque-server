package com.torque.app.server.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class Club {
    @NonNull
    private String name;

    public Club(String name){
        this.name = name.trim().toUpperCase();
    }
}
