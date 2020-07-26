package org.nilre.urlshortener.data;

import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.TreeMap;

@Repository
public class UrlRepositoryImpl implements IUrlRepository {

    private Map<String, String> originalToShortUrl = new TreeMap<>();
    private Map<String, String> shortToOriginalUrl = new TreeMap<>();

    @Override
    public String findShortUrl(String originalUrl) {
        return originalToShortUrl.get(originalUrl);
    }

    @Override
    public String saveShortUrl(String originalUrl, String shortUrl) {
        originalToShortUrl.put(originalUrl, shortUrl);
        shortToOriginalUrl.put(shortUrl, originalUrl);
        return shortUrl;
    }

    @Override
    public String findOriginalUrl(String shortUrl) {
        return shortToOriginalUrl.get(shortUrl);
    }
}
