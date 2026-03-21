package com.torque.app.server.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClubManager {
    @NonNull
    private List<Club> clubs= new ArrayList<>();

    public void addClub(Club club){
        if(!clubs.contains(club)){
            clubs.add(club);
             }
    }

    public void removeClub(Club club){
        clubs.remove(club);
    }

    public boolean hasClub(Club club){
        return clubs.contains(club);
    }
}
