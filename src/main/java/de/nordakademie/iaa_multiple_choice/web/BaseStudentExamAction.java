package de.nordakademie.iaa_multiple_choice.web;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import de.nordakademie.iaa_multiple_choice.domain.Exam;
import de.nordakademie.iaa_multiple_choice.domain.Question;
import de.nordakademie.iaa_multiple_choice.domain.Student;
import de.nordakademie.iaa_multiple_choice.domain.TestResult;
import de.nordakademie.iaa_multiple_choice.domain.exceptions.QuestionNotFoundException;
import de.nordakademie.iaa_multiple_choice.domain.exceptions.StudentNotEnrolledException;
import de.nordakademie.iaa_multiple_choice.service.ExamService;
import de.nordakademie.iaa_multiple_choice.service.TestResultService;
import de.nordakademie.iaa_multiple_choice.web.util.LoginRequired;
import de.nordakademie.iaa_multiple_choice.web.util.StudentRequired;
import lombok.Getter;
import lombok.Setter;

@LoginRequired
@StudentRequired
public abstract class BaseStudentExamAction extends BaseSessionAction {
    private static final long serialVersionUID = -6539533107098811258L;
    private static final Logger logger = LogManager.getLogger(BaseStudentExamAction.class.getName());
    @Autowired
    @Getter
    private ExamService examService;
    @Autowired
    @Getter
    private TestResultService testResultService;
    @Getter
    @Setter
    private Long examId;
    @Getter
    @Setter
    private Long questionId;
    @Getter
    @Setter
    private Exam exam;
    @Getter
    @Setter
    private Question question;
    @Getter
    @Setter
    private Student student;
    @Getter
    @Setter
    private TestResult testResult;

    public String checkPermissions() {
        exam = getExamService().find(examId);
        student = (Student) getUser();
        if (!exam.hasParticipant(student)) {
            logger.warn("The student {} tried to enroll to the exam {}, but he is not enlisted for this exam!",
                    student.getEmail(), exam.getName());
            throw new StudentNotEnrolledException();
        }
        testResult = testResultService.findByExamAndStudent(examId, student.getId());
        if (testResult == null) {
            addActionError(getText("validation.useToken"));
            return "token";
        } else {
            if (testResult.isExpired()) {
                return "expired";
            }
        }
        return SUCCESS;
    }

    public void checkQuestion() {
        if (questionId != null) {
            final Optional<Question> optionalQuestion = exam.getQuestions().stream()
                    .filter(q -> questionId == q.getId()).findFirst();
            if (!optionalQuestion.isPresent()) {
                throw new QuestionNotFoundException();
            }
            question = optionalQuestion.get();
        } else {
            question = exam.getFirstQuestion();
        }
    }
}
