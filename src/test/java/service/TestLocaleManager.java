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
        String error403 = localeManager.getValue("servlet.error.403", new Locale("en"));
        assertEquals("Access Denied", error403);

        error403 = localeManager.getValue("servlet.error.403", new Locale("ru"));
        assertEquals("Доступ запрещен", error403);

        assertEquals("Page not found", MessageProvider.STATUS_404_ERROR.toString());
    }
}
