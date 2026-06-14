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

        categories.stream().forEach(category->{

            //add bracket
            int catIndex = categories.indexOf(category);
            draw.add(makeBracket(category, catIndex));

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
       
        int numberOfPlayers = players.size();
        int numberOfPositions = getNumberOfPositions(numberOfPlayers);
        int numberOfRounds = getNumberOfRounds(numberOfPositions);
        List<Player> playersWithBYE = addBYE(numberOfPlayers, numberOfPositions, players);
        check(numberOfPositions);
        List<Match> firstRound = getFirstRound(playersWithBYE, numberOfPositions, numberOfRounds);
        List<List<Match>> matches = addAllRounds(numberOfRounds, firstRound);
        return matches;
    }

    private List<List<Match>> addAllRounds(int numberOfRounds, List<Match> firstRound){
        List<List<Match>> rounds = new ArrayList<>();
        rounds.add(firstRound);

        for(int i= 1; i<numberOfRounds; i++){
            List<Match> lastRound = rounds.getLast();
            List<Match> newRound = new ArrayList<>();
            for(int e = 0;e<lastRound.size();e=e+2){
                Player winnerOne = getWinner(lastRound.get(e));
                Player winnerTwo = getWinner(lastRound.get(e+1));
                Match match = new Match(winnerOne, winnerTwo);
                newRound.add(match);
                this.matches.add(match);
            }
            rounds.add(newRound);
        }

        return rounds;
    }

    private Player getWinner(Match match){
        Player bye = new Player("BYE");
        if (match==null||match.getOne()==null||match.getTwo()==null) throw new InvalidDrawsException("Can not get winner from a null match or nor a match containing a null player");
        if(match.getOne().equals(bye)) return match.getTwo();
        else if (match.getTwo().equals(bye)) return match.getOne();
        else return new Player(match.getOne().getName()+"|"+match.getTwo().getName());
    }

    private void check(int numberOfPositions){
        if(numberOfPositions<2) throw new InvalidDrawsException("There are less than two");
        if(Integer.highestOneBit(numberOfPositions)!=numberOfPositions) throw new InvalidDrawsException("The number of players is not in base 2");
    }

    private List<Match> getFirstRound(List<Player> players,int numberOfPositions, int numberOfRounds){
       List<Match> firstRound = new ArrayList<>();
        List<Integer> indexes = generateOrder(numberOfPositions, numberOfRounds);
        indexes.replaceAll(n->n-1);
       for(int i = 0; i<players.size();i=i+2){
        Match match = new Match(players.get(indexes.get(i)), players.get(indexes.get(i+1))); 
        firstRound.add(match);
        this.matches.add(match);
       }
        return firstRound;
    }



    private List<Integer> generateOrder(int numberOfPositions, int numberOfRounds){
         List<Integer> orders = new ArrayList<>(List.of(1, 2));
        
        boolean below = true;
        for(int i = 1; i<numberOfRounds;i++){
           
            int oS = orders.size();
            int iterator = 1;
            for(int e= 0; e<oS;e++){
                
                if(below){
                  orders.add(iterator,oS*2+1-orders.get(iterator-1));
                  iterator++;
                }  
                else{
                   orders.add(iterator, oS*2+1-orders.get(iterator));
                   iterator +=3;
                } 
                
                below=!below;
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
