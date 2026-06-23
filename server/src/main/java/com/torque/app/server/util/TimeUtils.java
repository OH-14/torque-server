package com.torque.app.server.util;
import com.torque.app.server.exceptions.InvalidConstructorException;
import com.torque.app.server.model.Time;
public final class TimeUtils {
    private TimeUtils(){throw new InvalidConstructorException("TimeUtils is a utility class and cannot be instantiated");}
        private static int hoursPassed(Time start, Time duration){
        return start.getHour()+duration.getHour()+ (start.getMinutes()+duration.getMinutes())/60;
    }

    private static int minutesSum(Time start, Time duration){
        return start.getMinutes()+duration.getMinutes();
    }

    public static Time findEndHour(Time start, Time duration){
        return new Time(hoursPassed(start, duration)%24,minutesSum(start, duration)%60);
    }

    public static int daysPassed(Time start, Time duration){
        return hoursPassed(start, duration)/24;
    }

    public static Time earlier(Time time1, Time time2){
        if(time1.getHour()<time2.getHour()|| (time1.getHour()==time2.getHour()&&time1.getMinutes()<time2.getMinutes())){
            return time1;
        } else {
            return time2;
        }
    }
}
