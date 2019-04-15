package Prankage;

import Personne.Victim;
import mail.EMail;

import java.util.ArrayList;

public class Prank {
    private Victim sender;
    private ArrayList<Victim> receiver;
    private ArrayList<Victim> cc;
    private String subject;
    private String body;

    public Prank(){}

    public Prank(Victim sender){
        this.sender = sender;
    }

    public Prank(Victim sender, ArrayList<Victim> receiver, ArrayList<Victim> cc){
        this.sender = sender;
        this.receiver = receiver;
        this.cc = cc;
    }

    public Prank(Victim sender, ArrayList<Victim> receiver, ArrayList<Victim> cc, String subject, String body){
        this(sender, receiver, cc);
        this.subject = subject;
        this.body = body;
    }

    public Victim getSender() {
        return sender;
    }

    public void setSender(Victim sender) {
        this.sender = sender;
    }

    public ArrayList<Victim> getTo() {
        return receiver;
    }

    public void setTo(ArrayList<Victim> receiver) {
        this.receiver = receiver;
    }

    public ArrayList<Victim> getCc() {
        return cc;
    }

    public void setCc(ArrayList<Victim> cc) {
        this.cc = cc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void addTo(Victim victim){
        receiver.add(victim);
    }

    public void addTo(ArrayList<Victim> victim){
        receiver = victim;
    }

    public void addCc(Victim victim){
        cc.add(victim);
    }

    public EMail mailMaker(){
        EMail email = new EMail(sender.getEmail());


        for(Victim victim : receiver){
            email.addTo(victim.getEmail());
        }

        if(!cc.isEmpty()) {
            for (Victim victim : cc) {
                email.addTo(victim.getEmail());
            }
        }

        email.setSubject(subject);
        email.setBody(body);

        return email;
    }
}
