package org.linda.war_cardgame;

import java.io.File;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.linda.war_cardgame.config.Config;
import org.linda.war_cardgame.impl.WarImpl;

/**
 * The App class includes the main method.
 * 
 * @author Linda
 *
 */
public class App {
    static Logger logger = Logger.getLogger(App.class);
    private static final String projectPath = System.getProperty("user.dir");
    private static final String fileSeparator = File.separator;
    static String[] suits;
    static String[] ranks;
    static int[] valuesOfRanks;
    static String[] players;

    public static void main(String[] args) {
        setUpProperties();
        new WarImpl().startGame(suits, ranks, valuesOfRanks, players);
    }

    /**
     * This method is used to get players, suits, ranks, etc. from
     * application.properties
     */
    public static void setUpProperties() {
        Properties props = Config.readConfig(projectPath + fileSeparator
                + "src" + fileSeparator + "main" + fileSeparator + "resources"
                + fileSeparator + "application.properties");

        players = Config.get(props, "players").split(", *");
        suits = Config.get(props, "suits").split(", *");
        ranks = Config.get(props, "ranks").split(", *");
        String[] valuesOfRanks_tokens = Config.get(props, "valuesOfRanks")
                .split(", *");

        if (ranks.length != valuesOfRanks_tokens.length) {
            logger.error("length of ranks must equals length of valuesOfRanks");
        }

        valuesOfRanks = new int[valuesOfRanks_tokens.length];

        for (int i = 0; i < valuesOfRanks_tokens.length; i++) {
            valuesOfRanks[i] = Integer.parseInt(valuesOfRanks_tokens[i]);
        }
        WarImpl.numFaceDownCardsIfDraw = Config.getInt(props,
                "numFaceDownCardsIfDraw");
    }
}
