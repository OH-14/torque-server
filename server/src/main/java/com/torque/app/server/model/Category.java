package com.torque.app.server.model;
import java.util.List;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NonNull;
@Data
@AllArgsConstructor
public class Category {
    @NonNull
    private String name;
    @NonNull
    private List<Player> players;
}
