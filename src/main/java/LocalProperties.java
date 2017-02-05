import java.io.*;
import java.util.*;

import org.apache.log4j.*;

/**
 * Copyright Schantz A/S, all rights reserved
 */

public class LocalProperties {

    public static final String PURCHASES_KEY = "purchases.url";
    private static final String PROPERTIES_FILE = "config.properties";
    private static LocalProperties instance = null;
    private Properties props;

    private LocalProperties() {
        props = new Properties();
        InputStream configInStream = getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE);
        if(configInStream != null) {
            try {
                props.load(configInStream);
            } catch (IOException e) {
                Logger.getLogger(LocalProperties.class).fatal("Properties file not found");
            }
        }
    }

    public static LocalProperties getInstance() {
        if(instance == null) {
            instance = new LocalProperties();
        }

        return instance;
    }

    public Properties getProps() {
        return props;
    }
}
