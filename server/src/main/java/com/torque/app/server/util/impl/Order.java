package com.torque.app.server.util.impl;

import java.util.List;

import com.torque.app.server.exceptions.InvalidConstructorException;
import com.torque.app.server.exceptions.InvalidTimeException;
import com.torque.app.server.model.Lapse;
import com.torque.app.server.util.LapseUtils;

public final class Order {
    private Order(){throw new InvalidConstructorException("Order is a utility class and cannot be instantiated");}

    public static List<Lapse> orderByTime(List<Lapse> lapses){
        return lapses.stream().sorted((lapse1, lapse2) -> {
           if(lapse1==null||lapse2==null) throw new InvalidTimeException("Lapses can not be null");
            if(LapseUtils.startEqual(lapse1, lapse2)){
               return 0;
           } 
           if(LapseUtils.startsBefore(lapse1, lapse2)){
               return -1;
           } 
           if(LapseUtils.startsBefore(lapse2, lapse1)){
               return 1;
           } 
           return 0;
        }).toList();
}
}