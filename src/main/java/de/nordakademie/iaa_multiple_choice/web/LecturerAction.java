package de.nordakademie.iaa_multiple_choice.web;

import com.opensymphony.xwork2.ActionSupport;

import de.nordakademie.iaa_multiple_choice.web.util.LecturerRequired;
import de.nordakademie.iaa_multiple_choice.web.util.LoginRequired;

@LoginRequired
@LecturerRequired
public class LecturerAction extends ActionSupport {
    private static final long serialVersionUID = -3814380118644996490L;
}
