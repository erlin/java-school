package org.nilre.urlshortener.shorteners;

import org.springframework.stereotype.Service;

@Service
public class YahooImpl extends IShortener {

    @Override
    public boolean applicable(String url) {
        return url.contains("yahoo");
    }

    @Override
    public String shortUrlInternal(String url) {
        return url; //TODO
    }
}
