package de.nordakademie.iaa_multiple_choice.web.exception;

/**
 * Exception for disabled registration. The configuration is possible via Spring properties.
 *
 * @author Tim Brust
 *
 */
public class RegistrationDisabledException extends RuntimeException {
    private static final long serialVersionUID = 4477813645281456571L;
}
