package de.nordakademie.iaa_multiple_choice.web;

import org.springframework.beans.factory.annotation.Autowired;

import de.nordakademie.iaa_multiple_choice.domain.Exam;
import de.nordakademie.iaa_multiple_choice.domain.Lecturer;
import de.nordakademie.iaa_multiple_choice.service.ExamService;
import de.nordakademie.iaa_multiple_choice.web.util.LecturerRequired;
import de.nordakademie.iaa_multiple_choice.web.util.LoginRequired;
import lombok.Getter;
import lombok.Setter;

/**
 * Action for showing all the results of one exam.
 *
 * @author Yannick Rump
 */

@LoginRequired
@LecturerRequired
public class ShowExamResultsAction extends BaseSessionAction {
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
    private Lecturer lecturer;

    @Override
    public String execute() {
        findExam();
        return SUCCESS;
    }

    /**
     * Finds an exam and shows all results
     */
    private void findExam() {
        exam = examService.find(examId);
        lecturer = (Lecturer) getUser();
    }

}
