;             
CREATE USER IF NOT EXISTS "IAA-MULTIPLE-CHOICE" SALT '73556acc0403f4b4' HASH 'a55f89b0574b59079763ea5d33920bed877e5aaa0598305242e7d96225a27475' ADMIN;        
CREATE SEQUENCE PUBLIC.HIBERNATE_SEQUENCE START WITH 126;     
CREATE CACHED TABLE PUBLIC.ANSWER(
    ID BIGINT NOT NULL,
    RIGHTANSWER BOOLEAN NOT NULL,
    TEXT VARCHAR(255)
);     
ALTER TABLE PUBLIC.ANSWER ADD CONSTRAINT PUBLIC.CONSTRAINT_7 PRIMARY KEY(ID); 
-- 59 +/- SELECT COUNT(*) FROM PUBLIC.ANSWER; 
INSERT INTO PUBLIC.ANSWER(ID, RIGHTANSWER, TEXT) VALUES
(9, TRUE, 'Struts2'),
(10, FALSE, 'React'),
(11, FALSE, 'Angular'),
(13, TRUE, 'Hibernate'),
(14, TRUE, 'Spring'),
(15, FALSE, 'React'),
(16, FALSE, 'Redux'),
(17, TRUE, 'Struts2'),
(19, TRUE, '14.11.2016'),
(21, TRUE, 'Jens'),
(22, TRUE, 'Tim'),
(23, TRUE, 'Peterson'),
(24, TRUE, 'Yannick'),
(28, TRUE, 'Volkswirtschaftslehre'),
(29, FALSE, STRINGDECODE('Verm\u00f6genswirksame Leistung')),
(31, TRUE, 'Nichtfinanzielle Kapitalgesellschaften'),
(32, TRUE, 'Finanzielle Kapitalgesellschaften'),
(33, TRUE, 'Staat'),
(34, TRUE, 'Private Organisationen ohne Erwerbszweck'),
(35, TRUE, 'Private Haushalte'),
(37, TRUE, 'Abschreibungen'),
(39, TRUE, 'Privater Haushalt'),
(40, TRUE, 'Unternehmen'),
(41, FALSE, 'Staat'),
(44, TRUE, '1960'),
(45, TRUE, 'Softwaretechnik'),
(46, TRUE, 'Softwarekrise'),
(48, FALSE, 'sind alle gleich'),
(49, TRUE, 'unterschiedlich'),
(50, FALSE, STRINGDECODE('nicht relevant f\u00fcr die Praxis')),
(52, TRUE, 'Prinzip der Abstraktion'),
(53, FALSE, 'Prinzip der Einfachheit'),
(54, FALSE, 'Prinzip der Wartbarkeit'),
(55, TRUE, 'Prinzip der Strukturierung'),
(56, TRUE, 'Prinzip der Hierarchisierung'),
(57, FALSE, 'Prinzip der Testbarkeit'),
(58, TRUE, 'Prinzip der Modularisierung'),
(60, FALSE, 'User Mode Linux'),
(61, TRUE, 'Unified Modeling Language'),
(62, FALSE, 'University of Massachusetts Lowell'),
(102, TRUE, '1960'),
(103, TRUE, 'Softwaretechnik'),
(104, TRUE, 'Krise'),
(106, TRUE, 'sind alle gleich'),
(107, FALSE, 'unterschiedlich'),
(108, FALSE, STRINGDECODE('nicht relevant f\u00fcr die Praxis')),
(110, TRUE, 'Prinzip der Abstraktion'),
(111, TRUE, 'Prinzip der Einfachheit'),
(112, TRUE, 'Prinzip der Wartbarkeit'),
(113, FALSE, 'Prinzip der Strukturierung'),
(114, TRUE, 'Prinzip der Hierarchisierung'),
(115, FALSE, 'Prinzip der Testbarkeit'),
(116, TRUE, 'Prinzip der Modularisierung'),
(118, TRUE, 'User Mode Linux'),
(119, FALSE, 'Unified Modeling Language'),
(120, FALSE, 'University of Massachusetts Lowell'),
(123, TRUE, '1960'),
(124, TRUE, 'Softwaretechnik'),
(125, TRUE, '...');    
CREATE CACHED TABLE PUBLIC.EXAM(
    ID BIGINT NOT NULL,
    CREDITPOINTS VARCHAR(255),
    ENDDATE DATE,
    EVALUATIONMETHOD VARCHAR(255),
    EXAMTIME INTEGER,
    MINPOINTS INTEGER,
    NAME VARCHAR(255),
    STARTDATE DATE
);               
ALTER TABLE PUBLIC.EXAM ADD CONSTRAINT PUBLIC.CONSTRAINT_2 PRIMARY KEY(ID);   
-- 3 +/- SELECT COUNT(*) FROM PUBLIC.EXAM;    
INSERT INTO PUBLIC.EXAM(ID, CREDITPOINTS, ENDDATE, EVALUATIONMETHOD, EXAMTIME, MINPOINTS, NAME, STARTDATE) VALUES
(7, 'THREEQUARTER', DATE '2016-11-28', 'NO_SUBTRACTION', 60, 50, 'IAA', DATE '2016-11-14'),
(26, 'ONE', DATE '2016-11-30', 'SUBTRACTION', 90, 75, 'VWL', DATE '2016-11-23'),
(42, 'HALF', DATE '2016-11-14', 'NO_SUBTRACTION', 20, 20, 'Softwaretechnik', DATE '2016-10-28');            
CREATE CACHED TABLE PUBLIC.EXAM_EXAMRESULT(
    EXAM_ID BIGINT NOT NULL,
    EXAMRESULTS_ID BIGINT NOT NULL
);             
ALTER TABLE PUBLIC.EXAM_EXAMRESULT ADD CONSTRAINT PUBLIC.CONSTRAINT_A PRIMARY KEY(EXAM_ID, EXAMRESULTS_ID);   
-- 2 +/- SELECT COUNT(*) FROM PUBLIC.EXAM_EXAMRESULT;         
INSERT INTO PUBLIC.EXAM_EXAMRESULT(EXAM_ID, EXAMRESULTS_ID) VALUES
(42, 100),
(42, 121);    
CREATE CACHED TABLE PUBLIC.EXAM_TESTRESULT(
    EXAM_ID BIGINT NOT NULL,
    TESTRESULTS_ID BIGINT NOT NULL
);             
ALTER TABLE PUBLIC.EXAM_TESTRESULT ADD CONSTRAINT PUBLIC.CONSTRAINT_B PRIMARY KEY(EXAM_ID, TESTRESULTS_ID);   
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.EXAM_TESTRESULT;         
CREATE CACHED TABLE PUBLIC.EXAM_QUESTION(
    EXAM_ID BIGINT NOT NULL,
    QUESTIONS_ID BIGINT NOT NULL
); 
ALTER TABLE PUBLIC.EXAM_QUESTION ADD CONSTRAINT PUBLIC.CONSTRAINT_25 PRIMARY KEY(EXAM_ID, QUESTIONS_ID);      
-- 12 +/- SELECT COUNT(*) FROM PUBLIC.EXAM_QUESTION;          
INSERT INTO PUBLIC.EXAM_QUESTION(EXAM_ID, QUESTIONS_ID) VALUES
(7, 8),
(7, 12),
(7, 18),
(7, 20),
(26, 27),
(26, 30),
(26, 36),
(26, 38),
(42, 43),
(42, 47),
(42, 51),
(42, 59); 
CREATE CACHED TABLE PUBLIC.EXAMRESULT(
    ID BIGINT NOT NULL,
    ENDTIME TIMESTAMP,
    PASSED BOOLEAN NOT NULL,
    POINTS INTEGER NOT NULL,
    STARTTIME TIMESTAMP,
    EXAM_ID BIGINT,
    STUDENT_ID BIGINT
); 
ALTER TABLE PUBLIC.EXAMRESULT ADD CONSTRAINT PUBLIC.CONSTRAINT_A8 PRIMARY KEY(ID);            
-- 2 +/- SELECT COUNT(*) FROM PUBLIC.EXAMRESULT;              
INSERT INTO PUBLIC.EXAMRESULT(ID, ENDTIME, PASSED, POINTS, STARTTIME, EXAM_ID, STUDENT_ID) VALUES
(100, TIMESTAMP '2016-11-13 16:06:31.946', TRUE, 5, TIMESTAMP '2016-11-13 15:51:41.858', 42, 2),
(121, TIMESTAMP '2016-11-13 16:09:59.24', FALSE, 2, TIMESTAMP '2016-11-13 16:09:34.433', 42, 3);         
CREATE CACHED TABLE PUBLIC.EXAMRESULT_SUBMITTED_ANSWERS(
    ID BIGINT NOT NULL,
    SUBMITTEDANSWERS_ID BIGINT NOT NULL,
    SUBMITTEDANSWERS_KEY BIGINT NOT NULL
);     
ALTER TABLE PUBLIC.EXAMRESULT_SUBMITTED_ANSWERS ADD CONSTRAINT PUBLIC.CONSTRAINT_D PRIMARY KEY(ID, SUBMITTEDANSWERS_KEY);     
-- 5 +/- SELECT COUNT(*) FROM PUBLIC.EXAMRESULT_SUBMITTED_ANSWERS;            
INSERT INTO PUBLIC.EXAMRESULT_SUBMITTED_ANSWERS(ID, SUBMITTEDANSWERS_ID, SUBMITTEDANSWERS_KEY) VALUES
(100, 101, 43),
(100, 105, 47),
(100, 109, 51),
(100, 117, 59),
(121, 122, 43);    
CREATE CACHED TABLE PUBLIC.EXAMRESULTANSWERS(
    ID BIGINT NOT NULL
);     
ALTER TABLE PUBLIC.EXAMRESULTANSWERS ADD CONSTRAINT PUBLIC.CONSTRAINT_F6 PRIMARY KEY(ID);     
-- 5 +/- SELECT COUNT(*) FROM PUBLIC.EXAMRESULTANSWERS;       
INSERT INTO PUBLIC.EXAMRESULTANSWERS(ID) VALUES
(101),
(105),
(109),
(117),
(122);       
CREATE CACHED TABLE PUBLIC.EXAMRESULTANSWERS_ANSWER(
    EXAMRESULTANSWERS_ID BIGINT NOT NULL,
    ANSWERS_ID BIGINT NOT NULL
);           
ALTER TABLE PUBLIC.EXAMRESULTANSWERS_ANSWER ADD CONSTRAINT PUBLIC.CONSTRAINT_2D PRIMARY KEY(EXAMRESULTANSWERS_ID, ANSWERS_ID);
-- 19 +/- SELECT COUNT(*) FROM PUBLIC.EXAMRESULTANSWERS_ANSWER;               
INSERT INTO PUBLIC.EXAMRESULTANSWERS_ANSWER(EXAMRESULTANSWERS_ID, ANSWERS_ID) VALUES
(101, 102),
(101, 103),
(101, 104),
(105, 106),
(105, 107),
(105, 108),
(109, 110),
(109, 111),
(109, 112),
(109, 113),
(109, 114),
(109, 115),
(109, 116),
(117, 118),
(117, 119),
(117, 120),
(122, 123),
(122, 124),
(122, 125);   
CREATE CACHED TABLE PUBLIC.LECTURER(
    ID BIGINT NOT NULL
);              
ALTER TABLE PUBLIC.LECTURER ADD CONSTRAINT PUBLIC.CONSTRAINT_9 PRIMARY KEY(ID);               
-- 2 +/- SELECT COUNT(*) FROM PUBLIC.LECTURER;
INSERT INTO PUBLIC.LECTURER(ID) VALUES
(1),
(6);            
CREATE CACHED TABLE PUBLIC.LECTURER_EXAM(
    LECTURER_ID BIGINT NOT NULL,
    EXAMS_ID BIGINT NOT NULL
); 
ALTER TABLE PUBLIC.LECTURER_EXAM ADD CONSTRAINT PUBLIC.CONSTRAINT_5 PRIMARY KEY(LECTURER_ID, EXAMS_ID);       
-- 3 +/- SELECT COUNT(*) FROM PUBLIC.LECTURER_EXAM;           
INSERT INTO PUBLIC.LECTURER_EXAM(LECTURER_ID, EXAMS_ID) VALUES
(6, 7),
(6, 26),
(6, 42);   
CREATE CACHED TABLE PUBLIC.STUDENT_TESTRESULT(
    STUDENT_ID BIGINT NOT NULL,
    TESTRESULTS_ID BIGINT NOT NULL
);       
ALTER TABLE PUBLIC.STUDENT_TESTRESULT ADD CONSTRAINT PUBLIC.CONSTRAINT_29 PRIMARY KEY(STUDENT_ID, TESTRESULTS_ID);            
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.STUDENT_TESTRESULT;      
CREATE CACHED TABLE PUBLIC.TESTRESULT(
    ID BIGINT NOT NULL,
    ENDTIME TIMESTAMP,
    PASSED BOOLEAN NOT NULL,
    POINTS INTEGER,
    STARTTIME TIMESTAMP,
    EXAM_ID BIGINT,
    STUDENT_ID BIGINT
);          
ALTER TABLE PUBLIC.TESTRESULT ADD CONSTRAINT PUBLIC.CONSTRAINT_B7 PRIMARY KEY(ID);            
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.TESTRESULT;              
CREATE CACHED TABLE PUBLIC.TESTRESULT_SUBMITTED_ANSWERS(
    ID BIGINT NOT NULL,
    SUBMITTEDANSWERS_ID BIGINT NOT NULL,
    SUBMITTEDANSWERS_KEY BIGINT NOT NULL
);     
ALTER TABLE PUBLIC.TESTRESULT_SUBMITTED_ANSWERS ADD CONSTRAINT PUBLIC.CONSTRAINT_71 PRIMARY KEY(ID, SUBMITTEDANSWERS_KEY);    
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.TESTRESULT_SUBMITTED_ANSWERS;            
CREATE CACHED TABLE PUBLIC.TESTRESULTANSWERS(
    ID BIGINT NOT NULL
);     
ALTER TABLE PUBLIC.TESTRESULTANSWERS ADD CONSTRAINT PUBLIC.CONSTRAINT_70 PRIMARY KEY(ID);     
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.TESTRESULTANSWERS;       
CREATE CACHED TABLE PUBLIC.TESTRESULTANSWERS_ANSWER(
    TESTRESULTANSWERS_ID BIGINT NOT NULL,
    ANSWERS_ID BIGINT NOT NULL
);           
ALTER TABLE PUBLIC.TESTRESULTANSWERS_ANSWER ADD CONSTRAINT PUBLIC.CONSTRAINT_3D PRIMARY KEY(TESTRESULTANSWERS_ID, ANSWERS_ID);
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.TESTRESULTANSWERS_ANSWER;
CREATE CACHED TABLE PUBLIC.QUESTION(
    ID BIGINT NOT NULL,
    POINTS INTEGER,
    TEXT VARCHAR(255),
    TYPE VARCHAR(255)
);         
ALTER TABLE PUBLIC.QUESTION ADD CONSTRAINT PUBLIC.CONSTRAINT_E PRIMARY KEY(ID);               
-- 12 +/- SELECT COUNT(*) FROM PUBLIC.QUESTION;               
INSERT INTO PUBLIC.QUESTION(ID, POINTS, TEXT, TYPE) VALUES
(8, 2, 'Diese Anwendung wurde programmiert mit', 'SINGLE_CHOICE'),
(12, 5, 'Folgende Technologien wurden eingesetzt', 'MULTIPLE_CHOICE'),
(18, 1, 'Die Hausarbeit muss am [14.11.2016] abgegeben werden', 'FILL_IN_THE_BLANK'),
(20, 4, 'Die Teammitglieder sind [Jens] Gottwald, [Tim] Brust, Hannes [Peterson] und [Yannick] Rump', 'FILL_IN_THE_BLANK'),
(27, 1, STRINGDECODE('VWL steht f\u00fcr'), 'SINGLE_CHOICE'),
(30, 3, STRINGDECODE('Welche Sektoren sind Teil der Aktivit\u00e4tskonten in der VGR?'), 'MULTIPLE_CHOICE'),
(36, 1, 'Das Nettoinlandsprodukt beinhalter nicht die [Abschreibungen]', 'FILL_IN_THE_BLANK'),
(38, 4, 'Teilnehmer im einfachen Wirtschaftskreislauf', 'MULTIPLE_CHOICE'),
(43, 3, STRINGDECODE('Im Jahre [1960] wurde die [Softwaretechnik] aufgrund der [Softwarekrise] eingef\u00fchrt.'), 'FILL_IN_THE_BLANK'),
(47, 2, 'Softwareprodukte', 'SINGLE_CHOICE'),
(51, 5, 'Grundprinzipien der Softwaretechnik', 'MULTIPLE_CHOICE'),
(59, 1, STRINGDECODE('UML steht f\u00fcr'), 'SINGLE_CHOICE');              
CREATE CACHED TABLE PUBLIC.QUESTION_ANSWER(
    QUESTION_ID BIGINT NOT NULL,
    ANSWERS_ID BIGINT NOT NULL
);             
ALTER TABLE PUBLIC.QUESTION_ANSWER ADD CONSTRAINT PUBLIC.CONSTRAINT_3 PRIMARY KEY(QUESTION_ID, ANSWERS_ID);   
-- 40 +/- SELECT COUNT(*) FROM PUBLIC.QUESTION_ANSWER;        
INSERT INTO PUBLIC.QUESTION_ANSWER(QUESTION_ID, ANSWERS_ID) VALUES
(8, 10),
(8, 9),
(8, 11),
(12, 14),
(12, 15),
(12, 17),
(12, 16),
(12, 13),
(18, 19),
(20, 23),
(20, 22),
(20, 24),
(20, 21),
(27, 28),
(27, 29),
(30, 31),
(30, 34),
(30, 32),
(30, 33),
(30, 35),
(36, 37),
(38, 40),
(38, 39),
(38, 41),
(43, 45),
(43, 46),
(43, 44),
(47, 48),
(47, 49),
(47, 50),
(51, 56),
(51, 58),
(51, 52),
(51, 57),
(51, 54),
(51, 53),
(51, 55),
(59, 60),
(59, 62),
(59, 61);        
CREATE CACHED TABLE PUBLIC.STUDENT(
    STUDENTNUMBER INTEGER NOT NULL,
    ID BIGINT NOT NULL
);          
ALTER TABLE PUBLIC.STUDENT ADD CONSTRAINT PUBLIC.CONSTRAINT_BA PRIMARY KEY(ID);               
-- 5 +/- SELECT COUNT(*) FROM PUBLIC.STUDENT; 
INSERT INTO PUBLIC.STUDENT(STUDENTNUMBER, ID) VALUES
(4567, 2),
(9874, 3),
(8484, 4),
(7413, 5),
(6547, 25);             
CREATE CACHED TABLE PUBLIC.STUDENT_EXAM(
    STUDENT_ID BIGINT NOT NULL,
    REGISTEREDEXAMS_ID BIGINT NOT NULL
);         
ALTER TABLE PUBLIC.STUDENT_EXAM ADD CONSTRAINT PUBLIC.CONSTRAINT_8 PRIMARY KEY(STUDENT_ID, REGISTEREDEXAMS_ID);               
-- 12 +/- SELECT COUNT(*) FROM PUBLIC.STUDENT_EXAM;           
INSERT INTO PUBLIC.STUDENT_EXAM(STUDENT_ID, REGISTEREDEXAMS_ID) VALUES
(3, 7),
(4, 7),
(2, 7),
(5, 7),
(4, 26),
(3, 26),
(2, 26),
(5, 26),
(3, 42),
(4, 42),
(2, 42),
(5, 42);    
CREATE CACHED TABLE PUBLIC.STUDENT_EXAM_TOKEN_LIST(
    ID BIGINT NOT NULL,
    TOKEN VARCHAR(255),
    TOKENLIST_KEY BIGINT NOT NULL
);  
ALTER TABLE PUBLIC.STUDENT_EXAM_TOKEN_LIST ADD CONSTRAINT PUBLIC.CONSTRAINT_89 PRIMARY KEY(ID, TOKENLIST_KEY);
-- 12 +/- SELECT COUNT(*) FROM PUBLIC.STUDENT_EXAM_TOKEN_LIST;
INSERT INTO PUBLIC.STUDENT_EXAM_TOKEN_LIST(ID, TOKEN, TOKENLIST_KEY) VALUES
(7, 'memjo7fdabsj2ib7kiv703qtif', 3),
(7, '9qq9bhh9nbb7g5pgbhhkdoii6u', 4),
(7, 'd0hfrcr9qbo2vhv7l3gad88ipl', 2),
(7, 'l5gjj8gvhdk1lo5im4agsa1vnu', 5),
(26, 'a06d6gjmb0uiu2d4e045ef4l9h', 4),
(26, 'utv177l8ie860jmfkcq8hdqhkl', 3),
(26, 'bj083bg90tvlqhjlf82kkk8tnk', 2),
(26, 'o8ir9am94vbb1jsa2r1ergbe9h', 5),
(42, '4ebpuqdabq0v003kb8d4n1vq82', 3),
(42, 'bmbjavhol6hh4mtle68402n7s5', 4),
(42, '247p9k5tmdpvqh65uq4tji1noc', 2),
(42, '780o3kk0do6hr7bksisb64b97p', 5);       
CREATE CACHED TABLE PUBLIC.STUDENT_EXAMRESULT(
    STUDENT_ID BIGINT NOT NULL,
    EXAMRESULTS_ID BIGINT NOT NULL
);       
ALTER TABLE PUBLIC.STUDENT_EXAMRESULT ADD CONSTRAINT PUBLIC.CONSTRAINT_F3 PRIMARY KEY(STUDENT_ID, EXAMRESULTS_ID);            
-- 2 +/- SELECT COUNT(*) FROM PUBLIC.STUDENT_EXAMRESULT;      
INSERT INTO PUBLIC.STUDENT_EXAMRESULT(STUDENT_ID, EXAMRESULTS_ID) VALUES
(2, 100),
(3, 121);
CREATE CACHED TABLE PUBLIC.USER(
    ROLE VARCHAR(31) NOT NULL,
    ID BIGINT NOT NULL,
    ACTIVATED BOOLEAN NOT NULL,
    ACTIVATIONTOKEN VARCHAR(255),
    EMAIL VARCHAR(255),
    FIRSTNAME VARCHAR(255),
    LASTNAME VARCHAR(255),
    PASSWORD VARCHAR(255)
);
ALTER TABLE PUBLIC.USER ADD CONSTRAINT PUBLIC.CONSTRAINT_27 PRIMARY KEY(ID);  
-- 7 +/- SELECT COUNT(*) FROM PUBLIC.USER;    
INSERT INTO PUBLIC.USER(ROLE, ID, ACTIVATED, ACTIVATIONTOKEN, EMAIL, FIRSTNAME, LASTNAME, PASSWORD) VALUES
('lecturer', 1, FALSE, '6mi5lrtgogthv2g24oghoalidl', 'not.activated@nordakademie.de', 'Not', 'Activated', '$31$16$ICTJxckhAK3TwYAzqCOzU2aDftTNXx2oL8v8i-BQJT8'),
('student', 2, TRUE, 'be53dm04j7gr303kmt5uvv9job', 'tim.brust@nordakademie.de', 'Tim', 'Brust', '$31$16$CwEOa4_N-qselqFhp_ZielD4YwFHkafu7AQQQFmHgQM'),
('student', 3, TRUE, '397riu2e6png04e1tsnmbr34gv', 'yannick.rump@nordakademie.de', 'Yannick', 'Rump', '$31$16$r1eavCUm8il3IFqvm-T1xVcSoa19G0KKBsozq7tyCv0'),
('student', 4, TRUE, 'm67iiqiustgsd8t855g4al2h85', 'hannes.peterson@nordakademie.de', 'Hannes', 'Peterson', '$31$16$w6HB_cfuecio8t89ly0gz3nZ9qCnMAcE6zSU6XKmJa8'),
('student', 5, TRUE, 'jih2jjd33pjh9akp0i708qgl65', 'jens.gottwald@nordakademie.de', 'Jens', 'Gottwald', '$31$16$od2jzS4zVpHmAy6jjB_1mK8NinXs__Ike8RNMsuGC6k'),
('lecturer', 6, TRUE, 'ln16e8v2v6mfv3p09t0hc2pnp0', 'benedikt.stemmildt@nordakademie.de', 'Benedikt ', 'Stemmildt', '$31$16$9XxrQRkKCtRWx2G8NCY9isQ_4PbRnFWiM6JdCQpbwYQ'),
('student', 25, TRUE, 't86704g47ttsq6em2cragfrd4e', 'no.exams.assigned@nordakademie.de', 'No-Exam', 'Assigned', '$31$16$55z78CTcIFtpaebnR2sqEgxw0XW8VgBpmIIhQn__vo8');    
CREATE CACHED TABLE PUBLIC.TESTRESULT_QUESTION_POINTS(
    ID BIGINT NOT NULL,
    POINTS INTEGER,
    SUBMITTEDANSWERS_KEY BIGINT NOT NULL
);            
ALTER TABLE PUBLIC.TESTRESULT_QUESTION_POINTS ADD CONSTRAINT PUBLIC.CONSTRAINT_F PRIMARY KEY(ID, SUBMITTEDANSWERS_KEY);       
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.TESTRESULT_QUESTION_POINTS;              
ALTER TABLE PUBLIC.TESTRESULTANSWERS_ANSWER ADD CONSTRAINT PUBLIC.UK_MCLGBVT035INUY09SYDVHG1TW UNIQUE(ANSWERS_ID);            
ALTER TABLE PUBLIC.QUESTION_ANSWER ADD CONSTRAINT PUBLIC.UK_MTD6DWVB7WINAFYB55ULYFY4N UNIQUE(ANSWERS_ID);     
ALTER TABLE PUBLIC.STUDENT ADD CONSTRAINT PUBLIC.UK_B1U9TY19QQIXJJ567Y2CNL91M UNIQUE(STUDENTNUMBER);          
ALTER TABLE PUBLIC.EXAM_QUESTION ADD CONSTRAINT PUBLIC.UK_8HTJ9D13WWW0LG9VXM15XVHQ6 UNIQUE(QUESTIONS_ID);     
ALTER TABLE PUBLIC.TESTRESULT_SUBMITTED_ANSWERS ADD CONSTRAINT PUBLIC.UK_HIMQOV5G45WLLORDG4U51GS9Q UNIQUE(SUBMITTEDANSWERS_ID);               
ALTER TABLE PUBLIC.EXAMRESULTANSWERS_ANSWER ADD CONSTRAINT PUBLIC.UK_NXSE5OXBQLLFEP0MH3VPMKC26 UNIQUE(ANSWERS_ID);            
ALTER TABLE PUBLIC.USER ADD CONSTRAINT PUBLIC.UK_9T4ER00MSTV8BY4KYIK0UONG0 UNIQUE(EMAIL);     
ALTER TABLE PUBLIC.EXAMRESULT_SUBMITTED_ANSWERS ADD CONSTRAINT PUBLIC.UK_BI5RWGG7OSGLMIU4QDB4JN3MK UNIQUE(SUBMITTEDANSWERS_ID);               
ALTER TABLE PUBLIC.LECTURER_EXAM ADD CONSTRAINT PUBLIC.UK_10D47H8MI43BQ5ES3YDMPTI8H UNIQUE(EXAMS_ID);         
ALTER TABLE PUBLIC.STUDENT_TESTRESULT ADD CONSTRAINT PUBLIC.FKO37RDWCDFP2HMK938GY5F4UDI FOREIGN KEY(TESTRESULTS_ID) REFERENCES PUBLIC.TESTRESULT(ID) NOCHECK; 
ALTER TABLE PUBLIC.STUDENT_EXAM_TOKEN_LIST ADD CONSTRAINT PUBLIC.FKLFBYTOQ2O1N1JIFT3IBDAVTP4 FOREIGN KEY(TOKENLIST_KEY) REFERENCES PUBLIC.STUDENT(ID) NOCHECK;
ALTER TABLE PUBLIC.EXAMRESULT_SUBMITTED_ANSWERS ADD CONSTRAINT PUBLIC.FK3RTXWGE0BNI606QIQF57GDP3F FOREIGN KEY(SUBMITTEDANSWERS_ID) REFERENCES PUBLIC.EXAMRESULTANSWERS(ID) NOCHECK;           
ALTER TABLE PUBLIC.STUDENT_EXAM ADD CONSTRAINT PUBLIC.FKMYIX47FP8QXIO74LDJG9DDV3T FOREIGN KEY(REGISTEREDEXAMS_ID) REFERENCES PUBLIC.EXAM(ID) NOCHECK;         
ALTER TABLE PUBLIC.STUDENT_EXAMRESULT ADD CONSTRAINT PUBLIC.FKHXPGLMVFUH3M2M5IYMN25MHRQ FOREIGN KEY(STUDENT_ID) REFERENCES PUBLIC.STUDENT(ID) NOCHECK;        
ALTER TABLE PUBLIC.QUESTION_ANSWER ADD CONSTRAINT PUBLIC.FKDOVDXEGCB1FECECSVGG6GD4SK FOREIGN KEY(QUESTION_ID) REFERENCES PUBLIC.QUESTION(ID) NOCHECK;         
ALTER TABLE PUBLIC.EXAMRESULT_SUBMITTED_ANSWERS ADD CONSTRAINT PUBLIC.FKIGJEKAO6SDC8OFINK60DNX2SY FOREIGN KEY(SUBMITTEDANSWERS_KEY) REFERENCES PUBLIC.QUESTION(ID) NOCHECK;   
ALTER TABLE PUBLIC.LECTURER_EXAM ADD CONSTRAINT PUBLIC.FKQS9RQXF4C49XB2L5JBWE0FBTA FOREIGN KEY(EXAMS_ID) REFERENCES PUBLIC.EXAM(ID) NOCHECK;  
ALTER TABLE PUBLIC.TESTRESULT_SUBMITTED_ANSWERS ADD CONSTRAINT PUBLIC.FKGLC5HFN127S2EYVEGKXFSHSRY FOREIGN KEY(SUBMITTEDANSWERS_ID) REFERENCES PUBLIC.TESTRESULTANSWERS(ID) NOCHECK;           
ALTER TABLE PUBLIC.TESTRESULTANSWERS_ANSWER ADD CONSTRAINT PUBLIC.FKJP6CIOS3VJAAW3EKDIW9SXCG2 FOREIGN KEY(TESTRESULTANSWERS_ID) REFERENCES PUBLIC.TESTRESULTANSWERS(ID) NOCHECK;              
ALTER TABLE PUBLIC.EXAM_QUESTION ADD CONSTRAINT PUBLIC.FK94MSJFCFMIEVYJCUEXLG0DNS6 FOREIGN KEY(EXAM_ID) REFERENCES PUBLIC.EXAM(ID) NOCHECK;   
ALTER TABLE PUBLIC.EXAM_EXAMRESULT ADD CONSTRAINT PUBLIC.FK9OFERD7ARURN5DB9Q6PJ4QSEO FOREIGN KEY(EXAM_ID) REFERENCES PUBLIC.EXAM(ID) NOCHECK; 
ALTER TABLE PUBLIC.TESTRESULT_SUBMITTED_ANSWERS ADD CONSTRAINT PUBLIC.FKFK63TGAI30SI9UCTRIFBJP706 FOREIGN KEY(ID) REFERENCES PUBLIC.TESTRESULT(ID) NOCHECK;   
ALTER TABLE PUBLIC.STUDENT_EXAMRESULT ADD CONSTRAINT PUBLIC.FK5I6T6SDWYUIK2IHMGKPM0JB2B FOREIGN KEY(EXAMRESULTS_ID) REFERENCES PUBLIC.EXAMRESULT(ID) NOCHECK; 
ALTER TABLE PUBLIC.STUDENT_EXAM_TOKEN_LIST ADD CONSTRAINT PUBLIC.FKSOB2M31Y9UE8C8K2D9VV2RUQC FOREIGN KEY(ID) REFERENCES PUBLIC.EXAM(ID) NOCHECK;              
ALTER TABLE PUBLIC.EXAMRESULTANSWERS_ANSWER ADD CONSTRAINT PUBLIC.FKQ5EK91C3G3EEKWTKDL8YMKA9 FOREIGN KEY(EXAMRESULTANSWERS_ID) REFERENCES PUBLIC.EXAMRESULTANSWERS(ID) NOCHECK;               
ALTER TABLE PUBLIC.EXAMRESULTANSWERS_ANSWER ADD CONSTRAINT PUBLIC.FKRXC8VTNXWROXXQJFALK39GBHA FOREIGN KEY(ANSWERS_ID) REFERENCES PUBLIC.ANSWER(ID) NOCHECK;   
ALTER TABLE PUBLIC.STUDENT_EXAM ADD CONSTRAINT PUBLIC.FK1451KQH3YUD5C9U4OAOAS06N9 FOREIGN KEY(STUDENT_ID) REFERENCES PUBLIC.STUDENT(ID) NOCHECK;              
ALTER TABLE PUBLIC.EXAM_TESTRESULT ADD CONSTRAINT PUBLIC.FKM6VP9FG9PMF6RNA6M8JKWUG49 FOREIGN KEY(TESTRESULTS_ID) REFERENCES PUBLIC.TESTRESULT(ID) NOCHECK;    
ALTER TABLE PUBLIC.EXAMRESULT_SUBMITTED_ANSWERS ADD CONSTRAINT PUBLIC.FKL6YDGM1L9V439U5BOLBTU8AC3 FOREIGN KEY(ID) REFERENCES PUBLIC.EXAMRESULT(ID) NOCHECK;   
ALTER TABLE PUBLIC.LECTURER_EXAM ADD CONSTRAINT PUBLIC.FKQG8016ERJ1E1VKHGEN26426JR FOREIGN KEY(LECTURER_ID) REFERENCES PUBLIC.LECTURER(ID) NOCHECK;           
ALTER TABLE PUBLIC.EXAMRESULT ADD CONSTRAINT PUBLIC.FKFIOY5NB85J5HSMTK06GW9D6EI FOREIGN KEY(EXAM_ID) REFERENCES PUBLIC.EXAM(ID) NOCHECK;      
ALTER TABLE PUBLIC.EXAM_EXAMRESULT ADD CONSTRAINT PUBLIC.FKFAQ4IQXWA322X96JQQTEVM5G2 FOREIGN KEY(EXAMRESULTS_ID) REFERENCES PUBLIC.EXAMRESULT(ID) NOCHECK;    
ALTER TABLE PUBLIC.EXAM_QUESTION ADD CONSTRAINT PUBLIC.FK1KT0C7D43LFI3PK05PVC81TRW FOREIGN KEY(QUESTIONS_ID) REFERENCES PUBLIC.QUESTION(ID) NOCHECK;          
ALTER TABLE PUBLIC.STUDENT ADD CONSTRAINT PUBLIC.FKKFQQ4NICKG8WU56AXC7JKTV1 FOREIGN KEY(ID) REFERENCES PUBLIC.USER(ID) NOCHECK;               
ALTER TABLE PUBLIC.EXAMRESULT ADD CONSTRAINT PUBLIC.FK3KL4TUNPIO14R9CLQEO6PA63R FOREIGN KEY(STUDENT_ID) REFERENCES PUBLIC.STUDENT(ID) NOCHECK;
ALTER TABLE PUBLIC.QUESTION_ANSWER ADD CONSTRAINT PUBLIC.FKSIJQDWE52LJ914QX22W8BB5NJ FOREIGN KEY(ANSWERS_ID) REFERENCES PUBLIC.ANSWER(ID) NOCHECK;            
ALTER TABLE PUBLIC.LECTURER ADD CONSTRAINT PUBLIC.FK6R27UA3X4WVPNVV7O572J0PE3 FOREIGN KEY(ID) REFERENCES PUBLIC.USER(ID) NOCHECK;             
