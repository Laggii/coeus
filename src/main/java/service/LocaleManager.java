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

    //English by default
    private ResourceBundle bundle = ResourceBundle.getBundle(RESOURCE_PATH, Locale.ENGLISH);

    public static LocaleManager getInstance() {
        return instance;
    }

    /**
     * Change ResourceBundle by locale
     * @param locale
     */
    public void changeLocale(Locale locale) {
        bundle = ResourceBundle.getBundle("localization.locale", locale);
    }

    /**
     * Get value from ResourceBundle
     * @param key
     * @return value
     */
    public String getValue(String key) {
        return bundle.getString(key);
    }
}
