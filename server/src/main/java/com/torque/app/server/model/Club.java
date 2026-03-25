package com.torque.app.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class Club {
    @NonNull
    private ClubId cid;
    @NonNull
    private String name;
    
}
