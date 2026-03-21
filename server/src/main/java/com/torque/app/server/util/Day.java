package com.torque.app.server.util;

public enum Day {
    MON, TUE, WED, THU, FRI, SAT, SUN;
    private static final Day[] DAYS = Day.values();
    public Day next(){
        return plus(1);
    }
    public Day previous(){
        return plus(-1);
    }
    public Day plus(int days){
        int totalDays = DAYS.length;
        int returnIndex= (this.ordinal()+days)%totalDays;
        if(returnIndex<0) returnIndex=returnIndex+totalDays;
        return DAYS[returnIndex];
        
    }

}
