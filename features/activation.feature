Feature: Activating a user account
  The user account should be activated when the correct activationToken is supplied, via the registration e-mail.

  Background:
    Given a user that is not yet activated exists
      And mailing is enabled

  Scenario: Activation without a token
    Given the user visits the activation page without a token
    Then the activation error page is displayed

  Scenario: Activation with mailing disabled
    Given the user visits the activation page
      And mailing is disabled
    Then the user is redirected to the home page

  Scenario: Activation with an invalid token
    Given the user visits the activation page with an invalid token
    Then the activation error page is displayed

  Scenario: Activation with the correct token
    Given the user visits the activation page with the correct token
    Then the user gets activated
      And the user is redirected to the home page

  Scenario: Activation with an activated user
    Given the user is activated and loggedn in
      And the user visits the activation page
    Then the user is redirected to the home page
