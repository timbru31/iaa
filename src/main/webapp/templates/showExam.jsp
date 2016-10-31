<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<center>
  <h1>
    <s:text name="show.title" />
  </h1>
</center>
<table class="table">
  <tr>
    <td><b><s:text name="create.examName" /></b></td>
    <td><s:text name="Platzhalter Exam Name" /></td>
  <tr>
    <td><b><s:text name="create.examTime" /></b></td>
    <td><s:text name="Platzhalter Exam Time" /></td>
  <tr>
    <td><b><s:text name="create.minPoints" /></b></td>
    <td><s:text name="Platzhalter Minimal Points" /></td>
  <tr>
    <td><b><s:text name="create.examCP" /></b></td>
    <td><s:text name="Platzhalter CP" /></td>
  <tr>
    <td><b><s:text name="create.examPeriod" /></b></td>
    <td><s:text name="Platzhalter Exam Period" /></td>
</table>
<br />
<button type="button" class="btn btn-default navbar-btn">
  <s:text name="show.nextQuestion" />
</button>