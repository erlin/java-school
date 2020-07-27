package org.nilre.urlshortener.services;

import org.nilre.urlshortener.data.IUrlRepository;
import org.nilre.urlshortener.shorteners.ShortenerExecutor;
import org.springframework.stereotype.Service;

@Service
public class UrlShortenerImpl implements IUrlShortenerService {

    private final ShortenerExecutor shortenerExecutor;

    private final IUrlRepository urlRepository;

    public UrlShortenerImpl(ShortenerExecutor shortenerExecutor, IUrlRepository urlRepository) {
        this.shortenerExecutor = shortenerExecutor;
        this.urlRepository = urlRepository;
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
        return urlRepository.findOriginalUrl(shortUrl);
    }
}
