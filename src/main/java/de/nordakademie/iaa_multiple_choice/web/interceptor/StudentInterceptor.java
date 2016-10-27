package de.nordakademie.iaa_multiple_choice.web.interceptor;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import de.nordakademie.iaa_multiple_choice.domain.Student;
import de.nordakademie.iaa_multiple_choice.domain.User;
import de.nordakademie.iaa_multiple_choice.service.UserService;
import de.nordakademie.iaa_multiple_choice.web.util.StudentRequired;

public class StudentInterceptor extends AbstractInterceptor {
    private static final long serialVersionUID = 6756324184319715740L;
    @Autowired
    private UserService userService;

    @Override
    public String intercept(final ActionInvocation invocation) throws Exception {
        final Object action = invocation.getAction();
        if (!action.getClass().isAnnotationPresent(StudentRequired.class)) {
            return invocation.invoke();
        }

        final Map<String, Object> session = ActionContext.getContext().getSession();
        final String email = (String) session.get("userEmail");
        User user = userService.find(email);
        if (user == null) {
            return "loginRedirect";
        }

        if (!(user instanceof Student)) {
            return "forbidden";
        }

        return invocation.invoke();
    }
}
