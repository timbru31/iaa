package de.nordakademie.iaa_multiple_choice.web;

import com.opensymphony.xwork2.ActionSupport;

import de.nordakademie.iaa_multiple_choice.web.util.LoginRequired;
import de.nordakademie.iaa_multiple_choice.web.util.StudentRequired;

@LoginRequired
@StudentRequired
public class StudentAction extends ActionSupport {
    private static final long serialVersionUID = -7859052872879312048L;
}
