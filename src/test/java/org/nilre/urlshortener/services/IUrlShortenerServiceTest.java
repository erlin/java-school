package org.nilre.urlshortener.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.nilre.urlshortener.error.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class IUrlShortenerServiceTest {

    @Autowired
    IUrlShortenerService shortenerService;

    @Test
    void getShorterUrl() {
        String shorterGoogleUrl = shortenerService.getShorterUrl("www.google.com");

        assertEquals(5, shorterGoogleUrl.length());
        assertEquals(0, shorterGoogleUrl.replaceAll("[a-zA-Z]", "").length());

        String shorterYahooUrl = shortenerService.getShorterUrl("espanol.yahoo.com");

        assertEquals(7, shorterYahooUrl.length());
        assertEquals(0, shorterYahooUrl.replaceAll("[a-zA-Z0-9]", "").length());

        String shorterOtherUrl = shortenerService.getShorterUrl("www.algo.com");

        assertEquals("wwwlgcm", shorterOtherUrl);
    }

    @Test()
    void emptyShortUrlTest() {
        Assertions.assertThrows(ApplicationException.class, () -> {
            shortenerService.getShorterUrl("");
        });
    }

    @Test()
    void invalidShortUrlTest() {
        Assertions.assertThrows(ApplicationException.class, () -> {
            shortenerService.getOriginalUrl("78mjn");
        });

        Assertions.assertThrows(ApplicationException.class, () -> {
            shortenerService.getOriginalUrl("78mjn?");
        });

        Assertions.assertThrows(ApplicationException.class, () -> {
            shortenerService.getOriginalUrl("78mjnuIOr");
        });
    }

    @Test
    void basicLogicTest() {
        String googleUrl = "www.google.com";

        String shorterGoogleUrl = shortenerService.getShorterUrl(googleUrl);

        String shorterGoogleUrl2 = shortenerService.getShorterUrl(googleUrl);

        assertEquals(shorterGoogleUrl, shorterGoogleUrl2);
    }

}