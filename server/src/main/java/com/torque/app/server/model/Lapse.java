package com.torque.app.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class Lapse {
    @NonNull
    private Time start;
    @NonNull
    private Time end;
}
