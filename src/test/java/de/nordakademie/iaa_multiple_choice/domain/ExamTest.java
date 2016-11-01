package de.nordakademie.iaa_multiple_choice.domain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.junit.Test;

public class ExamTest {

    @Test
    public void testEditable() {
        final Exam exam = new Exam();
        final LocalDate todayLocalDate = LocalDate.now();
        final Date todayDate = new Date();
        exam.setStartDate(todayDate);
        assertFalse("Expected that exams that start today are not editable", exam.isEditable());

        final LocalDate yesterday = todayLocalDate.minusDays(1);
        exam.setStartDate(Date.from(yesterday.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        assertFalse("Expected that exams that started yesterday are not editable", exam.isEditable());

        final LocalDate tomorrow = todayLocalDate.plusDays(1);
        exam.setStartDate(Date.from(tomorrow.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        assertTrue("Expected that exams that start tomorrow are editable", exam.isEditable());
    }
}
