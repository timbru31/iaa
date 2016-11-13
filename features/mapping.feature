Feature: Mapping
  As a lecturer I want to be able to map students with exams.

  Background:
    Given a valid user exists
      And a user is a lecturer
      And a user is a student
      And all users are activated
      And a valid exam exists
      And a the start date of the exam is not reached

  Scenario: Valid mapping
    Given the lecturer wants to map student/ exam
    When he enters valid data
      And clicks on assign
    Then the student will be mapped with the exam and an unique token will be send to the student (via e-mail)

  Scenario: Invalid data input
    Given the lecturer wants to assign a student
    When the student is non-existent
    Then an error message is displayed
