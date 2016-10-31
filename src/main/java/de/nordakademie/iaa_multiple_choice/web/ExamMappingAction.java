package de.nordakademie.iaa_multiple_choice.web;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import de.nordakademie.iaa_multiple_choice.domain.Exam;
import de.nordakademie.iaa_multiple_choice.domain.Lecturer;
import de.nordakademie.iaa_multiple_choice.domain.Student;
import de.nordakademie.iaa_multiple_choice.domain.User;
import de.nordakademie.iaa_multiple_choice.service.ExamService;
import de.nordakademie.iaa_multiple_choice.service.TokenGeneratorService;
import de.nordakademie.iaa_multiple_choice.service.UserService;
import de.nordakademie.iaa_multiple_choice.web.util.LecturerRequired;
import de.nordakademie.iaa_multiple_choice.web.util.LoginRequired;
import lombok.Getter;
import lombok.Setter;

@LoginRequired
@LecturerRequired
public class ExamMappingAction extends BaseSessionAction {

    private static final long serialVersionUID = 2428450249065529609L;
    @Autowired
    private ExamService examService;
    @Autowired
    private UserService userService;
    @Autowired
    private TokenGeneratorService userTokenGeneratorService;
    @Getter
    @Setter
    private Long examId;
    @Getter
    @Setter
    private String[] studentEmails;
    @Getter
    @Setter
    private Set<Student> students = new HashSet<>();
    @Getter
    @Setter
    private Lecturer lecturer;
    private boolean deleteAll;

    @Override
    public String execute() {
        lecturer = (Lecturer) getUser();
        return SUCCESS;
    }

    public String mapStudents() {
        final Exam exam = examService.find(examId);
        if (deleteAll) {
            removeAllStudents(exam);
        } else {
            updateStudentMapping(exam);
        }
        return SUCCESS;
    }

    private void removeAllStudents(final Exam exam) {
        final Map<Student, String> tokenList = exam.getTokenList();
        for (final Student student : tokenList.keySet()) {
            student.removeExam(exam);
            userService.updateUser(student);
        }
        exam.clearParticipants();
        examService.updateExam(exam);
    }

    private void updateStudentMapping(final Exam exam) {
        // Important: make a copy of the Map
        final Map<Student, String> enrolledStudents = new HashMap<>(exam.getTokenList());
        for (final Student student : students) {
            if (!enrolledStudents.containsKey(student)) {
                final String generatedToken = userTokenGeneratorService.generateToken();
                exam.addParticipant(student, generatedToken);
                student.addExam(exam);
                userService.updateUser(student);
                // sendInvitationMail();
            }
        }
        enrolledStudents.keySet().removeAll(students);
        for (final Entry<Student, String> entry : enrolledStudents.entrySet()) {
            final Student student = entry.getKey();
            student.removeExam(exam);
            exam.removeParticipant(student);
            userService.updateUser(student);
            // sendRevokeMail();
        }
        examService.updateExam(exam);
    }

    public void validateMapStudents() {
        if (studentEmails == null) {
            deleteAll = true;
            return;
        }
        for (final String email : studentEmails) {
            final User user = userService.findByMail(email);
            if (user == null || user instanceof Lecturer) {
                addFieldError("test", "test");
                break;
            }
            students.add((Student) user);
        }
    }

}
