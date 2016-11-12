Feature: Permission check based on roles
  The application should check for the correct permissions

  Background:
    Given a lecturer exists
      And a student exists

  Scenario: Lecturer tries to view a page for students
    Given a lecturer tries to view a page for students
    Then a forbidden error is displayed

  Scenario: Student tries to view a page for lecturers
    Given a student tries to view a page for lecturers
    Then a forbidden error is displayed

  Scenario: User not logged in
    Given a not logged in user tries to view a page that requires a login
    Then he is redirected to the login page
