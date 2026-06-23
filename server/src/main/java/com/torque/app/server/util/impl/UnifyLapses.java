package com.torque.app.server.util.impl;

import com.torque.app.server.exceptions.InvalidConstructorException;
import com.torque.app.server.exceptions.InvalidTimeException;
import com.torque.app.server.model.Lapse;
import com.torque.app.server.util.LapseUtils;

public final class UnifyLapses {
    private UnifyLapses(){throw new InvalidConstructorException("UnifyLapses is a utility class and cannot be instantiated");}
    public static Lapse unifyLapses(Lapse lapse1, Lapse lapse2){
        if (!LapsesConflict.lapsesConflict(lapse1, lapse2)) throw new InvalidTimeException("Can not unify not conflicting lapses");
        return new Lapse(
                LapseUtils.startsBefore(lapse1, lapse2) ? lapse1.getStartHour() : lapse2.getStartHour(),
                LapseUtils.startsBefore(lapse1, lapse2) ? lapse1.getStartDay() : lapse2.getStartDay(),
                LapseUtils.startsBefore(lapse1, lapse2) ? lapse1.getStartWeek() : lapse2.getStartWeek(),
                LapseUtils.endsAfter(lapse1, lapse2) ? lapse1.getEndHour() : lapse2.getEndHour(),
                LapseUtils.endsAfter(lapse1, lapse2) ? lapse1.getEndDay() : lapse2.getEndDay(),
                LapseUtils.endsAfter(lapse1, lapse2) ? lapse1.getEndWeek() : lapse2.getEndWeek()
        );
}

}