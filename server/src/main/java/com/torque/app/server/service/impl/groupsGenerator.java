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
    private List<GroupsSortingType> sortings;

    @Override
    public void generateDraw(List<Category> categories){
        //validate sortings length
        if(sortings.size()!=categories.size()) throw new InvalidDrawsException("The number of sorting types provided are different than the number of categories");
        
        categories.forEach(category->{
            //add groups
            int catIndex = categories.indexOf(category);
            draw.add(makeGroup(category, catIndex));
            //add matches
            listMatches();
        });

        
    }

    private void listMatches(){
        if(draw==null||draw.isEmpty()) throw new InvalidDrawsException("Can not generate matches from a null or empty draw");
        draw.forEach(category -> {
            category.getGroups().forEach(group->{
                int groupSize = group.getPlayers().size();
                List<Player> players = group.getPlayers();
                if(groupSize>1){
                for(int player1 = 0; player1<groupSize-1;player1++){
                    for(int player2 = player1+1;player2<groupSize;player2++){
                        Player one = players.get(player1);
                        Player two = players.get(player2);
                        matches.add(new Match(one,two));
                    }
                }}
            });
        });
    }
    
    private GroupsCat makeGroup(Category category, int catIndex){
        List<Group> groups = new ArrayList<>();
        //This should never happen since sort is intended to be non-null  
        GroupsSortingType sort = sortings.get(catIndex);
        if(sort==null){
            throw new InvalidDrawsException("Sorting type is null");
        } else if(sort==GroupsSortingType.SERPENTINE){
            groups=serpentine(category.getPlayers(), category.getNumGroups());
        } else if (sort==GroupsSortingType.RANDOM){
            groups=random(category.getPlayers(), category.getNumGroups());
        } else {
            throw new InvalidDrawsException("Unhandled Sorting type: Unknown error");
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
