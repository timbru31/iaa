package de.nordakademie.iaa_multiple_choice.web;

import org.springframework.beans.factory.annotation.Autowired;

import de.nordakademie.iaa_multiple_choice.domain.Lecturer;
import de.nordakademie.iaa_multiple_choice.domain.Student;
import de.nordakademie.iaa_multiple_choice.domain.User;
import de.nordakademie.iaa_multiple_choice.service.PasswordAuthenticationService;
import de.nordakademie.iaa_multiple_choice.service.UserService;
import lombok.Getter;
import lombok.Setter;

public class LoginAction extends BaseSessionAction {
    private static final long serialVersionUID = 2712837519650991068L;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordAuthenticationService passwordAuthenticationService;
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private User user;

    public String login() {
        getSession().put("userEmail", email);
        getSession().put("userName", user.getFullName());
        if (user instanceof Student) {
            return "successStudent";
        } else if (user instanceof Lecturer) {
            return "successLecturer";
        }
        return SUCCESS;
    }

    @Override
    public void validate() {
        if (email == null || email.isEmpty() || !email.endsWith("@nordakademie.de") || password == null
                || password.isEmpty() || password.length() < 8) {
            addFieldError("loginFailed", getText("validation.wrongEmailOrUser"));
        } else {
            user = userService.find(email);
            if (user == null
                    || !passwordAuthenticationService.authenticate(password.toCharArray(), user.getPassword())) {
                addFieldError("loginFailed", getText("validation.wrongEmailOrUser"));
            }
        }
    }
}
