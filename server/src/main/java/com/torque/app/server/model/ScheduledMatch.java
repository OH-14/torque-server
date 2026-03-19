package com.torque.app.server.model;
import lombok.Data; 
import lombok.NonNull;
import lombok.AllArgsConstructor;
@Data
@AllArgsConstructor
public class ScheduledMatch {
    @NonNull
    private Match encounter;
    @NonNull
    private Time Starthour;
    @NonNull
    private Time ExpectedDuration;
}
