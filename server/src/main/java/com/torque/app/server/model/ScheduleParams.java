package com.torque.app.server.model;

import java.util.List;

import lombok.Data;
import lombok.NonNull;

@Data
public class ScheduleParams {
         
    @NonNull
    //court, working lapse
    private List<List<WorkingLapse>> tournamentTimes;
    //resting time duration per category
    @NonNull
    private List<Time> restingDurations;
    //expected match durations per category
    @NonNull
    private List<Time> matchDurations;
    public ScheduleParams(List<List<WorkingLapse>> tournamentTimes, List<Time> restingDurations, List<Time> matchDurations){
    
        this.restingDurations = restingDurations;
        this.matchDurations = matchDurations;
        this.tournamentTimes=tournamentTimes;
        removeUnnecesary();
        orderByTime();
    }

    private void removeUnnecesary(){
        //remove conflicts with static methods of lapses and unify conflicted or behind lapses

    }

    private void orderByTime(){
        //order tournament time with static methods of lapses and streams

    }


}
