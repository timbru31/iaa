Feature: Login to the application
  As a user I want to be able to login to the application.

  Background:
    Given a valid user exists
      And a user that is not yet activated exists

  Scenario: Valid login
    Given The user visits the login page
    When he enters valid credentials
      And clicks on login
    Then he is redirected to the start page

  Scenario: Wrong credentials
    Given The user visits the login page
    When he enters a invalid credentials
      And clicks on login
    Then an error message is displayed

  Scenario: User not activated
    Given The user visits the login page
      And the user is not yet activated
    When he enters valid credentials
      And clicks on login
    Then an error message is displayed
