package de.nordakademie.iaa_multiple_choice.web.util;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotation for the interceptors to check that the user is a lecturer and not a student.
 *
 * @author Tim Brust
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface LecturerRequired {
    // The right man in the wrong place can make all the difference in the world. - The G-Man (Half Life 2)
}
