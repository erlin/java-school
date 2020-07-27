package org.nilre.urlshortener.shorteners.validation;

import org.springframework.stereotype.Component;

@Component
public class DefaultValidator implements IValidator {
    @Override
    public boolean validShortUrl(String shortUrl) {
        return shortUrl.replaceAll("[b-zB-Z&&[^eiouEIOU]]", "").isEmpty();
    }
}
