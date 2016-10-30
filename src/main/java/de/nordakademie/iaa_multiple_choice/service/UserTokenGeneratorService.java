package de.nordakademie.iaa_multiple_choice.service;

import java.math.BigInteger;
import java.security.SecureRandom;

import org.springframework.stereotype.Service;

/**
 * Secure token generator. Based on http://stackoverflow.com/a/41156/1902598
 *
 * @author erickson (https://stackoverflow.com/users/3474/erickson)
 *
 */
@Service
public final class UserTokenGeneratorService {
    private final SecureRandom random = new SecureRandom();

    public String generatedKey() {
        return new BigInteger(130, random).toString(32);
    }
}
