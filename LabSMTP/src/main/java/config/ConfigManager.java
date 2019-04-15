package config;


import java.io.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.List;
import mail.Victim;

/**
 *
 * @author nfluckiger
 */
public class ConfigManager implements ConfigManager_I{


    private String smtpServerAddress;
    private int smtpServerPort;
    private int numberOfGroup;
    private String witnessestoCC;

    public ConfigManager(){
        Properties prop = new Properties();

        try {
            FileInputStream input = new FileInputStream("config.properties");

            // load a properties file
            prop.load(input);
            smtpServerAddress = new String(prop.getProperty("smtpServerAddress"));

            smtpServerPort = Integer.valueOf( prop.getProperty("smtpServerPort"));
            numberOfGroup = Integer.valueOf( prop.getProperty("numberOfGroup"));
            witnessestoCC = prop.getProperty("witnessestoCC");

        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getServerAddress(){
        return smtpServerAddress;
    }
    public int getServerPort(){
        return smtpServerPort;
    }

    public  int getNumberOfGroup(){
        return numberOfGroup;
    }

    public List<Victim> getWitnessToCC(){
        List<Victim> returnvalues = new ArrayList<>();
        String valueToParse = witnessestoCC;
        while(valueToParse.indexOf(",") > -1){
            returnvalues.add(parseVictimFromEmail(valueToParse.substring(0, valueToParse.indexOf(","))));
            valueToParse = valueToParse.substring(valueToParse.indexOf(",")+1);
        }
        returnvalues.add(parseVictimFromEmail(valueToParse));


        return returnvalues;
    }

    public List<String> getMessages(){
           List<String> returnValue = new ArrayList<String>();
            try {
                BufferedReader bReader = new BufferedReader(new InputStreamReader(new FileInputStream("messages.utf8"), "UTF-8"));

                String line;
                String str = "";
                while ((line = bReader.readLine()) != null){

                    if(line.contains("Subject:")){
                        line = line.replace("Subject:","");
                        returnValue.add(line);
                    }else {
                        if(!line.contains("**")){
                            if(!line.equals("")){
                                str += line + "\r\n";
                            }
                        }
                        else{
                            returnValue.add(new String(str));
                            str = "";
                        }
                    }
                }
            }catch (IOException e){
                System.out.println(e.getMessage());
            }

           return returnValue;
    }

    public  List<Victim> getVictims(){
        List<Victim> returnValue = new ArrayList<>();

        try {
            BufferedReader bReader = new BufferedReader(new InputStreamReader(new FileInputStream("victims.utf8"), "UTF-8"));

            String line;
            while ((line = bReader.readLine()) != null){
               returnValue.add(parseVictimFromEmail(line));
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

        return returnValue;
    }

    private Victim parseVictimFromEmail(String email){
        StringBuilder strBuild = new StringBuilder(email.substring(0,email.indexOf(".")));
        String firstname = strBuild.replace(0, 1, strBuild.substring(0,1).toUpperCase()).toString();
        strBuild = new StringBuilder(email.substring(email.indexOf(".")+1, email.indexOf("@")));
        String lastname = strBuild.replace(0,1, strBuild.substring(0,1).toUpperCase()).toString();
        return new Victim(firstname, lastname, email);
    }
}
