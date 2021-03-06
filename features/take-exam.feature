Feature: Taking the exam
  As a student I want to take the exam

Background:
  Given a valid student is logged in
    And a valid exam exists
    And the student is mapped with the exam
    And a the start date of the exam is reached
    And the end date of the exam is not exceeded
    And the student is successfully signed up for the exam

Scenario: Finishing the exam
  Given the student wants to submit his answers on the test
  When he clicks on the submit button
  Then he is redirected to the exam result detail page
    And the exam result is evaluated

Scenario: Save answer
  Given the student wants to save the answer
  When he clicks on save answer
  Then the answer is saved
    And the progress bar is filled proportionately

Scenario: Next question
  Given the student wants to answer the next question
  When he clicks on the next button at the navigation bar
  Then he is redirected to the next question

Scenario: Time expires
  Given the student is taking on the exam
  When the time expires
  Then he is redirected to the exam result detail page
    And the exam is submitted
    And the exam result is evaluated
