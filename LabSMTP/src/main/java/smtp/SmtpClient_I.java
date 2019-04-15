package smtp;

import mail.EMail;

import java.io.IOException;

/**
 * The Interface of our Smtpclient
 * @author nfluckiger
 */
public interface SmtpClient_I {
    public void sendMail(EMail mail) throws IOException;
}
