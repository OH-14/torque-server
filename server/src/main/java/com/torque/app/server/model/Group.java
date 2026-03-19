package com.torque.app.server.model;
import lombok.Data; 
import lombok.NonNull;
import lombok.AllArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
public class Group {
    @NonNull
    private List<Player> players;

}
