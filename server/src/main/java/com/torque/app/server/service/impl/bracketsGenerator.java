package com.torque.app.server.service.impl;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
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
        if(sortings.size()<=catIndex||catIndex<0) throw new InvalidDrawsException("Can not get sorting type, check that catIndex is not negative and that sortings have that index");
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
        int numberOfPlayers = players.size();
        int numberOfPositions = getNumberOfPositions(numberOfPlayers);
        int numberOfRounds = getNumberOfRounds(numberOfPositions);
        List<Player> playersWithBYE = addBYE(numberOfPlayers, numberOfPositions, players);
        check(numberOfPositions);
        List<Match> firstRound = getFirstRound(playersWithBYE, numberOfPositions, numberOfRounds);
        matches.add(firstRound);
        for(int i=0; i<numberOfRounds; i++){
            
        }
        return matches;
    }

    private void check(int numberOfPositions){
        if(numberOfPositions<2) throw new InvalidDrawsException("There are less than two");
        if(Integer.highestOneBit(numberOfPositions)!=numberOfPositions) throw new InvalidDrawsException("The number of players is not in base 2");
    }

    private List<Match> getFirstRound(List<Player> players,int numberOfPositions, int numberOfRounds){
        List<Integer> orders = generateOrder(numberOfPositions, numberOfRounds);





        return matches;
    }

        private List<Integer> generateOrder(int numberOfPositions, int numberOfRounds){
        List<Integer> orders = Arrays.asList(1, 2);
        
        boolean below = true;
        for(int i = 1; i<numberOfRounds;i++){
            int ordersSize = orders.size();
            int iterator = ordersSize;
            for(int e= 0; e<ordersSize;e++){
                if(below) orders.add(iterator+1,numberOfPositions+1-orders.get(iterator)); 
                else orders.add(iterator-1, numberOfPositions+1-orders.get(iterator));
                iterator++;
            }
        }


        return orders;
    }

    private List<Player> addBYE(int numberOfPlayers, int numberOfPositions, List<Player> players ){
        Player bye = new Player("BYE");
        for(int i = numberOfPlayers;i<numberOfPositions;i++) players.add(bye);
        return players;
    }

    private int getNumberOfPositions(int numberOfPlayers){
        return Integer.highestOneBit(numberOfPlayers-1)<<1;
    }

    private int getNumberOfRounds(int numberOfPositions){
        return Integer.numberOfTrailingZeros(numberOfPositions);
    }



    


    private List<List<Match>> random(List<Player> players){
        Collections.shuffle(players);
        return serpentine(players);
    }



}
