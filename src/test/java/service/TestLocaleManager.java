package service;

import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.assertEquals;

/**
 * Created by Alexeev on 14.10.2016.
 */
public class TestLocaleManager {
    private LocaleManager localeManager = LocaleManager.getInstance();

    @Test
    public void testGetValue() {
        localeManager.changeLocale(new Locale("en"));
        String error403 = localeManager.getValue("servlet.error.403");

        assertEquals("Access Denied", error403);

        localeManager.changeLocale(new Locale("ru"));
        error403 = localeManager.getValue("servlet.error.403");

        assertEquals("Доступ запрещен", error403);
    }

    @Test
    public void testErrorCodes() {
        localeManager.changeLocale(new Locale("en"));
        assertEquals("Page not found", MessageProvider.STATUS_404_ERROR.toString());

        localeManager.changeLocale(new Locale("ru"));
        assertEquals("Страница не найдена", MessageProvider.STATUS_404_ERROR.toString());
    }
}
