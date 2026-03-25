package com.torque.app.server.model;

import java.util.List;

import com.torque.app.server.util.Day;

import lombok.Data;
import lombok.NonNull;

@Data
public class ScheduleParams {
    @NonNull
    private final Day startDay;
       
    @NonNull
    //key: court/club , value: working
    private List<WorkingLapse> tournamentTimes;

    public ScheduleParams(Day startDay, List<WorkingLapse> tournamentTimes){
        this.startDay=startDay;
        this.tournamentTimes=tournamentTimes;

        removeUnnecesary();
        orderByHour();
    }

    private void removeUnnecesary(){

    }

    private void orderByHour(){
        
    }


}
