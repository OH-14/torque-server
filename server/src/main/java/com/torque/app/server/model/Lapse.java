package com.torque.app.server.model;

import com.torque.app.server.exceptions.InvalidTimeException;
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



    public static Lapse unifyLapses(Lapse lapse1, Lapse lapse2){
        if(!laspsesConflict(lapse1, lapse2)) throw new InvalidTimeException("Can not unify lapses that do not conflict");
        Time start = Time.earlier(lapse1.getStart(), lapse2.getStart());
        return null;
    }
    
    









    //a lap conficts if one lap starts before or equal to the other and ends after or when the other started
    public static boolean laspsesConflict(Lapse lapse1, Lapse lapse2){
        return ((startsBefore(lapse1, lapse2)&&endsAfter(lapse1, lapse2))||(startsBefore(lapse2, lapse1)&&endsAfter(lapse2, lapse1)));
    }







    private static boolean startsBefore(Lapse lapse1, Lapse lapse2){
        if(startDaysBefore(lapse1, lapse2)) return true;
        return startDayEqual(lapse1, lapse2)&&equalOrBeforeStartHour(lapse1, lapse2);
    }


    private static boolean endsAfter(Lapse lapse1, Lapse lapse2){
        if(endDaysAfterStart(lapse1, lapse2)) return true;
        return endDayEqualToStartDay(lapse1, lapse2)&&endHourAfterOrEqualToStartHour(lapse1, lapse2);
    }

    private static boolean endHourAfterOrEqualToStartHour(Lapse lapse1, Lapse lapse2){
        return lapse1.getEndHour().getHour()>lapse2.getStart().getHour()|| (lapse1.getEndHour().getHour()==lapse2.getStart().getHour()&&lapse1.getEndHour().getMinutes()>=lapse2.getStart().getMinutes());
    }
    private static boolean endDayEqualToStartDay(Lapse lapse1, Lapse lapse2){
        return lapse1.getEndDay()==lapse2.getStartDay();
    }

    private static boolean endDaysAfterStart(Lapse lapse1, Lapse lapse2){
                return lapse1.getEndWeek()>lapse2.getWeek()||(endWeekEqualToStartWeek(lapse1, lapse2)&&Day.dayIsAfter(lapse1.getEndDay(), lapse2.getStartDay()));
    }
    private static boolean endWeekEqualToStartWeek(Lapse lapse1, Lapse lapse2){
        return lapse1.getEndWeek()==lapse2.getWeek();
    }
    private static boolean equalOrBeforeStartHour(Lapse lapse1, Lapse lapse2){
        if(lapse1.getStart().getHour()<lapse2.getStart().getHour()) return true;
        return lapse1.getStart().getHour()==lapse2.getStart().getHour()&&lapse1.getStart().getMinutes()<=lapse2.getStart().getMinutes();
    }

    private static boolean startDayEqual(Lapse lapse1, Lapse lapse2){
        return lapse1.getStartDay()==lapse2.getStartDay();
    }

    private static boolean startDaysBefore(Lapse lapse1, Lapse lapse2){
        return lapse1.getWeek()<lapse2.getWeek()||(startWeekEqual(lapse1, lapse2)&&Day.dayIsBefore(lapse1.getStartDay(), lapse2.getStartDay()));
    }
    private static boolean startWeekEqual(Lapse lapse1,Lapse lapse2){
        return lapse1.getWeek()==lapse2.getWeek();
    }


}
