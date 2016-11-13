Feature: Edit exam
  As a lecturer I want to be able to edit an exam.

  Background:
  Given a valid user exists
    And a user is activated
    And a user is a lecturer
    And a valid exam exists
    And the start date of the exam is not reached

  Scenario: Valid editing
    Given the lecturer wants to edit the exam
      And the user visits the edit exam page
    When the user enters valid data
      And the user clicks on update exam
    Then he is redirected to the lecturer home page

  Scenario: Invalid data input
    Given the lecturer wants to edit the exam
      And the user visits the edit exam page
    When the exam name is empty
      And the exam duration is not a positive number
      And minimal points is not a number between 1 and 100
      And credit points is not selected
      And evalutation method is not selected
      And exam period is not selected
      And the user clicks on update exam
    Then a specific error message is displayed

  # Note: scenario is focusing on protection against direct POST requests
  Scenario: Exam is not editable
    Given the exam is not editable
    When the user edits the exam
      And the user clicks on update exam
    Then an error message that the exam is not editable is displayed
      And the exam is not edited
