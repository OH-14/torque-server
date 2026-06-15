package com.torque.app.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
@Data
@AllArgsConstructor
public class ScheduledMatch {
    @NonNull
    private Match encounter;
    @NonNull
    private WorkingLapse timeCourt;
    
}
