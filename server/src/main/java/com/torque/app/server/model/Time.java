package com.torque.app.server.model;
import lombok.Data; 
import lombok.NoArgsConstructor;
import java.time.Duration;
import com.torque.app.server.exceptions.*;
@Data
@NoArgsConstructor
public class Time {
    private Duration duration=Duration.ZERO;
    //manually constructor
    public Time(int hours, int minutes){
            validate(hours, minutes);
            duration=Duration.ofHours(hours).plusMinutes(minutes);
        
        
    }
    //manually getters and setters so I can work with just hours and minutes
    public int getHour() {
        return (int) duration.toHours();
    }

    public int getMinutes() {
        return duration.toMinutesPart();
    }
    public void setHour(int hour){
        validate(hour, getMinutes());
        duration=Duration.ofHours(hour).plusMinutes(getMinutes());
    }
    public void setMinutes(int minutes){
        validate(getHour(), minutes);
        duration=Duration.ofHours(getHour()).plusMinutes(minutes);
    }

    //get hour in hh:mm format
    public String getFormattedTimeString(){
        return String.format("%02d:%02d", getHour(), getMinutes());
    }

    private void validate(int hours, int minutes){
         if(hours>23) {
            throw new InvalidTimeException("Duration is a day or greater");
        }
        
    }


    //generic functions
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
}
