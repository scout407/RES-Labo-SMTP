package smtp;

import mail.EMail;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Our SMTP client
 * @author Fluckiger
 */
public class SmtpClient implements SmtpClient_I {

    private static final Logger LOG = Logger.getLogger(SmtpClient.class.getName());

    private Socket socket;
    private PrintWriter pWriter;
    private BufferedReader bReader;

    private String smtpServerAddress;
    private int smtpServerPort;

    private static final String endOfLine = "\r\n";

    public SmtpClient(String smtpServerAddress, int smtpServerport){
        this.smtpServerAddress = smtpServerAddress;
        this.smtpServerPort = smtpServerport;
    }

    public void sendMail(EMail mail) throws IOException {
        LOG.info("Sending a message via SMTP protocol");
        Socket socket = new Socket(smtpServerAddress, smtpServerPort);
        pWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
        bReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
        String line = bReader.readLine();
        LOG.info(line);
        pWriter.printf("EHLO localhost" + endOfLine);

        line = bReader.readLine();
        LOG.info(line);

        ArrayList<String> toAddresses = mail.getTo();
        ArrayList<String> ccAddresses = mail.getCc();


        // throw an error if we receive other message from server than 250
        if(!line.startsWith("250")){
            throw new IOException("SMTP error :" + line);
        }

        while(line.startsWith("250-")){
            line = bReader.readLine();
            LOG.info(line);
        }
        pWriter.write("MAIL FROM: " + mail.getFrom() + endOfLine);
        pWriter.flush();
        line = bReader.readLine();
        LOG.info(line);

        for(String to : toAddresses){
            pWriter.write("RCPT TO: " + to + endOfLine);
            pWriter.flush();
            line = bReader.readLine();
            LOG.info(line);
        }

        // Ajoute les adresse de en copie.
        if(ccAddresses != null) {
            for (String cc : ccAddresses) {
                pWriter.write("RCPT TO: " + cc + endOfLine);
                pWriter.flush();
                line = bReader.readLine();
                LOG.info(line);
            }
        }




        // Send Data
        pWriter.write("DATA" + endOfLine);
        pWriter.flush();

        line = bReader.readLine();
        LOG.info(line);
        pWriter.write("Content-Type: text/plain; charset=UTF-8" + endOfLine);
        pWriter.write("From: " + mail.getFrom() + endOfLine);
        pWriter.write("To: " + toAddresses.get(0));
        for(int i = 1; i < toAddresses.size(); ++i){
            pWriter.write(", " + toAddresses.get(i));
        }
        pWriter.write(endOfLine);

        if(ccAddresses != null && !ccAddresses.isEmpty()){
            System.out.println(ccAddresses);
            pWriter.write("Cc: " + ccAddresses.get(0));
            for(int i = 1; i < ccAddresses.size(); ++i){
                pWriter.write(", " + ccAddresses.get(i));
            }
            pWriter.write(endOfLine);
        }



        pWriter.write("Subject: " + mail.getSubject());
        pWriter.write(endOfLine + endOfLine);
        pWriter.write(mail.getBody());
        pWriter.write(endOfLine + "." + endOfLine);
        pWriter.flush();

        line = bReader.readLine();
        LOG.info(line);

        pWriter.write("QUIT" + endOfLine);
        pWriter.flush();

        bReader.close();
        pWriter.close();
        socket.close();
    }
}
