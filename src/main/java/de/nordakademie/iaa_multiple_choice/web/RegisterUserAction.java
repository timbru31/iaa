package de.nordakademie.iaa_multiple_choice.web;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import de.nordakademie.iaa_multiple_choice.domain.Lecturer;
import de.nordakademie.iaa_multiple_choice.domain.Student;
import de.nordakademie.iaa_multiple_choice.domain.User;
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

    private final UserService userService;

    @Autowired
    public RegisterUserAction(final UserService userService) {
        this.userService = userService;
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
    }

    public void validateRegisterStudent() {
        baseValidator(student);
        Integer studentNumber = student.getStudentNumber();
        if (studentNumber == null || studentNumber.intValue() <= 0 || studentNumber.intValue() > 10000) {
            addFieldError("studentNumber", getText("validation.studentNumber"));
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
        // TODO password, check, repeat, store hashed
    }
}
