package com.torque.app.server.model;
import lombok.Data; 
import lombok.NoArgsConstructor;
import java.time.Duration;
@Data
@NoArgsConstructor
public class Time {
    private Duration duration;
    //manually constructor
    public Time(int hours, int minutes){
        this.duration=Duration.ofHours(hours).plusMinutes(minutes);
    }
    //get hour in hh:mm format
    public String getHour(){
        long h = duration.toHours()%24;
        int m = duration.toMinutesPart();
        return String.format("%02d:%02d", h, m);
    }
}
