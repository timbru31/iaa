package de.nordakademie.iaa_multiple_choice.web;

import java.time.LocalDateTime;

import de.nordakademie.iaa_multiple_choice.web.util.LoginRequired;
import de.nordakademie.iaa_multiple_choice.web.util.StudentRequired;

/**
 * Action for submitting an exam as a student.
 * 
 * @author Hannes Peterson
 */
@LoginRequired
@StudentRequired
public class StudentSubmitExamAction extends BaseStudentExamAction {
    private static final long serialVersionUID = -7995749861616937678L;

    public String submitExam() {
        final String result = checkPermissions();
        if (!result.equals(SUCCESS)) {
            return result;
        }
        final LocalDateTime endTime = LocalDateTime.now();
        getTestResult().setEndTime(endTime);
        getTestResult().calculateFinalPoints();
        getTestResultService().updateTestResult(getTestResult());
        return SUCCESS;
    }
}
