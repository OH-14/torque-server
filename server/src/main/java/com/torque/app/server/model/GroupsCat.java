package com.torque.app.server.model;
import lombok.Data; 
import lombok.NonNull;
import lombok.AllArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
public class GroupsCat {
    @NonNull
    //name of the category
    private String name;
    @NonNull
    private List<Group> groups;
    
}
