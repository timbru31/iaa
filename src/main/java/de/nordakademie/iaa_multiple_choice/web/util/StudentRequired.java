package de.nordakademie.iaa_multiple_choice.web.util;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotation for the interceptors to check that the user is a student and not a lecturer.
 *
 * @author Tim Brust
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface StudentRequired {
    // Did I ever tell you what the definition of insanity is? Insanity is doing the exact... same [...] thing... over
    // and over again expecting... [things] to change... That. Is. Crazy. [...] - Vaas Montenegro (Far Cry 3)
}
