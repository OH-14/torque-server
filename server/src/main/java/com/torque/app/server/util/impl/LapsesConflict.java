package com.torque.app.server.util.impl;

import com.torque.app.server.model.Lapse;
import com.torque.app.server.util.LapseUtils;
import com.torque.app.server.exceptions.InvalidConstructorException;

public final class LapsesConflict {
    private LapsesConflict(){throw new InvalidConstructorException("LapsesConflict is a utility class and cannot be instantiated");}
    public static boolean lapsesConflict(Lapse lapse1, Lapse lapse2){
        return (LapseUtils.startsBefore(lapse1, lapse2) && LapseUtils.endsAfterStart(lapse1, lapse2))
                || (LapseUtils.startsBefore(lapse2, lapse1) && LapseUtils.endsAfterStart(lapse2, lapse1));
    }

}
