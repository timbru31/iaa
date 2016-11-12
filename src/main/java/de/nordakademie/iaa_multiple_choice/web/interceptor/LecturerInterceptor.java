package de.nordakademie.iaa_multiple_choice.web.interceptor;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import de.nordakademie.iaa_multiple_choice.domain.Lecturer;
import de.nordakademie.iaa_multiple_choice.domain.User;
import de.nordakademie.iaa_multiple_choice.service.UserService;
import de.nordakademie.iaa_multiple_choice.web.util.LecturerRequired;

/**
 * Interceptor for lecturer.
 *
 * @author Tim Brust
 */
public class LecturerInterceptor extends AbstractInterceptor {
    private static final long serialVersionUID = 7111977843211607474L;
    @Autowired
    private UserService userService;

    /**
     * Checks that the given user is logged in and a lecturer.
     */
    @Override
    public String intercept(final ActionInvocation invocation) throws Exception {
        final Object action = invocation.getAction();
        if (!action.getClass().isAnnotationPresent(LecturerRequired.class)) {
            return invocation.invoke();
        }

        final Map<String, Object> session = ActionContext.getContext().getSession();
        final String email = (String) session.get("userEmail");
        final User user = userService.findByMail(email);
        if (user == null) {
            return "loginRedirect";
        }

        if (!(user instanceof Lecturer)) {
            return "forbidden";
        }

        return invocation.invoke();
    }
}
