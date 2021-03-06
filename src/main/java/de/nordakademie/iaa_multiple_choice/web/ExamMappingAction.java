package de.nordakademie.iaa_multiple_choice.web;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;

import de.nordakademie.iaa_multiple_choice.domain.Exam;
import de.nordakademie.iaa_multiple_choice.domain.Lecturer;
import de.nordakademie.iaa_multiple_choice.domain.Student;
import de.nordakademie.iaa_multiple_choice.domain.User;
import de.nordakademie.iaa_multiple_choice.domain.exceptions.ExamNotEditableException;
import de.nordakademie.iaa_multiple_choice.service.ExamService;
import de.nordakademie.iaa_multiple_choice.service.MailSenderService;
import de.nordakademie.iaa_multiple_choice.service.TokenGeneratorService;
import de.nordakademie.iaa_multiple_choice.service.UserService;
import de.nordakademie.iaa_multiple_choice.web.util.LecturerRequired;
import de.nordakademie.iaa_multiple_choice.web.util.LoginRequired;
import lombok.Getter;
import lombok.Setter;

/**
 * Action for mapping exams and students.
 *
 * @author Tim Brust
 */
@LoginRequired
@LecturerRequired
public class ExamMappingAction extends BaseSessionAction {
    private static final long serialVersionUID = 2428450249065529609L;
    @Autowired
    private ExamService examService;
    @Autowired
    private UserService userService;
    @Autowired
    private TokenGeneratorService userTokenGeneratorService;
    @Autowired
    private MailSenderService mailSenderService;
    @Getter
    @Setter
    private Long examId;
    @Getter
    @Setter
    private String[] studentEmails;
    @Getter
    @Setter
    private Set<Student> students = new HashSet<>();
    @Getter
    @Setter
    private Lecturer lecturer;

    private boolean deleteAll;

    @Override
    public String execute() {
        lecturer = (Lecturer) getUser();
        return SUCCESS;
    }

    @Override
    public String input() {
        lecturer = (Lecturer) getUser();
        return INPUT;
    }

    /**
     * Checks if given "raw" input is a valid user and a student.
     */
    public void manualValidateMapStudents() {
        if (studentEmails == null) {
            deleteAll = true;
            return;
        }
        for (final String email : studentEmails) {
            final User user = userService.findByMail(email);
            if (user == null || user instanceof Lecturer) {
                final String[] args = { email };
                addActionError(getText("mapping.userInvalidOrNotFound", args));
            } else {
                students.add((Student) user);
            }
        }
    }

    public String mapStudents() {
        final Exam exam = examService.find(examId);
        if (!exam.isEditable()) {
            throw new ExamNotEditableException();
        }
        manualValidateMapStudents();
        if (deleteAll) {
            removeAllStudents(exam);
        } else {
            updateStudentMapping(exam);
        }
        return hasErrors() ? INPUT : SUCCESS;
    }

    /**
     * Removes all students from an exam and sends a revocation e-mail.
     *
     * @param exam the exam
     */
    private void removeAllStudents(final Exam exam) {
        final Map<Student, String> tokenList = exam.getTokenList();
        for (final Student student : tokenList.keySet()) {
            student.removeExam(exam);
            userService.updateUser(student);
            if (!isMailerDisabled()) {
                sendRevokeEmail(student, exam);
            }
        }
        exam.clearParticipants();
        examService.updateExam(exam);
        if (isMailerDisabled()) {
            addActionMessage(getText("mapping.manual"));
        }
    }

    /**
     * Send invitation email to the student.
     *
     * @param student the student to send the mail to
     * @param exam the exam he was enrolled to
     * @param generatedToken his generated token
     */
    private void sendInvitationMail(final Student student, final Exam exam, final String generatedToken) {
        final String[] args = { student.getFullName(), exam.getName(), generatedToken };
        final String messageMailArgs[] = { student.getEmail() };
        try {
            mailSenderService.sendMail(student.getEmail(), getText("mapping.mailSubjectInvitation", args),
                    getText("mapping.mailTextInvitation", args));
            addActionMessage(getText("mapping.mailSent", messageMailArgs));
        } catch (UnsupportedEncodingException | MessagingException e) {
            final String messageTokenArgs[] = { student.getFullName(), generatedToken };
            addActionError(getText("mapping.mailFailed", messageMailArgs));
            addActionMessage(getText("mapping.token", messageTokenArgs));
            e.printStackTrace();
        }
    }

    /**
     * Sends a revocation e-mail to the student
     *
     * @param student the student to send the mail to
     * @param exam the exam that was revoked
     */
    private void sendRevokeEmail(final Student student, final Exam exam) {
        final String[] args = { student.getFullName(), exam.getName() };
        try {
            mailSenderService.sendMail(student.getEmail(), getText("mapping.mailSubjectRevoke", args),
                    getText("mapping.mailTextRevoke", args));
            final String messageArgs[] = { student.getEmail() };
            addActionMessage(getText("mapping.mailSent", messageArgs));
        } catch (UnsupportedEncodingException | MessagingException e) {
            final String messageArgs[] = { student.getEmail() };
            addActionError(getText("mapping.mailFailed", messageArgs));
            addActionMessage(getText("mapping.manual"));
            e.printStackTrace();
        }
    }

    /**
     * Send an invitation to all new students and a revocation mail to all removed students. If mailing is disabled it
     * shows the token as an actionMessage.
     * 
     * @param exam the exam
     */
    private void updateStudentMapping(final Exam exam) {
        // Important: make a copy of the Map
        final Map<Student, String> enrolledStudents = new HashMap<>(exam.getTokenList());
        for (final Student student : students) {
            if (!enrolledStudents.containsKey(student)) {
                final String generatedToken = userTokenGeneratorService.generateToken();
                exam.addParticipant(student, generatedToken);
                student.addExam(exam);
                userService.updateUser(student);
                if (!isMailerDisabled()) {
                    sendInvitationMail(student, exam, generatedToken);
                } else {
                    final String[] args = { student.getFullName(), generatedToken };
                    addActionMessage(getText("mapping.token", args));
                }
            }
        }
        enrolledStudents.keySet().removeAll(students);
        boolean notificationAdded = false;
        for (final Entry<Student, String> entry : enrolledStudents.entrySet()) {
            final Student student = entry.getKey();
            student.removeExam(exam);
            exam.removeParticipant(student);
            userService.updateUser(student);
            if (!isMailerDisabled()) {
                sendRevokeEmail(student, exam);
            } else if (!notificationAdded) {
                addActionMessage(getText("mapping.manual"));
                notificationAdded = true;
            }
        }
        examService.updateExam(exam);
    }

}
