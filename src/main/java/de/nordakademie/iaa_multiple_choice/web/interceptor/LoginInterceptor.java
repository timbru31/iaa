package de.nordakademie.iaa_multiple_choice.web.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import de.nordakademie.iaa_multiple_choice.web.util.LoginRequired;

/**
 * @author Jens Gottwald interceptor for login
 */
public class LoginInterceptor extends AbstractInterceptor {
    private static final long serialVersionUID = 4518828269270091937L;

    @Override
    public String intercept(final ActionInvocation invocation) throws Exception {
        final Map<String, Object> session = ActionContext.getContext().getSession();
        final String user = (String) session.get("userEmail");
        if (user != null) {
            return invocation.invoke();
        }

        final Object action = invocation.getAction();
        if (!action.getClass().isAnnotationPresent(LoginRequired.class)) {
            return invocation.invoke();
        }

        if (action.getClass().isAnnotationPresent(LoginRequired.class)) {
            return "loginRedirect";
        }

        return invocation.invoke();
    }
}
