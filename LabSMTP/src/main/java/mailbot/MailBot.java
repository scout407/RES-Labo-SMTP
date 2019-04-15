package mailbot;

import config.ConfigManager;
import mail.EMail;
import Prankage.Prank;
import Prankage.PrankGenerator;
import smtp.SmtpClient;
import com.sun.istack.internal.logging.Logger;

import java.io.IOException;
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
                mail = prank.mailMaker();
                client.sendMail(mail);
            }
        } catch (IOException ex){
            LOG.severe(ex.getMessage());
        }


    }
}
