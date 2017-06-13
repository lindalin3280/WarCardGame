package org.linda.war_cardgame.config;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 
 * @author Linda
 *
 */
public class Config {
    static Logger logger = Logger.getLogger(Config.class);

    /**
     * read config file
     * 
     * @param filename
     *            -- full path file name
     * @return Properties -- key-value pairs
     */
    public static Properties readConfig(String filename) {
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream(filename);
            prop.load(input);
        } catch (Exception e) {
            logger.info("filename = " + filename, e);
        }
        try {
            if (input != null)
                input.close();
        } catch (Exception e) {
            logger.info("filename = " + filename, e);
        }
        return prop;
    }

    /**
     * This method is used to get String properties from properties file (eg:
     * application.properties)
     * 
     * @param props
     * @param key
     * @param isRequiredField
     * @return
     */
    public static String get(Properties props, String key,
            boolean isRequiredField) {
        String res = null;
        try {
            res = new String(props.getProperty(key).trim()
                    .getBytes("iso8859-1"), "UTF-8");
        } catch (Exception e) {
            logger.error(key + " is missing in config file");
            System.exit(-1);
        }
        if (isRequiredField && (res == null || res.isEmpty())) {
            logger.error(key + " is a required field in config file");
            System.exit(-1);
        }
        return res;
    }

    /**
     * This method is used to get int properties from properties file (eg:
     * application.properties)
     * 
     * @param props
     * @param key
     * @param isRequiredField
     * @return
     */
    public static int getInt(Properties props, String key,
            boolean isRequiredField) {
        return Integer.parseInt(get(props, key, isRequiredField));
    }
}
