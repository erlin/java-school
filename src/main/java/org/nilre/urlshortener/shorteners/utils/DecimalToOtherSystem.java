package org.nilre.urlshortener.shorteners.utils;

import java.math.BigInteger;

public class DecimalToOtherSystem {
    public static String getRepresentationWithLimitedSize(BigInteger value, String system, String alphabet, int outputSize) {
        StringBuilder result = new StringBuilder("");
        BigInteger internal = (value.compareTo(BigInteger.ZERO) < 0) ? value.negate() : new BigInteger(value.toString());
        BigInteger baseSystem = new BigInteger(system);
        while (internal.compareTo(BigInteger.ZERO) >= 0 && result.length() < outputSize) {
            BigInteger[] quotientReminder = internal.divideAndRemainder(baseSystem);
            int alphabetIndex = quotientReminder[1].intValue();
            result.append(alphabet.charAt(alphabetIndex));
            internal = quotientReminder[0];
        }
        return result.toString();
    }

}
