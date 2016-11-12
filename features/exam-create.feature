Feature: Create exam
  As a lecturer I want to be able to create an exam.

  Background:
    Given a valid user exists
      And a user is activated
      And a user is a lecturer

  Scenario: Valid creation
    Given The lecturer wants to create the exam
    When he enters valid data
      And clicks on create exam
    Then he is redirected to the create question page

  Scenario: Invalid data input
    Given The lecturer wants to create the exam
    When the exam name is empty
    When the exam duration is not a positive number
    When minimal points is not a number between 1 and 100
    When credit points is not selected
    When evalutation method is not selected
    When exam period is not selected
      And clicks on create exam
    Then a specific error message is displayed
