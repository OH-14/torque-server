package com.torque.app.server.service;

import java.util.List;

import com.torque.app.server.model.Match;
import com.torque.app.server.model.ScheduleParams;
import com.torque.app.server.constants.CompetitionType;

public interface ScheduleGeneration {
    void generateSchedule(List<Match> matches, ScheduleParams params, CompetitionType competitionType);
}
