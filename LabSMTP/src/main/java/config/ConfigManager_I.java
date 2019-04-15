package config;

import mail.EMail;
import Personne.Victim;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author nfluckiger
 */
public interface ConfigManager_I {

    public String getServerAddress();
    public int getServerPort();
    public  int getNumberOfGroup();

    public List<Victim> getWitnessToCC();

    public List<String> getMessages();

    public  List<Victim> getVictims();
}
