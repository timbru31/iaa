package de.nordakademie.iaa_multiple_choice.web;

import java.util.Locale;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import lombok.Getter;
import lombok.Setter;

public class ChangeLanguageAction extends ActionSupport {
    private static final long serialVersionUID = 1329016943731331265L;

    @Getter
    @Setter
    private String lang;

    public String changeLanguage() {
        Locale locale = new Locale(lang);
        ActionContext.getContext().setLocale(locale);
        return SUCCESS;
    }
}
