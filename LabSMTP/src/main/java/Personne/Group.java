package Personne;

import java.util.ArrayList;

public class Group {
    private ArrayList<Victim> group;

    public Group(){
        group = new ArrayList<Victim>();
    }

    public Group(ArrayList<Victim> group){
        this.group = group;
    }

    public ArrayList<Victim> getGroup(){
        return group;
    }

    public void addInGroup(Victim person){
        group.add(person);
    }



}
