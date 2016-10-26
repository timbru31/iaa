package de.nordakademie.iaa_multiple_choice.web.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.nordakademie.iaa_multiple_choice.domain.Exam;
import de.nordakademie.iaa_multiple_choice.service.ExamService;

@RestController
@RequestMapping("/exams")
public class ExamRestController {

    @Autowired
    private ExamService examService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Exam>> getAllExams() {
        return new ResponseEntity<>(examService.listAll(), HttpStatus.OK);
    }
}
