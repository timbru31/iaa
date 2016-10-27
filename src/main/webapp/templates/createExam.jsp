<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<center>
  <h1>
    <s:text name="create.title" />
  </h1>
</center>

<s:form action="createExam">
  <div class="form-group">
    <label for="create.examName"><s:text name="create.examName"/></label>
    <s:textfield name="exam.name" id="create.examName" class="form-control" placeholder="%{getText('create.examName')}" />
  </div>
  <div class="form-group">
    <label for="create.examTime"><s:text name="create.examTime"/></label>
    <s:textfield name="exam.examTime" id="create.examTime" class="form-control" placeholder="%{getText('create.examTime')}" />
  </div>
  <div class="form-group">
    <label for="create.minPoints"><s:text name="create.minPoints"/></label>
    <s:textfield name="exam.minPoints" id="create.minPoints" class="form-control" placeholder="%{getText('create.minPoints')}" />
  </div>
  <div class="form-group">
    <label for="create.examCP"><s:text name="create.examCP"/></label>
    <s:textfield name="exam.creditPoints" id="create.examCP" class="form-control" placeholder="%{getText('create.examCP')}" />
  </div>
  <div class="form-group">
    <label for="create.examPeriod"><s:text name="create.examPeriod"/></label>
    <s:textfield name="exam.examPeriod" id="create.examPeriod" class="form-control" placeholder="%{getText('create.examPeriod')}" />
  </div>

  <center>
    <s:submit key="create.finalSubmit" class="btn btn-primary">
    </s:submit>
  </center>

</s:form>

