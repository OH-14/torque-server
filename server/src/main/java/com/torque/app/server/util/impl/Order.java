package com.torque.app.server.util.impl;

import java.util.List;
import java.util.Comparator;
import java.util.ArrayList;

import com.torque.app.server.exceptions.InvalidConstructorException;
import com.torque.app.server.exceptions.NotSpecifiedException;
import com.torque.app.server.model.Lapse;

public final class Order {
    private Order(){throw new InvalidConstructorException("Order is a utility class and cannot be instantiated");}

    
    public static List<Lapse> orderAndSimplifyLapses(List<Lapse> lapses){
        if(lapses==null||lapses.isEmpty()) throw new NotSpecifiedException("Lapses list cannot be null or empty");
        Comparator<Lapse> StartsEarlierComparator = Comparator
        .comparingInt(Lapse::getStartWeek)
        .thenComparing(Lapse::getStartDay)
        .thenComparingInt(lapse -> lapse.getStartHour().getHour())
        .thenComparingInt(lapse -> lapse.getStartHour().getMinutes());
        return lapses.stream().sorted(StartsEarlierComparator).collect(ArrayList::new, (acc, next) -> {
        if (acc.isEmpty()) {
            acc.add(next);
        } else {
            Lapse last = acc.get(acc.size() - 1);
            if (LapsesConflict.lapsesConflict(last, next)) {
                acc.set(acc.size() - 1, UnifyLapses.unifyLapses(last, next));
            } else {
                acc.add(next);
            }
        }
    }, ArrayList::addAll);

}
}