package org.nilre.urlshortener.data;

public interface IUrlRepository {
    String saveShortUrl(String originalUrl, String shortUrl);
    String findOriginalUrl(String shortUrl);
}
