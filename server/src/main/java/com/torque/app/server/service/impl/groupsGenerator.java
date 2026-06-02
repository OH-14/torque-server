package com.torque.app.server.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import org.springframework.web.service.registry.HttpServiceGroupConfigurer.Groups;

import com.torque.app.server.constants.GroupsSortingType;
import com.torque.app.server.exceptions.InvalidDrawsException;
import com.torque.app.server.model.*;
import com.torque.app.server.service.CompetitionSystem;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
public class groupsGenerator implements CompetitionSystem {
    private List<GroupsCat> draw=new ArrayList<>();
    private List<Match> matches= new ArrayList<>();

    @NonNull
    private GroupsSortingType sort;

    @Override
    public void generateDraw(List<Category> categories){
        categories.stream().forEach(category->{
            //add groups
            draw.add(makeGroup(category));
            //add matches

        });

        
    }

    private void listMatches(){
        if(draw==null||draw.isEmpty()) throw new InvalidDrawsException("Can not generate matches from a null or empty draw");
        
    }
    
    private GroupsCat makeGroup(Category category){
        List<Group> groups = new ArrayList<>();
        //This should never happen since sort is intended to be non-null
        if(sort==null){
            throw new InvalidDrawsException("Sorting system was not specified");
        } else if(sort==GroupsSortingType.SERPENTINE){
            groups=serpentine(category.getPlayers(), category.getNumGroups());
        } else if (sort==GroupsSortingType.RANDOM){
            groups=random(category.getPlayers(), category.getNumGroups());
        } else {
            throw new InvalidDrawsException("Unhandled Sorting system");
        }

        GroupsCat groupsCat = new GroupsCat(category.getName(), groups);

        return groupsCat;
    }

    private List<Group> serpentine(List<Player> players, int numGroups){
        List<Group> groups = new ArrayList<>();
        if(players.isEmpty()||numGroups<1) throw new InvalidDrawsException("There are no players or the number of groups is less than one");
        
        //initialize groups
        int end = (players.size()<numGroups)? players.size(): numGroups;
        for(int i = 0; i<end;i++) groups.add(new Group(new ArrayList<Player>()));
        //distribute players
        int playerIndex = 0;
        while(players.size()>playerIndex){
            for(int i=0; i<numGroups;i++){
                if(players.size()<=playerIndex) break;
                groups.get(i).getPlayers().add(players.get(playerIndex));
                playerIndex++;
            }
            for(int i=numGroups-1; i>=0;i--){
                if(players.size()<=playerIndex) break;
                groups.get(i).getPlayers().add(players.get(playerIndex));
                playerIndex++;
            }
        }
        

        return groups;
    }

    private List<Group> random(List<Player> players, int numGroups){
        Collections.shuffle(players);
        return serpentine(players, numGroups);
    }



}
