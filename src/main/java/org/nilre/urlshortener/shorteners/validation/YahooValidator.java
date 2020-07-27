package org.nilre.urlshortener.shorteners.validation;

import org.springframework.stereotype.Component;

@Component
public class YahooValidator implements IValidator {
    @Override
    public boolean validShortUrl(String shortUrl) {
        return shortUrl.length() == 7 && shortUrl.replaceAll("[a-zA-Z0-9]", "").isEmpty();
    }
}
