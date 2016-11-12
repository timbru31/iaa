package de.nordakademie.iaa_multiple_choice.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;

/**
 * @author Tim Brust action for token activation
 */
public class ActivationPendingAction extends BaseAction {
    private static final long serialVersionUID = -2646095881961031216L;

    @Override
    public String execute() {
        final HttpServletRequest request = (HttpServletRequest) ActionContext.getContext()
                .get(StrutsStatics.HTTP_REQUEST);
        final String referer = request.getHeader("referer");
        if (referer == null || referer.isEmpty() || !(referer.contains("registration") || referer.contains("register"))
                || isMailerDisabled()) {
            return "redirectHome";
        }
        return SUCCESS;
    }
}
