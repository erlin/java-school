package org.nilre.urlshortener.data;

import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UrlRepositoryImpl implements IUrlRepository {

    private ConcurrentHashMap<String, String> shortToOriginalUrl = new ConcurrentHashMap<>();

    @Override
    public String saveShortUrl(String originalUrl, String shortUrl) {
        shortToOriginalUrl.put(shortUrl, originalUrl);
        return shortUrl;
    }

    @Override
    public String findOriginalUrl(String shortUrl) {
        return shortToOriginalUrl.get(shortUrl);
    }
}
