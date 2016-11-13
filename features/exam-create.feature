Feature: Create exam
  As a lecturer I want to be able to create an exam.

  Background:
    Given a valid user exists
      And a user is activated
      And a user is a lecturer

  Scenario: Valid creation
    Given the lecturer wants to create the exam
      And the user visits the create exam page
    When the user enters valid data
      And the user clicks on create exam
    Then he is redirected to the create question page

  Scenario: Invalid data input
    Given the lecturer wants to create the exam
    When the exam name is empty
      And the exam duration is not a positive number
      And minimal points is not a number between 1 and 100
      And credit points is not selected
      And evalutation method is not selected
      And exam period is not selected
      And the user clicks on create exam
    Then a specific error message is displayed
