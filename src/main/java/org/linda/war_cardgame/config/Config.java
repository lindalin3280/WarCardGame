package org.linda.war_cardgame.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

/**
 * 
 * @author Linda
 *
 */
public class Config {
    private static final Logger logger = Logger.getLogger(Config.class);

    /**
     * read config file
     * 
     * @param filename
     *            -- full path file name
     * @return
     */
    public static Properties readConfig(String filename) {
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream(filename);
            // load a properties file
            prop.load(input);
            input.close();
        } catch (Exception e) {
            logger.info(e);
        } finally {
            if (input != null)
                try {
                    input.close();
                } catch (IOException e) {
                    logger.info(e);
                }
        }
        return prop;
    }

    /**
     * get a key field from props
     * 
     * @param props
     * @param key
     * @param isRequiredField
     *            means whether nonempty value is allowed in this field
     * @return a key field from props
     */
    public static String get(Properties props, String key,
            boolean isRequiredField) {
        String res = null;
        try {
            res = props.getProperty(key);
            if (res != null) {
                res = res.trim();
            }
        } catch (Exception e) {
            logger.error(key + " is missing in config file");
            System.exit(-1);
        }
        if (isRequiredField && (res == null || res.isEmpty())) {
            logger.error(key + " is a required field in config file");
            System.exit(-1);
        }
        logger.info(key + " is set to " + res);
        return res;
    }

    /**
     * get a key field from props, nonempty value id not allowed
     * 
     * @param props
     * @param key
     * @return
     */
    public static String get(Properties props, String key) {
        return get(props, key, true);
    }

    /**
     * get an int value for given key in props
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

    /**
     * get an int value from props, nonempty value is not allowed
     * 
     * @param props
     * @param key
     * @return
     */
    public static int getInt(Properties props, String key) {
        return getInt(props, key, true);
    }

    /**
     * get a boolean value from props
     * 
     * @param props
     * @param key
     * @param isRequiredField
     * @return
     */
    public static boolean getBoolean(Properties props, String key,
            boolean isRequiredField) {
        return Boolean.parseBoolean(get(props, key, isRequiredField));
    }

    /**
     * get a boolean value from props, nonempty value is not allowed
     * 
     * @param props
     * @param key
     * @return
     */
    public static boolean getBoolean(Properties props, String key) {
        return getBoolean(props, key, true);
    }

    /**
     * get a set of strings in the key field of props
     * 
     * @param props
     * @param key
     * @return
     */
    public static Set<String> getSet(Properties props, String key) {
        Set<String> set = new HashSet<>();
        String[] splits = get(props, key).split(",\\s*");
        for (String split : splits) {
            logger.info("load " + key + " " + split);
            set.add(split);
        }
        return set;
    }

    /**
     * get a map<String, Boolean> in the key field of props
     * 
     * @param props
     * @param key
     * @return
     */
    public static Map<String, Boolean> getMap(Properties props, String key,
            boolean initalValue) {
        Map<String, Boolean> map = new HashMap<>();
        String[] splits = get(props, key).split(",\\s*");
        for (String split : splits) {
            logger.info("load " + key + " " + split);
            map.put(split, initalValue);
        }
        return map;
    }
}
