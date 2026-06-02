package com.torque.app.server.model;
import java.util.List;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
@Data
@RequiredArgsConstructor
public class Category {
    @NonNull
    private String name;
    @NonNull
    private List<Player> players;

    //if the category is intended to be groups, the
    //  number of groups must be specified,
    //  otherwise the default is 1 and the variable numGroups should not be used
    private int numGroups=1;

}
