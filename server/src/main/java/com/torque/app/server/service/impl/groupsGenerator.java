package com.torque.app.server.service.impl;

import java.util.List;

import com.torque.app.server.exceptions.InvalidDrawsException;
import com.torque.app.server.model.*;
import com.torque.app.server.service.CompetitionSystem;

public class groupsGenerator implements CompetitionSystem {
    private List<GroupsCat> draw;
    private List<Match> matches;

    @Override
    public void generateDraw(List<Category> categories){
        categories.stream().forEach(category->{
            draw.add(makeGroup(category));
        });
    }

    
    private GroupsCat makeGroup(Category category){
        
        

        return 0;
    }

    @Override
    public void generateMatches(){
        if(draw==null) throw new InvalidDrawsException ("draws are null, cannot generate matches from null draws");
    }

}
