package de.nordakademie.iaa_multiple_choice.web;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import lombok.Getter;
import lombok.Setter;

public class LogoutAction extends ActionSupport implements SessionAware {
    private static final long serialVersionUID = -2066036762141985352L;
    @Getter
    @Setter
    private Map<String, Object> session;

    public String logout() {
        if (session.containsKey("userEmail")) {
            session.remove("userEmail");
        }
        if (session.containsKey("userName")) {
            session.remove("userName");
        }
        return SUCCESS;
    }
}
