package com.torque.app.server.model;
import lombok.Data; 
import lombok.NonNull;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class Match {
    @NonNull
    private Player one;
    @NonNull
    private Player two;
    @NonNull
    private String category;
}
