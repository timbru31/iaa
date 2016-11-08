package de.nordakademie.iaa_multiple_choice.web.util;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotation for the interceptors to check that the user is logged in.
 *
 * @author Tim Brust
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginRequired {
    // In the end, what separates a man from a slave? Money? Power? No... A man chooses, a slave obeys. - Andrew Ryan
    // (BioShock)
}
