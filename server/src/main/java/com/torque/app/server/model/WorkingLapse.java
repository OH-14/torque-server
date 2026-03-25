package com.torque.app.server.model;


import lombok.Data;

//this is a space/time where the tournament is running, in other words is a lapse on a 
//court were a match can be played
@Data
public class WorkingLapse {
    private String court;
    private Lapse timeLapse;
}
