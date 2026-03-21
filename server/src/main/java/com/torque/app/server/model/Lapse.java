package com.torque.app.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class Lapse {
    @NonNull
    private final Time start;
    @NonNull
    private final Time end;
}
