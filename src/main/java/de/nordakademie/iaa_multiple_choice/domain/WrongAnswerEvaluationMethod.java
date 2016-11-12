package de.nordakademie.iaa_multiple_choice.domain;

import lombok.Getter;

/**
 * @author Yannick Rump defines wrong answers
 */
public enum WrongAnswerEvaluationMethod {
    SUBTRACTION("evaluation.subtraction", "evaluation.subtractionShort"), NO_SUBTRACTION("evaluation.noSubtraction",
            "evaluation.noSubtractionShort");
    @Getter
    private String text;
    @Getter
    private String shortText;

    private WrongAnswerEvaluationMethod(final String text, final String shortText) {
        this.text = text;
        this.shortText = shortText;
    }
}
