package de.nordakademie.iaa_multiple_choice.web.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.nordakademie.iaa_multiple_choice.domain.TestResult;
import de.nordakademie.iaa_multiple_choice.service.TestResultService;

@RestController
@RequestMapping("/testResults")
public class TestResultRestController {

    @Autowired
    private TestResultService testResultService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<TestResult>> getAllTestResults() {
        return new ResponseEntity<>(testResultService.listAll(), HttpStatus.OK);
    }
}
