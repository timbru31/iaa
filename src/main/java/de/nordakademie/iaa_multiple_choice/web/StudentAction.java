package de.nordakademie.iaa_multiple_choice.web;

import de.nordakademie.iaa_multiple_choice.domain.Student;
import de.nordakademie.iaa_multiple_choice.web.util.LoginRequired;
import de.nordakademie.iaa_multiple_choice.web.util.StudentRequired;
import lombok.Getter;
import lombok.Setter;

@LoginRequired
@StudentRequired
public class StudentAction extends BaseSessionAction {
    private static final long serialVersionUID = -7859052872879312048L;
    @Getter
    @Setter
    private Student student;

    @Override
    public String execute() {
        student = (Student) getUser();
        return SUCCESS;
    }

    @Override
    public String input() {
        student = (Student) getUser();
        return INPUT;
    }
}
