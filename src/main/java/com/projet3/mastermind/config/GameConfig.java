package com.projet3.mastermind.config;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

import java.io.File;

public class GameConfig {

    private final static Logger logger = LogManager.getLogger(GameConfig.class.getName());

    private Integer nbItemsMastermind;
    private Integer nbTrialsMastermind;
    private Integer nbItemsSearch;
    private Integer nbTrialsSearch;
    private Boolean modeDev;

    private Boolean isForceModeDev = false;

    public GameConfig() {

        GameConfig.logger.info("Load app projet3.mastermind.config from projet3.mastermind.config.properties.");

        Configurations configs = new Configurations();

        try
        {
            Configuration config = configs.properties(new File("config.properties"));

            // access configuration properties

            this.nbItemsMastermind = config.getInt("mastermind.nbItems");
            this.nbTrialsMastermind = config.getInt("mastermind.nbTrials");
            this.nbItemsSearch = config.getInt("search.nbItems");
            this.nbTrialsSearch = config.getInt("search.nbTrials");
            this.modeDev = config.getBoolean("modeDev");

        }
        catch (ConfigurationException cex)
        {
            GameConfig.logger.fatal("Load of configuration failed. Exit program.");
        }

    }

    public Integer getNbItemsMastermind() {
        return this.nbItemsMastermind;
    }

    public Integer getNbTrialsMastermind() {
        return this.nbTrialsMastermind;
    }

    public Integer getNbItemsSearch() {
        return this.nbItemsSearch;
    }

    public Integer getNbTrialsSearch() {
        return this.nbTrialsSearch;
    }

    public void forceModeDev() {
        this.isForceModeDev = true;
    }

    public Boolean getModeDev () {
        if (this.isForceModeDev) {
            return true;
        }
        return this.modeDev;
    }

    public void forceErrorLevel () {
        Configurator.setRootLevel(Level.ERROR);
    }

}
