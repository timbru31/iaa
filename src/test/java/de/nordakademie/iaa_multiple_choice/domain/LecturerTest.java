package de.nordakademie.iaa_multiple_choice.domain;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;

import org.junit.Test;

public class LecturerTest {

    @Test
    public void testDuplicateExams() {
        final Exam exam = new Exam();
        final Lecturer lecturer = new Lecturer();
        lecturer.setExams(new HashSet<Exam>());
        lecturer.addExam(exam);
        lecturer.addExam(exam);
        assertEquals("Expected to have one exam", 1, lecturer.getExams().size());
    }
}
