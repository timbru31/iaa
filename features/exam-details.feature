Feature: Viewing exam details
  As a student I want to view the exam details before taking the exam

  Background:
  Given a valid user exists
    And a user is activated
    And a user is a student
    And the user is logged in
    And a valid exam exists
    And the student is enrolled to the exam

  Scenario: Invalid exam id
    Given the user tries to view an exam
    When the exam id is invalid
    Then an error page (exam not found) is displayed

  Scenario: Not enrolled to exam
    Given the user tries to view an exam
    When he is not rolled in to the exam
    Then an error page (forbidden) is displayed

  Scenario: Exam finished
    Given the user tries to view an exam
    When has finished the exam
    Then he is redirected to the exam result page

  Scenario: Exam not finished and not yet due dated
    Given the user tries to view an exam
    When has not finished the exam
      And the exam is not due dated
    Then he can view the exam detail page
      And there is no input field for enrolling

  Scenario: Exam not finished and due dated
    Given the user tries to view an exam
    When has not finished the exam
      And the exam is due dated
    Then he can view the exam detail page
      And there is an input field for enrolling
