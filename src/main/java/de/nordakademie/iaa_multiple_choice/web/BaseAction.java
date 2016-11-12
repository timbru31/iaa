package de.nordakademie.iaa_multiple_choice.web;

import org.springframework.beans.factory.annotation.Value;

import com.opensymphony.xwork2.ActionSupport;

import lombok.Getter;

/**
 * BaseAction to have access to mailer and registration enabled flags.
 * 
 * @author Tim Brust
 */
public class BaseAction extends ActionSupport {
    private static final long serialVersionUID = -5741251881623919553L;

    @Value("${mail.disabled}")
    @Getter
    private boolean mailerDisabled;

    @Value("${registration.enabled}")
    @Getter
    private boolean registrationEnabled;
}
