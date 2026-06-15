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
    //what happens if the tournament last several weeks
    private final int week;
    
    public Time getEndHour(){
        return Time.findEndHour(start, duration);
    }

    public Day getEndDay(){
        return startDay.plus(Time.daysPassed(start, duration));
    }

    public int getEndWeek(){
        //as duration can only be less than one day
        if (startDay==Day.SUN&&getEndDay()==Day.MON) return week+1;
        return week;
    }

    //one lap starts before or equal to the other and ends after the other started
    public static boolean laspsesConflict(Lapse lapse1, Lapse lapse2){
        boolean weekEqual = weekEqual(lapse1, lapse2);
        boolean dayEqual = dayEqual(lapse1, lapse2);
        boolean endWeekEqual = endWeekEqual(lapse1, lapse2);
        boolean endDayEqual = endDayEqual(lapse1, lapse2);
        //1 case: same week, same day
        if(weekEqual&&dayEqual&&endWeekEqual&&endDayEqual){
            
        }

        return false;
    }

    private static boolean weekEqual(Lapse lapse1,Lapse lapse2){
        return lapse1.getWeek()==lapse2.getWeek();
    }

    private static boolean dayEqual(Lapse lapse1,Lapse lapse2){
        return lapse1.getStartDay().equals(lapse2.getStartDay());
    }

    
    private static boolean endWeekEqual(Lapse lapse1,Lapse lapse2){
        return lapse1.getEndWeek()==lapse2.getEndWeek();
    }

    private static boolean endDayEqual(Lapse lapse1,Lapse lapse2){
        return lapse1.getEndDay().equals(lapse2.getEndDay());
    }


}
