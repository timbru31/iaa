package de.nordakademie.iaa_multiple_choice.web.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.nordakademie.iaa_multiple_choice.domain.Question;
import de.nordakademie.iaa_multiple_choice.service.QuestionService;

@RestController
@RequestMapping("/questions")
public class QuestionRestController {

    @Autowired
    private QuestionService questionService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Question>> getAllExams() {
        return new ResponseEntity<>(questionService.listAll(), HttpStatus.OK);
    }
}
