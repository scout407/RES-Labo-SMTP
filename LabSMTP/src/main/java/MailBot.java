package mailbot;

import config.ConfigManager;
import mail.EMail;
import Personne.Victim;
import Prankage.Prank;
import Prankage.PrankGenerator;
import smtp.SmtpClient;
import com.sun.istack.internal.logging.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class MailBot{


    public static void main(String [] args) {


        ConfigManager cm = new ConfigManager();
        SmtpClient client = new SmtpClient(cm.getServerAddress(), cm.getServerPort());
        Logger LOG = Logger.getLogger(client.getClass());


        try{


            PrankGenerator prankGenerator = new PrankGenerator(cm);

            List<Prank> pranks = prankGenerator.generatePranks();
            EMail mail;

            for(Prank prank : pranks){
                mail = prank.generateMail();
                // Test body presence LOG.info(mail.getBody());
                client.sendMail(mail);
            }
        } catch (IOException ex){
            LOG.severe(ex.getMessage());
        }


    }
}
