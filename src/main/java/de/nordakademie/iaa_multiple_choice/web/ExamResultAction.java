package de.nordakademie.iaa_multiple_choice.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import de.nordakademie.iaa_multiple_choice.domain.Exam;
import de.nordakademie.iaa_multiple_choice.domain.Student;
import de.nordakademie.iaa_multiple_choice.domain.TestResult;
import de.nordakademie.iaa_multiple_choice.domain.exceptions.StudentNotEnrolledException;
import de.nordakademie.iaa_multiple_choice.service.ExamService;
import de.nordakademie.iaa_multiple_choice.service.TestResultService;
import de.nordakademie.iaa_multiple_choice.web.util.LoginRequired;
import de.nordakademie.iaa_multiple_choice.web.util.StudentRequired;
import lombok.Getter;
import lombok.Setter;

@LoginRequired
@StudentRequired
public class ExamResultAction extends BaseSessionAction {
    private static final long serialVersionUID = 5328927367795893403L;
    private static final Logger logger = LogManager.getLogger(ExamResultAction.class.getName());
    @Autowired
    private ExamService examService;
    @Autowired
    private TestResultService testResultService;
    @Getter
    @Setter
    private Long examId;
    @Getter
    @Setter
    private Exam exam;
    @Getter
    @Setter
    private Student student;
    @Getter
    @Setter
    private long endTimeMillis;
    @Getter
    @Setter
    private TestResult testResult;

    public String display() {
        exam = examService.find(examId);
        student = (Student) getUser();
        if (!exam.hasParticipant(student)) {
            logger.warn("The student {} tried to view the exam result {}, but he is not enlisted for this exam!",
                    student.getEmail(), exam.getName());
            throw new StudentNotEnrolledException();
        }
        final TestResult byExamAndStudent = testResultService.findByExamAndStudent(examId, student.getId());
        if (byExamAndStudent == null) {
            addActionError(getText("validation.examNotTaken"));
            return "redirectHome";
        }
        testResult = byExamAndStudent;
        if (!testResult.isExpired()) {
            addActionError(getText("validation.examNotFinished"));
            return "redirectHome";
        }
        return SUCCESS;
    }
}
