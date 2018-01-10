import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class GameConfig {

    private final static Logger logger = LogManager.getLogger(GameConfig.class.getName());

    private Integer nbItems;
    private Integer nbTrials;

    public GameConfig() {

        GameConfig.logger.info("Load app config from config.properties.");

        Configurations configs = new Configurations();
        try
        {
            Configuration config = configs.properties(new File("config.properties"));

            // access configuration properties

            this.nbItems = config.getInt("nbItems");
            this.nbTrials = config.getInt("nbTrials");

        }
        catch (ConfigurationException cex)
        {
            GameConfig.logger.fatal("Load of configuration failed. Exit program.");
        }

    }

    public Integer getNbItems () {
        return this.nbItems;
    }

    public Integer getNbTrials () {
        return this.nbTrials;
    }

}
