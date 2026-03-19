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
         if(hours>24||(hours==24&&minutes>0)) {
            throw new InvalidTimeException("Duration is greater than a day");
        }
        
    }
}
