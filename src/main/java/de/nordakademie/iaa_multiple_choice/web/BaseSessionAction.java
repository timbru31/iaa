package de.nordakademie.iaa_multiple_choice.web;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import de.nordakademie.iaa_multiple_choice.domain.User;
import de.nordakademie.iaa_multiple_choice.service.UserService;
import lombok.Getter;
import lombok.Setter;

public class BaseSessionAction extends ActionSupport implements SessionAware {
    private static final long serialVersionUID = -6535887821833885360L;
    @Getter
    @Setter
    private Map<String, Object> session;
    @Autowired
    private UserService userService;

    public User getUser() {
        if (session == null) {
            return null;
        }
        final String email = (String) session.get("userEmail");
        if (email == null) {
            return null;
        }

        return userService.findByMail(email);
    }
}
