package de.nordakademie.iaa_multiple_choice.domain;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;

import org.junit.Test;

public class StudentTest {

    @Test
    public void testDuplicateExams() {
        final Exam exam = new Exam();
        final Student student = new Student();
        student.setRegisteredExams(new HashSet<Exam>());
        student.addExam(exam);
        student.addExam(exam);
        assertEquals("Expected to have one exam", 1, student.getRegisteredExams().size());
    }
}
