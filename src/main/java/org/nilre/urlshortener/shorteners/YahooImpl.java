package org.nilre.urlshortener.shorteners;

import org.nilre.urlshortener.error.ApplicationException;
import org.nilre.urlshortener.shorteners.utils.DecimalToOtherSystem;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class YahooImpl extends IShortener {

    private static final String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTEUVXYZ";
    private static final String NUMERICAL_SYSTEM = "62";

    @Override
    public boolean applicable(String url) {
        return url.contains("yahoo");
    }

    @Override
    public String shortUrlInternal(String url) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("MD5");
            BigInteger input = new BigInteger(digest.digest(url.getBytes()));
            return DecimalToOtherSystem.getRepresentationWithLimitedSize(input, NUMERICAL_SYSTEM, ALPHABET, 7);
        } catch (NoSuchAlgorithmException e) {
            throw new ApplicationException("There was a problem getting the MessageDigest instance");
        }
    }
}
