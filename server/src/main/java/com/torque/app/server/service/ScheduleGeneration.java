package com.torque.app.server.service;

import java.util.List;

import com.torque.app.server.model.Match;
import com.torque.app.server.model.Schedule;
import com.torque.app.server.model.ScheduleParams;

public interface ScheduleGeneration {
    Schedule generateSchedule(List<Match> matches, ScheduleParams params);
}
