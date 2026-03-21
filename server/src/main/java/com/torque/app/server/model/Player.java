package com.torque.app.server.model;
//necessary imports for not doing manually getters, setters,
//  equals, hashcode, toString, constructors
import lombok.Data;
import java.util.List;
//import lombok.NoArgsConstructor;
//import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import com.torque.app.server.exceptions.*;
@Data
@RequiredArgsConstructor
public class Player {
    @NonNull
    private String name;
    
    private Time lastMatchWas;
    
    //unavailable lapses
    //2D matrix because users may have different unavailable time lapses
    //in different days from the startday of the tournament
    private List<List<Lapse>> unavailableLapsesMatrix;
    
    

    //more methods:
    public boolean hasLastMatch(){
        return !(lastMatchWas==null);
    }

       public Time getLastMatchWas(){
    if(lastMatchWas==null) 
        throw new InvalidPlayerGetException("Player has not last match, Suggestion: use hasLastMatch() or use try-catch to prevent");
    return lastMatchWas;
   }


   public List<List<Lapse>> getUnavailableLapsesMatrix(){
    if(unavailableLapsesMatrix==null) 
        throw new InvalidPlayerGetException("Player has not unavailable spaces, Suggestion: use hasUnavailableSpaces() or use try-catch to prevent");
    return unavailableLapsesMatrix;
   }

   public boolean hasUnavailableSpaces(){
    return unavailableLapsesMatrix!=null;
   }
}
