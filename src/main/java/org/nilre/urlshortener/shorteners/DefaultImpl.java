package org.nilre.urlshortener.shorteners;

import org.nilre.urlshortener.error.ApplicationException;

public class DefaultImpl extends IShortener {

    @Override
    public boolean applicable(String url) {
        return true;
    }

    @Override
    public String shortUrlInternal(String url) {
        String shortUrl = url.replaceAll("[^b-zB-Z&&[^eiouEIOU]]", "");
        if (shortUrl.isEmpty()) {
            throw new ApplicationException("This url: " + url + " is not reducible by our algorithms");
        }
        return shortUrl;
    }
}
