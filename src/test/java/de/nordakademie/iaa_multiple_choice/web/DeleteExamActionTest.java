package de.nordakademie.iaa_multiple_choice.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
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

import de.nordakademie.iaa_multiple_choice.domain.CreditPointType;
import de.nordakademie.iaa_multiple_choice.domain.Exam;
import de.nordakademie.iaa_multiple_choice.domain.Lecturer;
import de.nordakademie.iaa_multiple_choice.domain.WrongAnswerEvaluationMethod;
import de.nordakademie.iaa_multiple_choice.service.ExamService;
import de.nordakademie.iaa_multiple_choice.service.PasswordAuthenticationService;
import de.nordakademie.iaa_multiple_choice.service.UserService;

/**
 * Test for DeleteExam action. Tests that exam is valid and editable.
 *
 * @author Tim Brust
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring-test.xml" })
public class DeleteExamActionTest extends StrutsSpringJUnit4TestCase<DeleteExamAction> {
    private static boolean setUpIsDone = false;
    @Autowired
    private UserService userService;
    @Autowired
    private ExamService examService;
    @Autowired
    private PasswordAuthenticationService passwordAuthenticationService;
    private Exam exam;
    private boolean recreationNeeded;

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        if (setUpIsDone && !recreationNeeded) {
            exam = examService.listAll().get(0);
            return;
        }
        exam = new Exam();
        exam.setName("Test");
        exam.setCreditPoints(CreditPointType.HALF);
        exam.setEvaluationMethod(WrongAnswerEvaluationMethod.NO_SUBTRACTION);
        exam.setMinPoints(50);
        exam.setQuestions(new HashSet<>());
        exam.setExamResults(new HashSet<>());
        exam.setExamTime(60);
        exam.setEndDate(LocalDate.now().plusDays(20L));
        exam.setStartDate(LocalDate.now());
        exam.setTokenList(new HashMap<>());
        examService.createExam(exam);
        if (!recreationNeeded) {
            final Lecturer lecturer = new Lecturer("John", "Doe", "john.doe.delete.exam@nordakademie.de",
                    passwordAuthenticationService.hash("12345678".toCharArray()), "token");
            lecturer.setActivated(true);
            userService.createUser(lecturer);
        }
        recreationNeeded = false;
        setUpIsDone = true;
    }

    /**
     * Tests that an ExamNotEditable exception is thrown.
     */
    @Test
    public void testExamNotEditable() {
        exam.setStartDate(LocalDate.now().minusDays(2L));
        examService.updateExam(exam);
        request.setParameter("examId", exam.getId().toString());
        final ActionProxy proxy = getActionProxy("/deleteExam");
        final Map<String, Object> sessionMap = new HashMap<>();
        sessionMap.put("userEmail", "john.doe.delete.exam@nordakademie.de");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        proxy.setExecuteResult(false);
        String result = "";
        try {
            result = proxy.execute();
        } catch (final Exception e) {
            e.printStackTrace();
            fail("Test failed due to exception");
        }
        assertEquals("Expected to have result name examNotEditable", "examNotEditable", result);
    }

    /**
     * Tests that invalid exam id's are correctly handled.
     */
    @Test
    public void testInvalidExamId() {
        request.setParameter("examId", "55");
        final ActionProxy proxy = getActionProxy("/deleteExam");
        final Map<String, Object> sessionMap = new HashMap<>();
        sessionMap.put("userEmail", "john.doe.delete.exam@nordakademie.de");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        proxy.setExecuteResult(false);
        String result = "";
        try {
            result = proxy.execute();
        } catch (final Exception e) {
            e.printStackTrace();
            fail("Test failed due to exception");
        }
        assertEquals("Expected to have result name examNotFound", "examNotFound", result);
    }

    /**
     * Tests a successful exam deletion.
     */
    @Test
    public void testSuccessfulDeletion() {
        exam.setStartDate(LocalDate.now().plusDays(2L));
        examService.updateExam(exam);
        request.setParameter("examId", exam.getId().toString());
        final ActionProxy proxy = getActionProxy("/deleteExam");
        final Map<String, Object> sessionMap = new HashMap<>();
        sessionMap.put("userEmail", "john.doe.delete.exam@nordakademie.de");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        proxy.setExecuteResult(false);
        String result = "";
        try {
            result = proxy.execute();
        } catch (final Exception e) {
            e.printStackTrace();
            fail("Test failed due to exception");
        }
        assertEquals("Expected to have result name success", Action.SUCCESS, result);
        recreationNeeded = true;
    }
}
