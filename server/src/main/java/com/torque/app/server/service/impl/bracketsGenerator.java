package com.torque.app.server.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.torque.app.server.constants.BracketSortingType;
import com.torque.app.server.exceptions.InvalidDrawsException;
import com.torque.app.server.model.*;

import com.torque.app.server.service.CompetitionSystem;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class bracketsGenerator implements CompetitionSystem {
    
    private List<Bracket> draw = new ArrayList<>();
    private List<Match> matches = new ArrayList<>();

    @NonNull
    List<BracketSortingType> sortings;

    @Override
    public void generateDraw(List<Category> categories){
        //validate sortings length
        if(sortings.size()!=categories.size()) throw new InvalidDrawsException("The number of sorting types provided are different than the number of categories");

        categories.forEach(category->{

            //add bracket
            int catIndex = categories.indexOf(category);
            draw.add(makeBracket(category, catIndex));
            //list matches

        });


    }

    private Bracket makeBracket(Category category, int catIndex){
        
        BracketSortingType sort = sortings.get(catIndex);

        if(sort==null){
            throw new InvalidDrawsException("Sorting type is null");
        } else if(sort==BracketSortingType.SERPENTINE){
            return new Bracket(category.getName(), serpentine(category.getPlayers()));
        } else if (sort==BracketSortingType.RANDOM){
            return new Bracket(category.getName(), random(category.getPlayers()));
        } else {
            throw new InvalidDrawsException("Unhandled Sorting type: Unknown error");
        }


    }

    private List<List<Match>> serpentine(List<Player> players){
        List<List<Match>> matches = new ArrayList<>();



        return matches;
    }

    


    private List<List<Match>> random(List<Player> players){
        Collections.shuffle(players);
        return serpentine(players);
    }



}
