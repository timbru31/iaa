package de.nordakademie.iaa_multiple_choice.domain;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class QuestionTest {
    private Question question;

    @Before
    public void setUp() {
        final Answer correct = new Answer();
        correct.setRightAnswer(true);
        final Answer correctTwo = new Answer();
        correctTwo.setRightAnswer(true);
        final Answer wrong = new Answer();

        question = new Question();
        question.setAnswers(new ArrayList<Answer>());
        question.addAnswer(wrong);
        question.addAnswer(correctTwo);
        question.addAnswer(correct);
    }

    @Test
    public void testCorrectAnswers() {
        assertEquals("Expected to have two correct answers", 2, question.getCorrectAnswers().size());
    }
}
