package service;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Alexeev on 13.10.2016.
 */

/**
 * LocaleManager provides localization for server messages and errors using ResourceBundle
 */
public class LocaleManager {
    private static final String RESOURCE_PATH = "localization.locale";

    private final static LocaleManager instance = new LocaleManager();

    public static LocaleManager getInstance() {
        return instance;
    }

    /**
     * Get value from ResourceBundle by Locale
     *
     * @param key
     * @param locale
     * @return value
     */
    public String getValue(String key, Locale locale) {
        return ResourceBundle
                .getBundle("localization.locale", locale)
                .getString(key);
    }
}
