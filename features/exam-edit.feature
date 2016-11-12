Feature: Edit exam 
	As a lecturer I want to be able to edit an exam.

Background: 
	Given a valid user exists 
	And a user is activated 
	And a user is a lecturer 
	And a valid exam exists 
	And the start date of the exam is not reached 
	
Scenario: Valid editing 
	Given the lecturer wants to edit the exam 
	When he enters valid data 
	And clicks on update exam 
	Then he is redirected to the lecturer home page 
	
Scenario: Invalid data input 
	Given The lecturer wants to edit the exam 
	When the exam name is empty 
	When the exam duration is not a positive number 
	When minimal points is not a number between 1 and 100 
	When credit points is not selected 
	When evalutation method is not selected 
	When exam period is not selected 
	And clicks on create exam 
	Then a specific error message is displayed 
