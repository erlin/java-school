package org.nilre.urlshortener.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

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
}