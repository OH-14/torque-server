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
    //The locator can be use to reference that a player is located on an specific group, round, etc. 
    //This is useful for the scheduling algorithm to know where to place the match in the schedule.
    private int locator = 0;
}
