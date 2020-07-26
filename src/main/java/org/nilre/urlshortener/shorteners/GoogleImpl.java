package org.nilre.urlshortener.shorteners;

import org.springframework.stereotype.Service;

@Service
public class GoogleImpl extends IShortener {

    @Override
    public boolean applicable(String url) {
        return url.contains("google");
    }

    @Override
    public String shortUrlInternal(String url) {
        return url; //TODO
    }
}
