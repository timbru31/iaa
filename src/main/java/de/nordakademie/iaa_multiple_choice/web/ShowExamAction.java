package de.nordakademie.iaa_multiple_choice.web;

import org.springframework.beans.factory.annotation.Autowired;

import de.nordakademie.iaa_multiple_choice.domain.Exam;
import de.nordakademie.iaa_multiple_choice.domain.Student;
import de.nordakademie.iaa_multiple_choice.domain.exceptions.StudentNotEnrolledException;
import de.nordakademie.iaa_multiple_choice.service.ExamService;
import de.nordakademie.iaa_multiple_choice.web.util.LoginRequired;
import de.nordakademie.iaa_multiple_choice.web.util.StudentRequired;
import lombok.Getter;
import lombok.Setter;

@LoginRequired
@StudentRequired
public class ShowExamAction extends BaseSessionAction {
    private static final long serialVersionUID = -7347508004353789191L;
    @Autowired
    private ExamService examService;
    @Getter
    @Setter
    private Long examId;
    @Getter
    @Setter
    private Exam exam;
    @Getter
    @Setter
    private Student student;

    @Override
    public String execute() {
        findExam();
        return SUCCESS;
    }

    private void findExam() {
        exam = examService.find(examId);
        student = (Student) getUser();
        if (!exam.hasParticipant(student)) {
            throw new StudentNotEnrolledException();
        }
    }

    @Override
    public String input() {
        findExam();
        return INPUT;
    }
}
