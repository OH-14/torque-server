package com.torque.app.server.model;

import com.torque.app.server.util.Day;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class Lapse {
    @NonNull
    private final Time start;
    @NonNull
    private final Time duration;
    @NonNull
    private final Day startDay;

    public Time getEndHour(){
        return Time.findEndHour(start, duration);
    }

    public Day getEndDay(){
        return startDay.plus(Time.daysPassed(start, duration));
    }


}
