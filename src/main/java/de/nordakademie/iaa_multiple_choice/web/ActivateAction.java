package de.nordakademie.iaa_multiple_choice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import de.nordakademie.iaa_multiple_choice.domain.User;
import de.nordakademie.iaa_multiple_choice.service.UserService;
import lombok.Getter;
import lombok.Setter;

public class ActivateAction extends BaseSessionAction {
    private static final long serialVersionUID = -2646095881961031216L;
    @Value("${mail.disabled}")
    private boolean mailerDisabled;
    @Getter
    @Setter
    private String token;
    @Autowired
    private UserService userService;

    @Override
    public String execute() {
        if (mailerDisabled) {
            return "redirectHome";
        }

        if (token == null || token.isEmpty()) {
            return ERROR;
        }

        final User user = userService.findByToken(token);
        if (user == null) {
            return ERROR;
        }

        if (user.isActivated()) {
            return "redirectHome";
        }

        user.setActivated(true);
        userService.updateUser(user);
        getSession().put("userEmail", user.getEmail());
        getSession().put("userName", user.getFullName());
        return SUCCESS;
    }
}
