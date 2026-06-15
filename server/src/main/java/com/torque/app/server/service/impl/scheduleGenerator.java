package com.torque.app.server.service.impl;

import java.util.List;

import com.torque.app.server.service.ScheduleGeneration;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.torque.app.server.model.*;


@Data
@NoArgsConstructor
public class scheduleGenerator implements ScheduleGeneration {
    private Schedule schedule;

    public void generateSchedule(List<Match> matches, ScheduleParams params){
        
    }
}
