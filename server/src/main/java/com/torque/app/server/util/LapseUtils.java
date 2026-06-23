package com.torque.app.server.util;

import com.torque.app.server.model.Day;
import com.torque.app.server.model.Lapse;
import com.torque.app.server.exceptions.InvalidConstructorException;
public final class LapseUtils {
    private LapseUtils() {throw new InvalidConstructorException("LapseUtils is a utility class and cannot be instantiated");}


    public static boolean endsAfter(Lapse lapse1 , Lapse lapse2){
        if(endDaysAfterEnd(lapse1, lapse2)) return true;
        return endDayEqualToEndDay(lapse1, lapse2) && endHourAfterOrEqualToEndHour(lapse1, lapse2);
    }

    public static boolean startsBefore(Lapse lapse1, Lapse lapse2){
        if(startDaysBefore(lapse1, lapse2)) return true;
        return startDayEqual(lapse1, lapse2)&&equalOrBeforeStartHour(lapse1, lapse2);
    }


    public static boolean endsAfterStart(Lapse lapse1, Lapse lapse2){
        if(endDaysAfterStart(lapse1, lapse2)) return true;
        return endDayEqualToStartDay(lapse1, lapse2)&&endHourAfterOrEqualToStartHour(lapse1, lapse2);
    }

    public static boolean startEqual(Lapse lapse1, Lapse lapse2){
        return startWeekEqual(lapse1, lapse2) && startDayEqual(lapse1, lapse2) && startTimeEqual(lapse1, lapse2);
    }

    private static boolean startTimeEqual(Lapse lapse1, Lapse lapse2){
        return lapse1.getStartHour().getHour()==lapse2.getStartHour().getHour() && lapse1.getStartHour().getMinutes()==lapse2.getStartHour().getMinutes();
    }

    private static boolean endHourAfterOrEqualToStartHour(Lapse lapse1, Lapse lapse2){
        return lapse1.getEndHour().getHour()>lapse2.getStartHour().getHour() || (lapse1.getEndHour().getHour()==lapse2.getStartHour().getHour() && lapse1.getEndHour().getMinutes()>=lapse2.getStartHour().getMinutes());
    }
    private static boolean endDayEqualToStartDay(Lapse lapse1, Lapse lapse2){
        return lapse1.getEndDay()==lapse2.getStartDay();
    }

    private static boolean endDaysAfterStart(Lapse lapse1, Lapse lapse2){
                return lapse1.getEndWeek()>lapse2.getStartWeek() || (endWeekEqualToStartWeek(lapse1, lapse2) && Day.dayIsAfter(lapse1.getEndDay(), lapse2.getStartDay()));
    }
    private static boolean endWeekEqualToStartWeek(Lapse lapse1, Lapse lapse2){
        return lapse1.getEndWeek()==lapse2.getStartWeek();
    }

    private static boolean endHourAfterOrEqualToEndHour(Lapse lapse1, Lapse lapse2){
        return lapse1.getEndHour().getHour()>lapse2.getEndHour().getHour() || (lapse1.getEndHour().getHour()==lapse2.getEndHour().getHour() && lapse1.getEndHour().getMinutes()>=lapse2.getEndHour().getMinutes());
    }
    private static boolean endDayEqualToEndDay(Lapse lapse1, Lapse lapse2){
        return lapse1.getEndDay()==lapse2.getEndDay();
    }

    private static boolean endDaysAfterEnd(Lapse lapse1, Lapse lapse2){
                return lapse1.getEndWeek()>lapse2.getEndWeek() || (endWeekEqualToEndWeek(lapse1, lapse2) && Day.dayIsAfter(lapse1.getEndDay(), lapse2.getEndDay()));
    }
    private static boolean endWeekEqualToEndWeek(Lapse lapse1, Lapse lapse2){
        return lapse1.getEndWeek()==lapse2.getEndWeek();
    }
    private static boolean equalOrBeforeStartHour(Lapse lapse1, Lapse lapse2){
        if(lapse1.getStartHour().getHour()<lapse2.getStartHour().getHour()) return true;
        return lapse1.getStartHour().getHour()==lapse2.getStartHour().getHour() && lapse1.getStartHour().getMinutes()<=lapse2.getStartHour().getMinutes();
    }

    private static boolean startDayEqual(Lapse lapse1, Lapse lapse2){
        return lapse1.getStartDay()==lapse2.getStartDay();
    }

    private static boolean startDaysBefore(Lapse lapse1, Lapse lapse2){
        return lapse1.getStartWeek()<lapse2.getStartWeek() || (startWeekEqual(lapse1, lapse2)    && Day.dayIsBefore(lapse1.getStartDay(), lapse2.getStartDay()));
    }
    private static boolean startWeekEqual(Lapse lapse1,Lapse lapse2){
        return lapse1.getStartWeek()==lapse2.getStartWeek();
    }

}
