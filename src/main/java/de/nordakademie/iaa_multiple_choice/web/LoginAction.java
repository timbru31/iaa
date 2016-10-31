package de.nordakademie.iaa_multiple_choice.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;

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
    @Getter
    @Setter
    private String prevUrl;

    public String display() {
        // Important to call super, otherwise we get the local user object!
        final User alreadyLoggedInUser = super.getUser();
        if (alreadyLoggedInUser == null) {
            return SUCCESS;
        }
        return "redirectHome";
    }

    public String login() {
        final HttpServletRequest request = (HttpServletRequest) ActionContext.getContext()
                .get(StrutsStatics.HTTP_REQUEST);
        final String referer = request.getHeader("referer");
        getSession().put("userEmail", email);
        getSession().put("userName", user.getFullName());
        if (referer != null && !referer.isEmpty() && !referer.contains("login")) {
            prevUrl = referer;
            return "redirect";
        }
        if (user instanceof Student) {
            return "successStudent";
        } else if (user instanceof Lecturer) {
            return "successLecturer";
        }
        return SUCCESS;
    }

    public void validateLogin() {
        if (email == null || email.isEmpty() || !email.endsWith("@nordakademie.de") || password == null
                || password.isEmpty() || password.length() < 8) {
            addFieldError("loginFailed", getText("validation.wrongEmailOrUser"));
        } else {
            user = userService.findByMail(email);
            if (user == null
                    || !passwordAuthenticationService.authenticate(password.toCharArray(), user.getPassword())) {
                addFieldError("loginFailed", getText("validation.wrongEmailOrUser"));
            } else if (!user.isActivated()) {
                addFieldError("notActivated", getText("validation.notActivated"));
            }
        }
    }
}
