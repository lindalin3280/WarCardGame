package org.linda.war_cardgame;

import java.io.File;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.linda.war_cardgame.config.Config;
import org.linda.war_cardgame.pojo.War;

public class App {
	static Logger logger = Logger.getLogger(App.class);
	private static final String projectPath = System.getProperty("user.dir");
	private static final String fileSeparator = File.separator;
	static int numberOfPlayers;
	static int numberOfSuits;
	static int numberOfRank;
	static String[] namesOfPlayers;
	
	public static void main(String[] args) {
		setUpProperties();
		new War().play(numberOfSuits, numberOfRank, numberOfPlayers, namesOfPlayers);
	}
	public static void setUpProperties() {
		Properties props = Config.readConfig(projectPath + fileSeparator + "src" + fileSeparator + "main"
				+ fileSeparator + "resources" + fileSeparator + "application.properties");		
		numberOfPlayers = Config.getInt(props, "numberOfPlayers", true);
		logger.info("numberOfPlayers = " + numberOfPlayers);
		numberOfSuits = Config.getInt(props, "numberOfSuits", true);
	    logger.info("numberOfSuits = " + numberOfSuits);
	    numberOfRank = Config.getInt(props, "numberOfRank", true);
	    logger.info("numberOfRank = " + numberOfRank);
	    namesOfPlayers = Config.get(props, "namesOfPlayers", true).split(", *");
	}
}
