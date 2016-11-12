package de.nordakademie.iaa_multiple_choice.domain;

import lombok.Getter;

/**
 * CreditPointEnum used for credit point amount of exam.
 *
 * @author Tim Brust
 */
public enum CreditPointType {
    HALF(0.5), THREEQUARTER(0.75), ONE(1);
    @Getter
    private double value;

    CreditPointType(final double value) {
        this.value = value;
    }
}
