Feature: Create question
  As a lecturer I want to be able to add a question to an exam (create question).

  Background:
    Given a valid user exists
      And a user is activated
      And a user is a lecturer
      And a valid exam exists

  Scenario: Valid creation
    Given The lecturer wants to create the question
    When he enters valid data
      And clicks on save question and create next one
    Then the question is added to the exam and he is redirected to the create question page

  Scenario: Invalid data input
    Given The lecturer wants to create a question
    When the question type is not selected
    When the question text is empty
    When the answer text is empty on single choice/ multiple choice questions
    When the points for the correct answer is empty
      And clicks on save question and create next one
    Then a specific error message is displayed
