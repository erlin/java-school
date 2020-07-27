package org.nilre.urlshortener.shorteners.validation;

import org.springframework.stereotype.Service;

@Service
public class Validator {

    private IValidator[] validators;

    public Validator(IValidator[] validators) {
        this.validators = validators;
    }

    public boolean validShortUrl(String shortUrl) {
        for(IValidator validator : validators) {
            if(validator.validShortUrl(shortUrl)) {
                return true;
            }
        }
        return false;
    }
}
