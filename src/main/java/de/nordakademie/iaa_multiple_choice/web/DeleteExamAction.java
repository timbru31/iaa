package de.nordakademie.iaa_multiple_choice.web;

import org.springframework.beans.factory.annotation.Autowired;

import de.nordakademie.iaa_multiple_choice.domain.Exam;
import de.nordakademie.iaa_multiple_choice.domain.Lecturer;
import de.nordakademie.iaa_multiple_choice.domain.Student;
import de.nordakademie.iaa_multiple_choice.domain.exceptions.ExamNotEditableException;
import de.nordakademie.iaa_multiple_choice.service.ExamService;
import de.nordakademie.iaa_multiple_choice.service.UserService;
import de.nordakademie.iaa_multiple_choice.web.util.LecturerRequired;
import de.nordakademie.iaa_multiple_choice.web.util.LoginRequired;
import lombok.Getter;
import lombok.Setter;

@LoginRequired
@LecturerRequired
public class DeleteExamAction extends BaseSessionAction {
    private static final long serialVersionUID = -6669737772568959965L;
    @Autowired
    private ExamService examService;
    @Autowired
    private UserService userService;
    @Getter
    @Setter
    private Long examId;

    public String deleteExam() {
        final Exam exam = examService.find(examId);
        if (!exam.isEditable()) {
            throw new ExamNotEditableException();
        }
        final Lecturer lecturer = (Lecturer) getUser();
        lecturer.removeExam(exam);
        userService.updateUser(lecturer);
        for (final Student student : exam.getTokenList().keySet()) {
            student.removeExam(exam);
            userService.updateUser(student);
        }
        exam.getTokenList().clear();
        examService.deleteExam(examId);
        // TODO send mail to students if mailing = true
        return SUCCESS;
    }
}
