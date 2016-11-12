package de.nordakademie.iaa_multiple_choice.web;

import de.nordakademie.iaa_multiple_choice.domain.Lecturer;
import de.nordakademie.iaa_multiple_choice.web.util.LecturerRequired;
import de.nordakademie.iaa_multiple_choice.web.util.LoginRequired;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Yannick Rump action for lecturer home
 */
@LoginRequired
@LecturerRequired
public class LecturerAction extends BaseSessionAction {
    private static final long serialVersionUID = -3814380118644996490L;
    @Getter
    @Setter
    private Lecturer lecturer;

    @Override
    public String execute() {
        lecturer = (Lecturer) getUser();
        return SUCCESS;
    }
}
