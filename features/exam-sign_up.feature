Feature: Signing up for an exam 
	As a user I want to be able to sign up for the exam.
	
Background: 
	Given a valid student is logged in 
	And a valid exam exists 
	And the student is mapped with the exam 
	And a the start date of the exam is reached 
	And the end date of the exam is not exceeded
	And a valid token has been sent to the users email
	
Scenario: 
	When a valid token has been entered
	And the user clicks on sign up
	Then he is redirected to the take exam page
	
Scenario:
	When an invalid token has been entered
	And the user clicks on sign up
	Then an error message is displayed