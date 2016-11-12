package de.nordakademie.iaa_multiple_choice.web;

import org.springframework.beans.factory.annotation.Autowired;

import de.nordakademie.iaa_multiple_choice.domain.Lecturer;
import de.nordakademie.iaa_multiple_choice.domain.Student;
import de.nordakademie.iaa_multiple_choice.domain.User;
import de.nordakademie.iaa_multiple_choice.service.UserService;

/**
 * @author Tim Brust action for home site
 */
public class HomeAction extends BaseSessionAction {
    private static final long serialVersionUID = -7773097018962821833L;
    @Autowired
    private UserService userService;

    @Override
    public String execute() {
        if (getSession().containsKey("userEmail")) {
            final User user = userService.findByMail((String) getSession().get("userEmail"));
            if (user == null) {
                return "welcome";
            }
            if (user instanceof Student) {
                return "student";
            } else if (user instanceof Lecturer) {
                return "lecturer";
            }
            return "welcome";
        }
        return "welcome";
    }
}
