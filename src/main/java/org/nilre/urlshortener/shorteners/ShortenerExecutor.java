package org.nilre.urlshortener.shorteners;

public class ShortenerExecutor {

    private IShortener firstShortener;

    public IShortener getFirstShortener() {
        return firstShortener;
    }

    public void setFirstShortener(IShortener firstShortener) {
        this.firstShortener = firstShortener;
    }

    public String executeShorting(String url) {
        if (firstShortener != null) {
            return firstShortener.shortUrl(url);
        }
        throw new RuntimeException("Bad initialization, there is a default shortener");
    }
}
