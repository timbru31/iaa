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

import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.ActionSupport;

import de.nordakademie.iaa_multiple_choice.domain.Lecturer;
import de.nordakademie.iaa_multiple_choice.service.PasswordAuthenticationService;
import de.nordakademie.iaa_multiple_choice.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring-test.xml" })
public class LoginActionTest extends StrutsSpringJUnit4TestCase<LoginAction> {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordAuthenticationService passwordAuthenticationService;
    private static boolean setUpIsDone = false;

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        if (setUpIsDone) {
            return;
        }
        Lecturer lecturer = new Lecturer("Max", "Mustermann", "max.mustermann@nordakademie.de",
                passwordAuthenticationService.hash("12345678".toCharArray()));
        userService.createUser(lecturer);
        setUpIsDone = true;
    }

    @Test
    public void testValidLogin() {
        request.setParameter("email", "max.mustermann@nordakademie.de");
        request.setParameter("password", "12345678");
        ActionProxy proxy = getActionProxy("/loginUser");
        Map<String, Object> sessionMap = new HashMap<String, Object>();
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        LoginAction loginAction = (LoginAction) proxy.getAction();
        proxy.setExecuteResult(false);
        String result = "";
        try {
            result = proxy.execute();
        } catch (Exception e) {
            e.printStackTrace();
            fail("Test failed due to exception");
        }
        assertEquals("Expected to have no fieldErrors", 0, loginAction.getFieldErrors().size());
        assertEquals("Expected to have result name successLecturer", "successLecturer", result);
    }

    @Test
    public void testInvalidEmail() {
        request.setParameter("email", "max.mustermann.invalid@nordakademie.de");
        request.setParameter("password", "12345678");
        ActionProxy proxy = getActionProxy("/loginUser");
        Map<String, Object> sessionMap = new HashMap<String, Object>();
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        LoginAction loginAction = (LoginAction) proxy.getAction();
        proxy.setExecuteResult(false);
        String result = "";
        try {
            result = proxy.execute();
        } catch (Exception e) {
            e.printStackTrace();
            fail("Test failed due to exception");
        }
        assertEquals("Expected to have one fieldError", 1, loginAction.getFieldErrors().size());
        assertTrue("Expected to have fieldError with key loginFailed",
                loginAction.getFieldErrors().containsKey("loginFailed"));
        assertEquals("Expected to have result name INPUT", ActionSupport.INPUT, result);
    }

    @Test
    public void testInvalidPassword() {
        request.setParameter("email", "max.mustermann@nordakademie.de");
        request.setParameter("password", "123456789-invalid");
        ActionProxy proxy = getActionProxy("/loginUser");
        Map<String, Object> sessionMap = new HashMap<String, Object>();
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        LoginAction loginAction = (LoginAction) proxy.getAction();
        proxy.setExecuteResult(false);
        String result = "";
        try {
            result = proxy.execute();
        } catch (Exception e) {
            e.printStackTrace();
            fail("Test failed due to exception");
        }
        assertEquals("Expected to have one fieldError", 1, loginAction.getFieldErrors().size());
        assertTrue("Expected to have fieldError with key loginFailed",
                loginAction.getFieldErrors().containsKey("loginFailed"));
        assertEquals("Expected to have result name INPUT", ActionSupport.INPUT, result);
    }

}
