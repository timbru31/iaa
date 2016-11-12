Feature: Viewing the exam results
  As a student I want to be able to view the exam results

  Background:
    Given the user is a student and logged in

  Scenario: Viewing the exam results page without any exams finsihed
    Given the user clicks on the exam results link
      And the user has not finished any exams yet
    When he is on the exam results page
    Then no exam results are displayed
      But a friendly error message is displayed

  Scenario: Viewing the exam results page with exams finsihed
    Given the user clicks on the exam results link
      And the user has finished exams
    When he is on the exam results page
    Then exam results are displayed in a table
