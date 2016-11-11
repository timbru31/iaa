package de.nordakademie.iaa_multiple_choice.web;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import de.nordakademie.iaa_multiple_choice.web.util.LoginRequired;
import de.nordakademie.iaa_multiple_choice.web.util.StudentRequired;
import lombok.Getter;
import lombok.Setter;

@LoginRequired
@StudentRequired
public class TakeExamAction extends BaseStudentExamAction {
    private static final long serialVersionUID = -2887663909719799155L;
    @Getter
    @Setter
    private long endTimeMillis;
    @Getter
    private int progress;

    public String display() {
        final String result = checkPermissions();
        if (!result.equals(SUCCESS)) {
            return result;
        }
        checkQuestion();
        final LocalDateTime endTime = getTestResult().getStartTime().plusMinutes(getExam().getExamTime().intValue());
        endTimeMillis = endTime.atOffset(ZonedDateTime.now().getOffset()).toEpochSecond();
        progress = (int) ((getTestResult().getSubmittedAnswers().size() * 100.0f) / getExam().getQuestions().size());
        return SUCCESS;
    }
}
