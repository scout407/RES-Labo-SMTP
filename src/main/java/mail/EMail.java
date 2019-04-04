package mail;

import java.util.ArrayList;

public class EMail {
    private String from;
    private ArrayList<String> to;
    private ArrayList<String> cc;
    private String subject;
    private String body;

    public EMail(){}

    public EMail(String from, ArrayList<String> to){
        this.from = from;
        this.to = to;
    }

    public EMail(String from, ArrayList<String> to, String subject, String body){
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.body = body;
    }

    public EMail(String from, ArrayList<String> to, ArrayList<String> cc, String subject, String body){
        this.from = from;
        this.to = to;
        this.cc = cc;
        this.subject = subject;
        this.body = body;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getFrom() {
        return from;
    }

    public void setTo(ArrayList<String> to) {
        this.to = to;
    }

    public ArrayList<String> getTo(){
        return to;
    }

    public void setCc(ArrayList<String> cc) {
        this.cc = cc;
    }

    public ArrayList<String> getCc(){
        return cc;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBody(){
        return body;
    }
}