package com.torque.app.server.model;
import com.torque.app.server.util.Day;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
@Data
@AllArgsConstructor
public class ScheduledMatch {
    @NonNull
    private Match encounter;
    @NonNull
    private Lapse timeLapse;
}
