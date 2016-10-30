package de.nordakademie.iaa_multiple_choice.web;

import org.springframework.beans.factory.annotation.Autowired;

import de.nordakademie.iaa_multiple_choice.domain.Lecturer;
import de.nordakademie.iaa_multiple_choice.domain.Student;
import de.nordakademie.iaa_multiple_choice.service.PasswordAuthenticationService;
import de.nordakademie.iaa_multiple_choice.service.UserService;
import lombok.Getter;
import lombok.Setter;

public class RegisterAction extends BaseSessionAction {
    private static final long serialVersionUID = -5785967910896850512L;
    // RegExp from http://stackoverflow.com/a/3802238/1902598
    @SuppressWarnings("unused")
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

    public void baseValidator() {
        if (firstName == null || firstName.isEmpty() || firstName.length() < 3) {
            addFieldError("firstName", getText("validation.firstName"));
        }
        if (lastName == null || lastName.isEmpty() || lastName.length() < 3) {
            addFieldError("lastName", getText("validation.lastName"));
        }
        if (email == null || email.isEmpty() || !email.endsWith("@nordakademie.de")) {
            addFieldError("email", getText("validation.email"));
        }
        // TODO enable password regex
        if (password == null || password.isEmpty() /* || !password.matches(PASSWORD_REGEXP) */) {
            addFieldError("password", getText("validation.password"));
        }
        if (password == null || passwordRepeat == null || !password.equals(passwordRepeat)) {
            addFieldError("password", getText("validation.passwordRepeat"));
        }
        if (userService.find(getEmail()) != null) {
            addFieldError("email", getText("validation.emailExists"));
        }
    }

    private String hashPassword() {
        return passwordAuthenticationService.hash(password.toCharArray());
    }

    public String registerLecturer() {
        final String hashedPassword = hashPassword();
        final Lecturer lecturer = new Lecturer(firstName, lastName, email, hashedPassword);
        userService.createUser(lecturer);
        getSession().put("userEmail", lecturer.getEmail());
        getSession().put("userName", lecturer.getFullName());
        return SUCCESS;
    }

    public String registerStudent() {
        final String hashedPassword = hashPassword();
        final Student student = new Student(firstName, lastName, email, hashedPassword, studentNumber);
        userService.createUser(student);
        getSession().put("userEmail", student.getEmail());
        getSession().put("userName", student.getFullName());
        return SUCCESS;
    }

    public void validateRegisterLecturer() {
        baseValidator();
    }

    public void validateRegisterStudent() {
        baseValidator();
        if (studentNumber == null || studentNumber.intValue() <= 0 || studentNumber.intValue() > 10000) {
            addFieldError("studentNumber", getText("validation.studentNumber"));
        }
    }
}
