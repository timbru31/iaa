Feature: Deleting a question from an exam
  As a lecturer I want to be able to delete a question from an exam.

  Background:
    Given a valid lecturer exists and is logged in

  # Note: scenario is focusing on protection against direct POST requests
  Scenario: Deleting a question from an exam which is not editable
    Given there is a valid exam which is not editable
      And the user is editing questions
    When he clicks delete question
    Then an error message is displayed that the exam is not editable
      And the question is not deleted

  Scenario: Deleting a question which is not part of an exam
    Given there is a valid exam which is editable
      And the question does not belong to the exam
      And the user is editing questions
    When he clicks delete question
    Then an error message is displayed that the question was not found
      And the question is not deleted

  Scenario: Deleting a question
    Given there is a valid exam which is editable
      And the question does belong to the exam
      And the user is editing questions
    When he clicks delete question
    Then the questions gets deleted
      And the user is redirected to the edit question page with the previous question (in order)

  Scenario: Deleting the last question
    Given there is a valid exam which is editable
      And the question does belong to the exam
      And is the last question of the exam
      And the user is editing questions
    When he clicks delete question
    Then the questions gets deleted
      And the user is redirected to the create question page

  Scenario: Deleting the first question
    Given there is a valid exam which is editable
      And the question does belong to the exam
      And is the first question of the exam
      And the user is editing questions
    When he clicks delete question
    Then the questions gets deleted
      And the user is redirected to the edit question page with the next question (in order)
