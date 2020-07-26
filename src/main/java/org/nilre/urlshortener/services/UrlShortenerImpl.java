package org.nilre.urlshortener.services;

import org.nilre.urlshortener.data.IUrlRepository;
import org.nilre.urlshortener.shorteners.ShortenerExecutor;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        return Optional.ofNullable(urlRepository.findShortUrl(url))
                .orElse(urlRepository.saveShortUrl(url, shortenerExecutor.executeShorting(url)));
    }

    @Override
    public String getOriginalUrl(String shortUrl) {
        return urlRepository.findOriginalUrl(shortUrl);
    }
}
