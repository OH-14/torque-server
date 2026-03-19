package com.torque.app.server.model;
import java.util.List;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NonNull;
@Data
@AllArgsConstructor
public class Schedule {
    //court hour
    @NonNull
    private List<List<ScheduledMatch>> scheduleMatrix;
}
