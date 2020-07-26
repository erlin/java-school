package org.nilre.urlshortener.services;

public interface IUrlShortenerService {
    String getShorterUrl(String url);
    String getOriginalUrl(String shortUrl);
}
