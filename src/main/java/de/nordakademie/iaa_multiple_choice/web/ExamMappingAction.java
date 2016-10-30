package de.nordakademie.iaa_multiple_choice.web;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import de.nordakademie.iaa_multiple_choice.domain.Exam;
import de.nordakademie.iaa_multiple_choice.domain.Lecturer;
import de.nordakademie.iaa_multiple_choice.domain.Student;
import de.nordakademie.iaa_multiple_choice.domain.User;
import de.nordakademie.iaa_multiple_choice.service.ExamService;
import de.nordakademie.iaa_multiple_choice.service.UserService;
import de.nordakademie.iaa_multiple_choice.service.UserTokenGeneratorService;
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
    private UserTokenGeneratorService userTokenGeneratorService;
    @Getter
    @Setter
    private Long examId;
    @Getter
    @Setter
    private String[] studentEmails;
    @Getter
    @Setter
    private ArrayList<Student> students = new ArrayList<>();
    @Getter
    @Setter
    private Lecturer lecturer;

    @Override
    public String execute() {
        lecturer = (Lecturer) getUser();
        return SUCCESS;
    }

    public String mappingStudent() {
        final Exam exam = examService.find(examId);
        for (final Student student : students) {
            final String generatedToken = userTokenGeneratorService.generateToken();
            exam.addStudent(student, generatedToken);
            student.addExam(exam);
            userService.updateUser(student);
        }
        examService.updateExam(exam);
        return SUCCESS;
    }

    public void validateMappingStudent() {
        if (studentEmails == null) {
            addFieldError("test", "test");
            return;
        }
        for (final String email : studentEmails) {
            final User user = userService.find(email);
            if (user == null || user instanceof Lecturer) {
                addFieldError("test", "test");
                break;
            }
            students.add((Student) user);
        }
    }

}
