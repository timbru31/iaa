Feature: Viewing the token list
  As a lecturer I want to be able to view the token list for the exam, when mailing is disabled

  Background:
    Given a valid user exists
      And a user is activated
      And a user is a lecturer

  Scenario: Mailing enabled
    Given mailing is enabled
    When the user visits the lecturer home page
    Then no button is displayed to view the token list

  Scenario: Mailing enabled and direct visiting the page
    Given mailing is enabled
    When the user visits the token list page for an exam
    Then the user is redirected to the home page

  Scenario: Mailing disabled and invalid exam id
    Given mailing is disabled
    When the user visits the token list page for an exam
      And the exam id is invalid or not present
    Then an error message is displayed

  Scenario: Mailing disabled
    Given mailing is disabled
    When the user visits the token list page for an exam
    Then the user can see the token list for the exam
