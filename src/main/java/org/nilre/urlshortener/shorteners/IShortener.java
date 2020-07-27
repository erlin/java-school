package org.nilre.urlshortener.shorteners;

import org.nilre.urlshortener.error.ApplicationException;

public abstract class IShortener {

    private IShortener nextShortener;

    public void setNextShortener(IShortener nextShortener) {
        this.nextShortener = nextShortener;
    }

    public String shortUrl(String url) {
        if (applicable(url)) {
            return shortUrlInternal(url);
        } else {
            if (nextShortener != null) {
                return nextShortener.shortUrl(url);
            }
        }
        throw new ApplicationException("Invalid chain state, bad initialization, there is a default Shortener");
    }

    public abstract boolean applicable(String url);

    public abstract String shortUrlInternal(String url);

}
