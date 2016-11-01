package de.nordakademie.iaa_multiple_choice.domain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Test;

public class ExamTest {

    @Test
    public void testEditable() {
        final Exam exam = new Exam();
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
