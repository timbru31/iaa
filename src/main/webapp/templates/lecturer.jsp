<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<h1>
  <s:text name="lecturer.title" />
</h1>
<div>
  <a href="/iaa-multiple-choice/exam/create"> <img
    src="/iaa-multiple-choice/static/img/ExamIcon.png" alt="ExamIcon" /></a>
  <s:text name="lecturer.newExam" />
</div>
<div>
  <a href="/iaa-multiple-choice/lecturer/mapping"> <img
    src="/iaa-multiple-choice/static/img/StudentIcon.png" alt="StudentIcon" /></a>
  <s:text name="lecturer.mapStudent" />
</div>
<p></p>
<h1>
  <s:text name="lecturer.title2" />
</h1>
<div>
  <s:text name="Platzhalter Exam 1"></s:text>
  <p></p>
  <s:text name="Platzhalter Exam 2"></s:text>
  <p></p>
  <s:text name="Platzhalter Exam 3"></s:text>
</div>
<s:text name="lecturer.editableExam" />