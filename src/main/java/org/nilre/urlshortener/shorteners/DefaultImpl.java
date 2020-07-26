package org.nilre.urlshortener.shorteners;

public class DefaultImpl extends IShortener {

    @Override
    public boolean applicable(String url) {
        return true;
    }

    @Override
    public String shortUrlInternal(String url) {
        return url.replaceAll("[^b-zB-Z&&[^eiouEIOU]]", "");
    }
}
