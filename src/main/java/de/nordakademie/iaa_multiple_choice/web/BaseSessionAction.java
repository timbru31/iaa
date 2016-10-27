package de.nordakademie.iaa_multiple_choice.web;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import lombok.Getter;
import lombok.Setter;

public class BaseSessionAction extends ActionSupport implements SessionAware {
    private static final long serialVersionUID = -6535887821833885360L;
    @Getter
    @Setter
    private Map<String, Object> session;
}
