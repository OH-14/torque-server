package com.torque.app.server.model;




import java.util.LinkedHashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;  

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClubManager {
    @NonNull
    private Map<ClubId, Club> clubs= new LinkedHashMap<>();

    public void addClub(ClubId cid ,Club club){
            clubs.putIfAbsent(cid, club);
    }

    public void removeClub(ClubId cid){
        clubs.remove(cid);
    }

    public boolean hasClub(ClubId cid){
        return clubs.containsKey(cid);
    }

    public Club getClub(ClubId cid){
        return clubs.get(cid);
    }
}
