package org.nilre.urlshortener.shorteners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShortenersConfiguration {

    @Autowired
    private IShortener[] shorteners;

    @Bean
    public ShortenerExecutor shortenerExecutor() {

        ShortenerExecutor shortenerExecutor = new ShortenerExecutor();

        if (shorteners.length > 0) {
            shortenerExecutor.setFirstShortener(shorteners[0]);

            for (int i = 1; i < shorteners.length; i++) {
                shorteners[i - 1].setNextShortener(shorteners[i]);
            }

            shorteners[shorteners.length - 1].setNextShortener(new DefaultImpl());
        } else {
            shortenerExecutor.setFirstShortener(new DefaultImpl());
        }

        return shortenerExecutor;
    }

}
