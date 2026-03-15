package com.torque.app.server.model;
//necessary imports for not doing manually getters, setters,
//  equals, hashcode, toString, constructors
import lombok.Data; 
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Player {
    private String name;
    private int used=2;
}
