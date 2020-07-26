package org.nilre.urlshortener.data;

public interface IUrlRepository {
    String findShortUrl(String originalUrl);
    String saveShortUrl(String originalUrl, String shortUrl);
    String findOriginalUrl(String shortUrl);
}
