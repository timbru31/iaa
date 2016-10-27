package de.nordakademie.iaa_multiple_choice.web;

public class LogoutAction extends BaseSessionAction {
    private static final long serialVersionUID = -2066036762141985352L;

    public String logout() {
        if (getSession().containsKey("userEmail")) {
            getSession().remove("userEmail");
        }
        if (getSession().containsKey("userName")) {
            getSession().remove("userName");
        }
        return SUCCESS;
    }
}
