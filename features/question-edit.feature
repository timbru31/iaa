Feature: Edit question
  As a lecturer I want to be able to edit a question of an exam (edit question).

  Background:
    Given a valid user exists
      And a user is activated
      And a user is a lecturer
      And a valid exam exists
      And the exam has questions

  Scenario: Valid edit
    Given the lecturer wants to edit a question
      And he edits and exam
      And he clicks on edit questions
    When he enters valid data
      And clicks on update question
    Then the question is updated

  Scenario: Invalid data input
    Given the lecturer wants to edit a question
      And he edits and exam
      And he clicks on edit questions
    When the question text is empty
      And the answer text is empty on single choice/multiple choice questions
      And the points for the correct answer is empty
      And clicks on update question
    Then a specific error message is displayed
