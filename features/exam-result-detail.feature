Feature: Viewing the exam result detail
  As a student I want to be able to view the exam result in detail

  Background:
    Given the user is a student and logged in

  Scenario: Viewing the exam result page with an invalid exam id
    Given he is on the exam result page
      And the exam id is missing or invalid
    Then an exam not found error message is displayed

  Scenario: Viewing the exam result page and the student is not enrolled
    Given he is on the exam result page
      And and he is not enrolled for the exam
    Then a forbidden error message is displayed

  Scenario: Viewing the exam result page and the student has not taken the exam
    Given he is on the exam result page
      And the student has not taken the exam
    Then he is redirected to the home page
      And an error message is displayed

  Scenario: Viewing the exam result page and the student has not finished the exam
    Given he is on the exam result page
      And the student has not finished the exam
    Then he is redirected to the home page
      And an error message is displayed

  Scenario: Viewing the exam result page and the student has finished the exam
    Given he is on the exam result page
      And the student has  finished the exam
    Then he sees the exam result in detail
