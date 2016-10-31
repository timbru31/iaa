package de.nordakademie.iaa_multiple_choice.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Value;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ActivationPendingAction extends ActionSupport {
    private static final long serialVersionUID = -2646095881961031216L;
    @Value("${mail.disabled}")
    private boolean mailerDisabled;

    @Override
    public String execute() {
        final HttpServletRequest request = (HttpServletRequest) ActionContext.getContext()
                .get(ServletActionContext.HTTP_REQUEST);
        final String referer = request.getHeader("referer");
        if (referer == null || referer.isEmpty() || !referer.contains("registration") || mailerDisabled) {
            return "redirectHome";
        }
        return SUCCESS;
    }
}
