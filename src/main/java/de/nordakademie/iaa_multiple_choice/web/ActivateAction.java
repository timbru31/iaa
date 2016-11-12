package de.nordakademie.iaa_multiple_choice.web;

import org.springframework.beans.factory.annotation.Autowired;

import de.nordakademie.iaa_multiple_choice.domain.User;
import de.nordakademie.iaa_multiple_choice.service.UserService;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Tim Brust action for token activation
 */
public class ActivateAction extends BaseSessionAction {
    private static final long serialVersionUID = -1644655626750378438L;
    @Getter
    @Setter
    private String token;
    @Autowired
    private UserService userService;

    @Override
    public String execute() {
        if (isMailerDisabled()) {
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
