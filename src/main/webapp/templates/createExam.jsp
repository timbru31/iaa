<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<h1><s:text name="create.title"/></h1>
<table class="table">
<tr> <td><b><s:text name="create.examName"/></b></td>
<td> <s:text name="Platzhalter Exam Name"/> </td>
<tr> <td><b><s:text name="create.examTime"/></b></td>
<td> <s:text name="Platzhalter Exam Time"/> </td>
<tr> <td><b><s:text name="create.minPoints"/></b></td>
<td> <s:text name="Platzhalter Minimal Points"/> </td>
<tr> <td><b><s:text name="create.examCP"/></b></td>
<td> <s:text name="Platzhalter CP"/> </td>
<tr> <td><b><s:text name="create.examPeriod"/></b></td>
<td> <s:text name="Platzhalter Exam Period"/> </td>
</table>
<br/>
<div class="btn-group btn-group-justified" role="group" aria-label="...">
  <div class="btn-group" role="group">
    <button type="button" class="btn btn-default"><s:text name= "create.Question1" /></button>
  </div>
  <div class="btn-group" role="group">
    <button type="button" class="btn btn-default"><s:text name= "create.Question2" /></button>
  </div>
  <div class="btn-group" role="group">
    <button type="button" class="btn btn-default"><s:text name= "create.Question3" /></button>
  </div>
</div>
<div>
<s:text name="Je nachdem, welcher Fragetyp ausgewählt wurde, erscheint hier eine Fragevorlage" />
<br/>
<br/>
<s:text name="create.rightanswer" />
<br/>
<s:textarea label="Comments" name="comments" cols="50" rows="3"/>
</div>
<nav aria-label="...">
  <ul class="pagination">
    <li class="disabled"><a href="#" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
    <li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li>
  </ul>
</nav>