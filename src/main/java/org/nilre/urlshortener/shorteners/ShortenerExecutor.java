package org.nilre.urlshortener.shorteners;

import org.nilre.urlshortener.shorteners.utils.ApplicationException;

public class ShortenerExecutor {

    private IShortener firstShortener;

    public void setFirstShortener(IShortener firstShortener) {
        this.firstShortener = firstShortener;
    }

    public String executeShorting(String url) {
        if (firstShortener != null) {
            return firstShortener.shortUrl(url);
        }
        throw new ApplicationException("Bad initialization, there is a default shortener");
    }
}
