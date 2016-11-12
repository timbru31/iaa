Feature: Deleting an exam
  As a lecturer I want to be able to delete an exam.

  Background:
    Given a valid lecturer exists and is logged in

  # Note: scenario is focusing on protection against direct POST requests
  Scenario: Exam is not editable
    Given the exam is not editable
    When the edits the exam
      And he clicks on delete exam
    Then an error message that the exam is not editable is displayed
      And the exam is not deleted

  Scenario: Exam is editable
    Given the exam is editable
    When the edits the exam
      And he clicks on delete exam
    Then the exam is deleted
      And all students are removed
      And if mailing is enabled they are notified via e-mail
