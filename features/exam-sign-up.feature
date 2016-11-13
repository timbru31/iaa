Feature: Signing up for an exam
  As a user I want to be able to sign up for the exam.

Background:
  Given a valid student is logged in
    And a valid exam exists
    And the student is mapped with the exam
    And a the start date of the exam is reached
    And the end date of the exam is not exceeded
    And the user knows the token for the exam

Scenario: Entering a valid token
  Given the user is on the exam detail page
  When a valid token has been entered
    And the user clicks on sign up
  Then he is redirected to the take exam page

Scenario: Entering an invalid token
  Given the user is on the exam detail page
  When an invalid token has been entered
    And the user clicks on sign up
  Then an error message is displayed
