package org.nilre.urlshortener.shorteners.validation;

import org.springframework.stereotype.Component;

@Component
public class GoogleValidator implements IValidator {
    @Override
    public boolean validShortUrl(String shortUrl) {
        return shortUrl.length() == 5 && shortUrl.replaceAll("[a-zA-Z]", "").isEmpty();
    }
}
