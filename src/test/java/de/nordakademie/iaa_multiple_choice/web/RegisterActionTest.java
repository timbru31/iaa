package de.nordakademie.iaa_multiple_choice.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.StrutsSpringJUnit4TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.ActionSupport;

import de.nordakademie.iaa_multiple_choice.domain.Lecturer;
import de.nordakademie.iaa_multiple_choice.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring-test.xml" })
public class RegisterActionTest extends StrutsSpringJUnit4TestCase<RegisterAction> {
    @Autowired
    private UserService userService;

    @Test
    public void testDuplicateRegistration() {
        userService.createUser(
                new Lecturer("Erika", "Mustermann", "erika.mustermann@nordakademie.de", "password", "token"));
        request.setParameter("firstName", "Erika");
        request.setParameter("lastName", "Mustermann");
        request.setParameter("email", "erika.mustermann@gmail.com");
        request.setParameter("password", "12345678");
        request.setParameter("passwordRepeat", "12345678");
        final ActionProxy proxy = getActionProxy("/registerLecturer");
        final Map<String, Object> sessionMap = new HashMap<>();
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        final RegisterAction registerAction = (RegisterAction) proxy.getAction();
        proxy.setExecuteResult(false);
        String result = "";
        try {
            result = proxy.execute();
        } catch (final Exception e) {
            e.printStackTrace();
            fail("Test failed due to exception");
        }
        assertEquals("Expected to have no fieldErrors", 1, registerAction.getFieldErrors().size());
        assertTrue("Expected to have fieldError with key emailExists",
                registerAction.getFieldErrors().containsKey("email"));
        assertEquals("Expected to have result name INPUT", ActionSupport.INPUT, result);
    }

    @Test
    public void testInvalidRegistration() {
        request.setParameter("firstName", "Zu");
        request.setParameter("lastName", "");
        request.setParameter("email", "max.mustermann@gmail.com");
        request.setParameter("password", "123456");
        request.setParameter("passwordRepeat", "123456789");
        final ActionProxy proxy = getActionProxy("/registerLecturer");
        final Map<String, Object> sessionMap = new HashMap<>();
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        final RegisterAction registerAction = (RegisterAction) proxy.getAction();
        proxy.setExecuteResult(false);
        String result = "";
        try {
            result = proxy.execute();
        } catch (final Exception e) {
            e.printStackTrace();
            fail("Test failed due to exception");
        }
        assertEquals("Expected to have no fieldErrors", 4, registerAction.getFieldErrors().size());
        assertTrue("Expected to have fieldError with key firstName",
                registerAction.getFieldErrors().containsKey("firstName"));
        assertTrue("Expected to have fieldError with key lastName",
                registerAction.getFieldErrors().containsKey("lastName"));
        assertTrue("Expected to have fieldError with key email", registerAction.getFieldErrors().containsKey("email"));
        assertTrue("Expected to have fieldError with key password",
                registerAction.getFieldErrors().containsKey("password"));
        assertEquals("Expected to have result name INPUT", ActionSupport.INPUT, result);
    }

    @Test
    public void testValidRegistration() {
        request.setParameter("firstName", "Max");
        request.setParameter("lastName", "Mustermann");
        request.setParameter("email", "max.mustermann@nordakademie.de");
        request.setParameter("password", "12345678");
        request.setParameter("passwordRepeat", "12345678");
        request.setParameter("studentNumber", "1234");
        final ActionProxy proxy = getActionProxy("/registerStudent");
        final Map<String, Object> sessionMap = new HashMap<>();
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        final RegisterAction registerAction = (RegisterAction) proxy.getAction();
        proxy.setExecuteResult(false);
        String result = "";
        try {
            result = proxy.execute();
        } catch (final Exception e) {
            e.printStackTrace();
            fail("Test failed due to exception");
        }
        assertEquals("Expected to have no fieldErrors", 0, registerAction.getFieldErrors().size());
        assertEquals("Expected to have result name SUCCESS", ActionSupport.SUCCESS, result);
    }
}
