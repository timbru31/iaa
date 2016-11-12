Feature: Registering a user
  As a user I want to be able to register for the application.

  Scenario: Registration disabled
    Given registration is disabled
    When the user visits the registration page
    Then an error message is displayed

  Scenario: Valid registration with mailing enabled
    Given registration is enabled
      And mailing is enabled
    When the user visits the registration page
      And enters valid information
      And clicks register
    Then he is redirected to the activation pending page
      And received an e-mail with the activation link

  Scenario: Valid registration with mailing disabled
    Given registration is enabled
      And mailing is disabled
    When the user visits the registration page
      And enters valid information
      And clicks register
    Then he is redirected to the home page
      And is logged in

  # Note this could be an outline, saying which specific information is wrong
  Scenario: Invalid registration
    Given registration is enabled
      And mailing is disabled
    When the user visits the registration page
      And enters invalid information
      And clicks register
    Then an error message is displayed

  Scenario: Invalid registration with duplicate e-mail
    Given registration is enabled
      And mailing is disabled
      And a user with this e-mail exists
    When the user visits the registration page
      And enters valid information
      And clicks register
    Then an error message is displayed
