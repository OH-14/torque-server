package com.torque.app.server.model;

import java.util.Map;

import com.torque.app.server.util.Day;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class ScheduleParams {
    @NonNull
    private final Day startDay;
    
   
    @NonNull
    //key: court/club , value: working
    private Map<Club, WorkingLapse> tournamentTimes;


}
