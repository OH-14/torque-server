package com.torque.app.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class Lapse {
    @NonNull
    private final Time startHour;
    @NonNull
    private final Day startDay;
    
    private final int startWeek;
   
    @NonNull
    private final Time endHour;

    @NonNull
    private final Day endDay;

    private final int endWeek;

}
