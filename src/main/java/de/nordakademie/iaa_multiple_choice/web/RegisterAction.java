package de.nordakademie.iaa_multiple_choice.web;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import de.nordakademie.iaa_multiple_choice.domain.Lecturer;
import de.nordakademie.iaa_multiple_choice.domain.Student;
import de.nordakademie.iaa_multiple_choice.domain.User;
import de.nordakademie.iaa_multiple_choice.service.MailSenderService;
import de.nordakademie.iaa_multiple_choice.service.PasswordAuthenticationService;
import de.nordakademie.iaa_multiple_choice.service.TokenGeneratorService;
import de.nordakademie.iaa_multiple_choice.service.UserService;
import de.nordakademie.iaa_multiple_choice.web.exception.RegistrationDisabledException;
import lombok.Getter;
import lombok.Setter;

public class RegisterAction extends BaseSessionAction {
    private static final long serialVersionUID = -5785967910896850512L;
    // RegExp from http://stackoverflow.com/a/3802238/1902598
    private static final String PASSWORD_REGEXP = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
    @Getter
    @Setter
    private String firstName;
    @Getter
    @Setter
    private String lastName;
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private Integer studentNumber;
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private String passwordRepeat;
    @Getter
    @Setter
    private String role;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordAuthenticationService passwordAuthenticationService;
    @Autowired
    private MailSenderService mailSenderService;
    @Autowired
    private TokenGeneratorService tokenGeneratorService;

    public void baseValidator() {
        if (!isRegistrationEnabled()) {
            throw new RegistrationDisabledException();
        }
        if (firstName == null || firstName.isEmpty() || firstName.length() < 3) {
            addFieldError("firstName", getText("validation.firstName"));
        }
        if (lastName == null || lastName.isEmpty() || lastName.length() < 3) {
            addFieldError("lastName", getText("validation.lastName"));
        }
        if (email == null || email.isEmpty() || !email.endsWith("@nordakademie.de")) {
            addFieldError("email", getText("validation.email"));
        }
        if (password == null || password.isEmpty() || !password.matches(PASSWORD_REGEXP)) {
            addFieldError("password", getText("validation.password"));
        }
        if (password == null || passwordRepeat == null || !password.equals(passwordRepeat)) {
            addFieldError("password", getText("validation.passwordRepeat"));
        }
        if (userService.findByMail(getEmail()) != null) {
            addFieldError("email", getText("validation.emailExists"));
        }
    }

    public String display() {
        if (!isRegistrationEnabled()) {
            throw new RegistrationDisabledException();
        }
        return SUCCESS;
    }

    private String hashPassword() {
        return passwordAuthenticationService.hash(password.toCharArray());
    }

    public String registerLecturer() {
        final String hashedPassword = hashPassword();
        final String activationToken = tokenGeneratorService.generateToken();
        final Lecturer lecturer = new Lecturer(firstName, lastName, email, hashedPassword, activationToken);
        lecturer.setActivated(isMailerDisabled());
        sendRegistrationMail(lecturer);
        if (hasActionErrors()) {
            return INPUT;
        }
        userService.createUser(lecturer);
        if (isMailerDisabled()) {
            getSession().put("userEmail", lecturer.getEmail());
            getSession().put("userName", lecturer.getFullName());
            return SUCCESS;
        }
        return "activationPending";
    }

    public String registerStudent() {
        final String hashedPassword = hashPassword();
        final String activationToken = tokenGeneratorService.generateToken();
        final Student student = new Student(firstName, lastName, email, hashedPassword, activationToken, studentNumber);
        student.setActivated(isMailerDisabled());
        sendRegistrationMail(student);
        if (hasActionErrors()) {
            return INPUT;
        }
        userService.createUser(student);
        if (isMailerDisabled()) {
            getSession().put("userEmail", student.getEmail());
            getSession().put("userName", student.getFullName());
            return SUCCESS;
        }
        return "activationPending";
    }

    private void sendRegistrationMail(final User user) {
        final HttpServletRequest request = ServletActionContext.getRequest();
        try {
            final String url = request.getRequestURL().toString();
            final String baseURL = url.substring(0, url.length() - request.getRequestURI().length())
                    + request.getContextPath() + "/";
            final String activateURL = baseURL + "activate?token=" + user.getActivationToken();
            final String[] args = { user.getFullName(), activateURL };
            mailSenderService.sendMail(user.getEmail(), getText("registration.mailSubject"),
                    getText("registration.mailText", args));
        } catch (UnsupportedEncodingException | MessagingException e) {
            addActionError("registration.mailFailed");
        }
    }

    public void validateRegisterLecturer() {
        baseValidator();
    }

    public void validateRegisterStudent() {
        baseValidator();
        if (studentNumber == null || studentNumber <= 0 || studentNumber > 10000) {
            addFieldError("studentNumber", getText("validation.studentNumber"));
        }
        if (userService.findByStudentNumber(studentNumber) != null) {
            addFieldError("studentNumber", getText("validation.studentNumberExists"));
        }
    }
}
