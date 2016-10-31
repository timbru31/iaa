package de.nordakademie.iaa_multiple_choice.web;

import org.springframework.beans.factory.annotation.Value;

import com.opensymphony.xwork2.ActionSupport;

public class ActivationPendingAction extends ActionSupport {
    private static final long serialVersionUID = -2646095881961031216L;
    @Value("${mail.disabled}")
    private boolean mailerDisabled;

    @Override
    public String execute() {
        if (mailerDisabled) {
            return "redirectHome";
        }
        return SUCCESS;
    }
}
