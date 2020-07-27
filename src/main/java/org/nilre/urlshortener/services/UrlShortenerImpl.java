package org.nilre.urlshortener.services;

import org.nilre.urlshortener.data.IUrlRepository;
import org.nilre.urlshortener.error.ApplicationException;
import org.nilre.urlshortener.shorteners.ShortenerExecutor;
import org.nilre.urlshortener.shorteners.validation.Validator;
import org.springframework.stereotype.Service;

@Service
public class UrlShortenerImpl implements IUrlShortenerService {

    private final ShortenerExecutor shortenerExecutor;

    private final IUrlRepository urlRepository;

    private final Validator validator;

    public UrlShortenerImpl(ShortenerExecutor shortenerExecutor, IUrlRepository urlRepository, Validator validator) {
        this.shortenerExecutor = shortenerExecutor;
        this.urlRepository = urlRepository;
        this.validator = validator;
    }

    @Override
    public String getShorterUrl(String url) {
        String value = url;
        while (true) {
            String shortCode = shortenerExecutor.executeShorting(value);
            String originalUrl = urlRepository.findOriginalUrl(shortCode);
            if (originalUrl == null) {
                urlRepository.saveShortUrl(url, shortCode);
                if (url.equals(urlRepository.findOriginalUrl(shortCode))) {
                    return shortCode;
                }
            }

            if (url.equals(originalUrl)) {
                return shortCode;
            }

            value = shortCode;
        }
    }

    @Override
    public String getOriginalUrl(String shortUrl) {
        if (validator.validShortUrl(shortUrl)) {
            String originalUrl = urlRepository.findOriginalUrl(shortUrl);
            if (originalUrl != null) {
                return originalUrl;
            }
            throw new ApplicationException("That 'alias' is not associated with any original Url!!");
        } else {
            throw new ApplicationException("Invalid 'alias' format");
        }
    }
}
