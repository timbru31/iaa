package de.nordakademie.iaa_multiple_choice.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.StrutsSpringJUnit4TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionProxy;

import de.nordakademie.iaa_multiple_choice.domain.Lecturer;
import de.nordakademie.iaa_multiple_choice.service.PasswordAuthenticationService;
import de.nordakademie.iaa_multiple_choice.service.UserService;

/**
 * LoginAction test for invalid and valid logins.
 *
 * @author Tim Brust
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring-test.xml" })
public class LoginActionTest extends StrutsSpringJUnit4TestCase<LoginAction> {
    private static boolean setUpIsDone = false;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordAuthenticationService passwordAuthenticationService;

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        if (setUpIsDone) {
            return;
        }
        final Lecturer lecturer = new Lecturer("John", "Doe", "john.doe@nordakademie.de",
                passwordAuthenticationService.hash("12345678".toCharArray()), "token");
        lecturer.setActivated(true);
        userService.createUser(lecturer);
        setUpIsDone = true;
    }

    /**
     * Tests login with an invalid email.
     */
    @Test
    public void testInvalidEmail() {
        request.setParameter("email", "john.doe.invalid@nordakademie.de");
        request.setParameter("password", "12345678");
        final ActionProxy proxy = getActionProxy("/loginUser");
        final Map<String, Object> sessionMap = new HashMap<>();
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        final LoginAction loginAction = (LoginAction) proxy.getAction();
        proxy.setExecuteResult(false);
        String result = "";
        try {
            result = proxy.execute();
        } catch (final Exception e) {
            e.printStackTrace();
            fail("Test failed due to exception");
        }
        assertEquals("Expected to have one fieldError", 1, loginAction.getFieldErrors().size());
        assertTrue("Expected to have fieldError with key loginFailed",
                loginAction.getFieldErrors().containsKey("loginFailed"));
        assertEquals("Expected to have result name INPUT", Action.INPUT, result);
    }

    /**
     * Test for a wrong password login attempt.
     */
    @Test
    public void testInvalidPassword() {
        request.setParameter("email", "john.doe@nordakademie.de");
        request.setParameter("password", "123456789-invalid");
        final ActionProxy proxy = getActionProxy("/loginUser");
        final Map<String, Object> sessionMap = new HashMap<>();
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        final LoginAction loginAction = (LoginAction) proxy.getAction();
        proxy.setExecuteResult(false);
        String result = "";
        try {
            result = proxy.execute();
        } catch (final Exception e) {
            e.printStackTrace();
            fail("Test failed due to exception");
        }
        assertEquals("Expected to have one fieldError", 1, loginAction.getFieldErrors().size());
        assertTrue("Expected to have fieldError with key loginFailed",
                loginAction.getFieldErrors().containsKey("loginFailed"));
        assertEquals("Expected to have result name INPUT", Action.INPUT, result);
    }

    /**
     * Test for successful login.
     */
    @Test
    public void testValidLogin() {
        request.setParameter("email", "john.doe@nordakademie.de");
        request.setParameter("password", "12345678");
        final ActionProxy proxy = getActionProxy("/loginUser");
        final Map<String, Object> sessionMap = new HashMap<>();
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        final LoginAction loginAction = (LoginAction) proxy.getAction();
        proxy.setExecuteResult(false);
        String result = "";
        try {
            result = proxy.execute();
        } catch (final Exception e) {
            e.printStackTrace();
            fail("Test failed due to exception");
        }
        assertEquals("Expected to have no fieldErrors", 0, loginAction.getFieldErrors().size());
        assertEquals("Expected to have result name successLecturer", "successLecturer", result);
    }

}
