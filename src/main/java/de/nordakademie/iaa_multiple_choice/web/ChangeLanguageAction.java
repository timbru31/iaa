package de.nordakademie.iaa_multiple_choice.web;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;
import org.apache.struts2.interceptor.I18nInterceptor;

import com.opensymphony.xwork2.ActionContext;

import lombok.Getter;
import lombok.Setter;

public class ChangeLanguageAction extends BaseSessionAction {
    private static final long serialVersionUID = 6866964825181632874L;
    @Getter
    @Setter
    private String lang;
    @Getter
    @Setter
    private String prevUrl;

    public String changeLanguage() {
        final HttpServletRequest request = (HttpServletRequest) ActionContext.getContext()
                .get(StrutsStatics.HTTP_REQUEST);
        prevUrl = request.getHeader("referer");
        final Locale locale = new Locale(lang);
        ActionContext.getContext().setLocale(locale);
        getSession().put(I18nInterceptor.DEFAULT_SESSION_ATTRIBUTE, locale);
        return SUCCESS;
    }
}
