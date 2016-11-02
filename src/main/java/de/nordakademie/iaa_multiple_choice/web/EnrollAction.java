package de.nordakademie.iaa_multiple_choice.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import de.nordakademie.iaa_multiple_choice.domain.Exam;
import de.nordakademie.iaa_multiple_choice.domain.Student;
import de.nordakademie.iaa_multiple_choice.service.ExamService;
import de.nordakademie.iaa_multiple_choice.web.util.LoginRequired;
import de.nordakademie.iaa_multiple_choice.web.util.StudentRequired;
import lombok.Getter;
import lombok.Setter;

@LoginRequired
@StudentRequired
public class EnrollAction extends BaseSessionAction {
    private static final long serialVersionUID = -2887663909719799155L;
    private static final Logger logger = LogManager.getLogger(EnrollAction.class.getName());

    @Autowired
    private ExamService examService;
    @Getter
    @Setter
    private Long examId;
    @Getter
    @Setter
    private String token;
    @Getter
    @Setter
    private Exam exam;
    @Getter
    @Setter
    private Student student;

    public String enrollStudent() {
        return SUCCESS;
    }

    @Override
    public String input() {
        student = (Student) getUser();
        return INPUT;
    }

    @Override
    public void validate() {
        exam = examService.find(examId);
        student = (Student) getUser();
        if (!exam.hasParticipant(student)) {
            addActionError(getText("validation.notEnrolled"));
            logger.warn("The student {} tried to enroll to the exam {}, but he is not enlisted for this exam!",
                    student.getEmail(), exam.getName());
            return;
        }
        if (token == null || token.isEmpty() || !exam.getToken(student).equals(token)) {
            addFieldError("token", getText("validation.tokenInvalid"));
            return;
        }
    }
}
