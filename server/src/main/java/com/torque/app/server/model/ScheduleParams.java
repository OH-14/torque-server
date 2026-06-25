package com.torque.app.server.model;

import java.util.List;

import lombok.Data;
import lombok.NonNull;

import com.torque.app.server.util.impl.Order;

@Data
public class ScheduleParams {
         
    @NonNull
    //court, working lapse
    private List<List<Lapse>> workingLapses;
    //resting time duration per category
    @NonNull
    private List<Time> restingDurations;
    //expected match durations per category
    @NonNull
    private List<Time> matchDurations;
    public ScheduleParams(List<List<Lapse>> workingLapses, List<Time> restingDurations, List<Time> matchDurations){
    
        this.restingDurations = restingDurations;
        this.matchDurations = matchDurations;

        //Remove unnecesary Lapses and order each list of lapses
        this.workingLapses= workingLapses.stream().map(lapses->Order.orderAndSimplifyLapses(lapses)).toList();
        
    }


}
