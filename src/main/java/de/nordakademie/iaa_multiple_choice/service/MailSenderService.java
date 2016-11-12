package de.nordakademie.iaa_multiple_choice.service;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * Service for mail sending.
 *
 * @author Tim Brust
 */
@Service("mailService")
public class MailSenderService {
    private static final Logger logger = LogManager.getLogger(MailSenderService.class.getName());

    /**
     * Gets value from Spring properties file
     */
    @Value("${mail.disabled}")
    private boolean disabled;

    @Autowired
    private JavaMailSender mailSender;

    /**
     * Gets value from Spring properties file. The default is null.
     */
    @Value("${mail.overrideRecipient:}")
    private String recipientOverride;

    /**
     * Sends a mail to the given recipient.
     *
     * @param to the recipient
     * @param subject the subject line
     * @param body the body
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    public void sendMail(final String to, final String subject, final String body)
            throws MessagingException, UnsupportedEncodingException {
        if (disabled) {
            logger.info("Mail sending is disabled via configuration");
            return;
        }
        final MimeMessage mimeMessage = mailSender.createMimeMessage();
        final MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
        final String recipient = recipientOverride != null && !recipientOverride.isEmpty() ? recipientOverride : to;
        mimeMessageHelper.setTo(recipient);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(body, true);
        mimeMessageHelper.setFrom("noreply@nordakademie.de", "NORDAKADEMIE Multiple Choice");
        mailSender.send(mimeMessage);
        logger.info("Sent mail to " + recipient);
    }
}
