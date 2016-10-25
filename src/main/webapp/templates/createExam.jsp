<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<center><h1><s:text name="create.title"/></h1></center>
<table class="table">
  <tr> <td><b><s:text name="create.examName"/></b></td>
    <td> <s:textfield class="form-control" placeholder="%{getText('create.enterExamName')}"/> </td>
  <tr> <td><b><s:text name="create.examTime"/></b></td>
    <td> <s:textfield class="form-control" placeholder="%{getText('create.enterExamTime')}"/> </td>
  <tr> <td><b><s:text name="create.minPoints"/></b></td>
    <td> <s:textfield class="form-control" placeholder="%{getText('create.enterMinPoints')}"/> </td>
  <tr> <td><b><s:text name="create.examCP"/></b></td>
    <td> <s:textfield class="form-control" placeholder="%{getText('create.enterExamCP')}"/> </td>
  <tr> <td><b><s:text name="create.examPeriod"/></b></td>
    <td> <s:textfield class="form-control" placeholder="%{getText('create.enterExamPeriod')}"/> </td>
</table>
<br/>
<i><s:text name="create.rightanswer" /></i>
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
<br/>

<form>
  <div class="row" style= "border:1px solid black">
    <b><s:text name= "create.Question1" /></b>
     <s:textfield id="Simple Choice" class="form-control" placeholder="%{getText('create.enterQuestion')}" />
        <br />
   <div>
     <input type="Radio" name="answer"/> 
     <input type="text">
    </div>
      <br />
    <input type="Radio" name="answer"/> 
    <input type="text">
      <br />
    <button type="button" class="btn btn-default navbar-btn">
      <s:text name="create.nextAnswer"/>
    </button>
   </div>
</form>
<br/>

<form>
<div class="row" style= "border:1px solid black">
<b><s:text name= "create.Question2" /></b>
<s:textfield id="Simple Choice" class="form-control" placeholder="%{getText('create.enterQuestion')}" />
<br />
<div>
<input type="checkbox" name="answer"/> 
<input>
</div>
<br />
<input type="checkbox" name="answer"/> <input type="text">
<br />
<button type="button" class="btn btn-default navbar-btn">
<s:text name="create.nextAnswer"/>
</button>
</div>
</form>

<br/>

<form>
<div class="row" style= "border:1px solid black">
<b><s:text name= "create.Question3" /></b>
<s:textfield id="Simple Choice" class="form-control" placeholder="%{getText('create.enterGapQuestion')}" />
<br />
</div>
</form>

<button type="button" class="btn btn-default navbar-btn">
<s:text name="create.deleteQuestion"/>
</button>
<nav aria-label="...">
  <ul class="pagination">
    <li class="disabled"><a href="#" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
    <li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li>
  </ul>
</nav>
<center><button type="button" class="btn btn-primary">
<s:text name="create.finalSubmit"/>
</button></center>