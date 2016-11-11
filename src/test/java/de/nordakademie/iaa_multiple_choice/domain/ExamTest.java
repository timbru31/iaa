package de.nordakademie.iaa_multiple_choice.domain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

/**
 * Test for different exam utility methods.
 *
 * @author Tim Brust
 *
 */
public class ExamTest {
    private Exam exam;

    @Before
    public void setUp() {
        exam = new Exam();
    }

    /**
     * Test if exam is due dated (testing both start and end dates).
     */
    @Test
    public void testDueDated() {
        final LocalDate today = LocalDate.now();
        final LocalDate tomorrow = today.plusDays(1);
        final LocalDate yesterday = today.minusDays(1);
        final LocalDate oneWeekLater = today.plusWeeks(1);

        exam.setStartDate(today);
        exam.setEndDate(oneWeekLater);
        assertTrue("Expected that exam is due dated (starts today)", exam.isDueDated());

        exam.setStartDate(yesterday);
        assertTrue("Expected that exam is due dated (started yesterday)", exam.isDueDated());

        exam.setStartDate(tomorrow);
        assertFalse("Expected that exam is not due dated (starts tomorrow)", exam.isDueDated());

        exam.setStartDate(yesterday);
        exam.setEndDate(yesterday);
        assertFalse("Expected that exam is not due dated (ended yesterday)", exam.isDueDated());

        exam.setStartDate(yesterday);
        exam.setEndDate(today);
        assertTrue("Expected that exam is due dated (ends today)", exam.isDueDated());
    }

    /**
     * Test if an example is editable.
     */
    @Test
    public void testEditable() {
        final LocalDate today = LocalDate.now();
        exam.setStartDate(today);
        assertFalse("Expected that exams that start today are not editable", exam.isEditable());

        final LocalDate yesterday = today.minusDays(1);
        exam.setStartDate(yesterday);
        assertFalse("Expected that exams that started yesterday are not editable", exam.isEditable());

        final LocalDate tomorrow = today.plusDays(1);
        exam.setStartDate(tomorrow);
        assertTrue("Expected that exams that start tomorrow are editable", exam.isEditable());
    }
}
