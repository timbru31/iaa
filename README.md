# 🎓 IAA Hausarbeit - Multiple Choice 📝

[![No Maintenance Intended](http://unmaintained.tech/badge.svg)](http://unmaintained.tech/)

[![Build Status](https://travis-ci.org/timbru31/iaa-multiple-choice.svg?branch=master)](https://travis-ci.org/timbru31/iaa-multiple-choice)
[![Known Vulnerabilities](https://snyk.io/test/github/timbru31/iaa-multiple-choice/badge.svg)](https://snyk.io/test/github/timbru31/iaa-multiple-choice)

### Authors

Jens Gottwald, Hannes Peterson, Yannick Rump, Tim Brust

## Info

This application provides a multiple choice test for seminars of the NORDAKADEMIE. It allows lecturers to create new exams with either a single choice question, a multiple choice question or a gap text.  
It's possible to add students to the exam via their e-mail address. The students can login into the web frontend and take the exam. They are not able to repeat it, if they fail.  
The system is restricted to NORDAKADEMIE enrolled students and lecturers only.  
Technologies used are Hibernate, Spring and Struts2, jQuery (with plugins) and Bootstrap (with plugins).

## Prerequisites

1. Install the following components
1. JDK 11
1. IDE (e.g. IntelliJ or eclipse)
1. Lombok
1. Maven 3
1. Tomcat v9 (or similar webserver)

## Installation

1. Clone the project
2. Create a `user.properties` in `src/main/resources/`
3. add a correct `database.url` for the H2 database
   1. See below for the default database installation instructions
4. Run the project. Visit [http://localhost:8080/iaa-multiple-choice](http://localhost:8080/iaa-multiple-choice)

## Configuration

### Mail sending

You can enable mail sending by adding the flag `mail.disabled=false` to your `user.properties`.  
You need to configure both `mail.username` and `mail.password` with valid Gmail credentials or use your own mail server.  
**This will enable the need to activate the user accounts and mailing of tokens for exams!**  
Besides that you can force to override the recipient by adding `mail.overrideRecipient=your@email` to your `user.properties`.

### Registration

Per default registration of new users is enabled. To disable this set `registration.enabled=false` in your `user.properties`.

## Default database

You might want to use the pre-configured database in `docs/db/iaa-multiple-choice.h2.db` (credentials are listed below). **Make sure to set `database.mode` to `update`**  
If this does not work, there is an import script located in `docs/db/import.sql`. Copy it to `src/main/resources`.  
To use it while Hibernate starts, add the following property to the `spring.xml` (bean id _entityManagerFactory_):  
`<prop key="hibernate.hbm2ddl.import_files">import.sql</prop>`

## Default credentials

can be found under [docs/EXAMPLE_CREDENTIALS.md](docs/EXAMPLE_CREDENTIALS.md)
