package com.torque.app.server.service.impl;

import java.util.List;

import com.torque.app.server.service.ScheduleGeneration;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.torque.app.server.model.*;
import com.torque.app.server.constants.*;
import com.torque.app.server.exceptions.*;

@Data
@NoArgsConstructor
public class scheduleGenerator implements ScheduleGeneration {
    private Schedule schedule;

    public void generateSchedule(List<Match> matches, ScheduleParams params, CompetitionType competitionType){
        switch (competitionType) {
             case CompetitionType. GROUPS_ROUND_ROBIN -> generateGroupsSchedule(matches, params);
             case CompetitionType.BRACKETS_SINGLE_ELIMINATION -> generateBracketsSchedule(matches, params);
                 default -> {
                     NotSpecifiedException.throwNotSpecifiedException("Competition type not specified");
                }
        }
        
    }

    private void generateGroupsSchedule(List<Match> matches, ScheduleParams params){
        
    }

    private void generateBracketsSchedule(List<Match> matches, ScheduleParams params){
        
    }

}
