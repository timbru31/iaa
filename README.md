# 🎓  IAA Hausarbeit - Multiple Choice 📝 [![Build Status](https://travis-ci.org/timbru31/iaa-multiple-choice.svg?branch=master)](https://travis-ci.org/timbru31/iaa-multiple-choice)
### Authors

Jens Gottwald, Hannes Peterson, Yannick Rump, Tim Brust

## Info

This application provides a multiple choice test for seminars of the NORDAKADEMIE. It allows lecturers to create new exams with either a single choice question, a multiple choice question or a gap text.  
It's possible to add students to the exam via their e-mail address. The students can login into the web frontend and take the exam. They are not able to repeat it, if they fail.  
The system is restricted to NORDAKADEMIE enrolled students and lecturers only.  
Technologies used are Hibernate, Spring and Struts2.

## Prerequisites

1. Install the following components
  1. JDK 8
  2. IDE
  3. Lombok
  4. Maven 3
  5. Tomcat v8 or v8.5 (or similiar webserver)

## Installation

1. Clone the project
2. Create a `user.properties` in `src/main/resources/` and add a correct `database.url` for the H2 database
3. Run the project. Visit [http://localhost:8080/iaa-multiple-choice](http://localhost:8080/iaa-multiple-choice)

## Configuration

You can enable mailing by adding the flag `mail.disabled=false` to your `user.properties`. You need to configure both `mail.username` and `mail.password` with valid Gmail credentials or use your own mail server. This will enable the need of activation of user accounts and mailing of tokens for exams!  
Besides that you can force to override the recipient by adding `mail.overrideRecipient=your@email` to your `user.properties`.
