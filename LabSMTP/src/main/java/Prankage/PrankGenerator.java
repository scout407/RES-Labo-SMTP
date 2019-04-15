package Prankage;

import Personne.Group;
import Personne.Victim;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.logging.Logger;

import static sun.font.LayoutPathImpl.LOG;

public class PrankGenerator {


    public PrankGenerator(){};


    //génère les groups
    public ArrayList<Group> generateGroup(ArrayList<Victim> victims, int nbrGroup){

        ArrayList<Group> groups = new ArrayList<Group>();

        ArrayList<Victim> shuffledVictim = new ArrayList<Victim>(victims);
        Collections.shuffle(shuffledVictim);

        int numPerson = 0;

        for(int i = 0; i < nbrGroup; ++i){
            groups.add(new Group());
        }

        while(numPerson < shuffledVictim.size()){
            groups.get(numPerson%groups.size()).addInGroup(shuffledVictim.get(numPerson));
            numPerson++;
        }

        return groups;
    }

    //Génère les prank
    public ArrayList<Prank> generatePranks(){

        ArrayList<Prank> pranks = new ArrayList<Prank>();
        ArrayList<String> messages = configManager.getMessages();
        ArrayList<Victim> victims = configManager.getVictims();

        int nbrGroups = configManager.getNumberOfGroup();
        int nbrVictims = victims.size();

        if( nbrVictims / nbrGroups < 3){
            nbrGroups = nbrVictims / 3;
            LOG.warning("Erreur pas assez de victime désigné ou trop de groupe \nvictims : "+ nbrVictims + " \nGroup generated : " + nbrGroups);
        }

        ArrayList<Group> groups = generateGroup(victims, nbrGroups);

        int numMessage = 0;
        Random rand = new Random();

        for(Group group : groups){
            Prank prank = new Prank();

            ArrayList<Victim> members = group.getGroup();
            Collections.shuffle(members);

            Victim sender = members.remove(0);
            prank.setSender(sender);
            prank.addTo(members);

            prank.addCc(configManager.getWitnessToCC());

            numMessage = rand.nextInt(messages.size() / 2) * 2;
            String subject = messages.get(numMessage);
            String message = messages.get(numMessage + 1);

            prank.setSubject(subject);
            prank.setBody(message);
            pranks.add(prank);
        }
        return pranks;
    }

}
