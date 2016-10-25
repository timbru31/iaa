package de.nordakademie.iaa_multiple_choice.web;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import de.nordakademie.iaa_multiple_choice.domain.Lecturer;
import de.nordakademie.iaa_multiple_choice.domain.Student;
import de.nordakademie.iaa_multiple_choice.domain.User;
import de.nordakademie.iaa_multiple_choice.service.PasswordAuthenticationService;
import de.nordakademie.iaa_multiple_choice.service.UserService;
import lombok.Getter;
import lombok.Setter;

public class RegisterUserAction extends ActionSupport {
    private static final long serialVersionUID = -5785967910896850512L;
    @Getter
    @Setter
    private Student student;
    @Getter
    @Setter
    private Lecturer lecturer;
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private String passwordRepeat;
    private final UserService userService;
    private final PasswordAuthenticationService passwordAuthenticationService;

    @Autowired
    public RegisterUserAction(final UserService userService,
            final PasswordAuthenticationService passwordAuthenticationService) {
        this.userService = userService;
        this.passwordAuthenticationService = passwordAuthenticationService;
    }

    public String registerLecturer() {
        this.userService.createUser(lecturer);
        return SUCCESS;
    }

    public String registerStudent() {
        this.userService.createUser(student);
        return SUCCESS;
    }

    public void validateRegisterLecturer() {
        baseValidator(lecturer);
        hashPassword(lecturer);
    }

    public void validateRegisterStudent() {
        baseValidator(student);
        Integer studentNumber = student.getStudentNumber();
        if (studentNumber == null || studentNumber.intValue() <= 0 || studentNumber.intValue() > 10000) {
            addFieldError("studentNumber", getText("validation.studentNumber"));
        }
        hashPassword(student);
    }

    private void hashPassword(final User user) {
        if (!hasFieldErrors()) {
            user.setPassword(passwordAuthenticationService.hash(password.toCharArray()));
        }
    }

    public void baseValidator(final User user) {
        if (user.getFirstName() == null || user.getFirstName().isEmpty() || user.getFirstName().length() < 3) {
            addFieldError("firstName", getText("validation.firstName"));
        }
        if (user.getLastName() == null || user.getLastName().isEmpty() || user.getLastName().length() < 3) {
            addFieldError("lastName", getText("validation.lastName"));
        }
        if (user.getEmail() == null || user.getEmail().isEmpty() || !user.getEmail().endsWith("@nordakademie.de")) {
            addFieldError("email", getText("validation.email"));
        }
        if (password == null || password.isEmpty() || password.length() < 8) {
            addFieldError("password", getText("validation.password"));
        }
        if (password == null || passwordRepeat == null || !password.equals(passwordRepeat)) {
            addFieldError("password", getText("validation.passwordRepeat"));
        }
    }
}
