<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="page-header">
  <h1>
    <s:text name="create.title" />
  </h1>
</div>

<s:form action="saveExam">
  <div class="form-group">
    <label for="create.examName"><s:text name="create.examName"/></label>
    <s:textfield name="exam.name" id="create.examName" type="text" required="true" class="form-control" placeholder="%{getText('create.examName')}" />
  </div>
  <div class="form-group">
    <label for="create.examTime"><s:text name="create.examTime"/></label>
    <s:textfield name="exam.examTime" id="create.examTime" type="number" required="true" class="form-control" placeholder="%{getText('create.examTime')}" />
  </div>
  <div class="form-group">
    <label for="create.minPoints"><s:text name="create.minPoints"/></label>
    <s:textfield name="exam.minPoints" id="create.minPoints" type="number" required="true" class="form-control" placeholder="%{getText('create.minPoints')}" />
  </div>
  <div class="form-group">
    <label for="create.examCP"><s:text name="create.examCP"/></label>
    <s:textfield name="exam.creditPoints" id="create.examCP" type="number" step="0.5" required="true" class="form-control" placeholder="%{getText('create.examCP')}" />
  </div>
  <div class="form-group">
    <label for="create.examPeriod"><s:text name="create.examPeriod"/></label>
    <s:textfield name="exam.startDate" id="create.examPeriod" required="true" class="form-control" pattern="^(31|30|0[1-9]|[12][0-9]|[1-9])\.(0[1-9]|1[012]|[1-9])\.((18|19|20)\d{2}|\d{2})$" placeholder="%{getText('create.startDate')}" />
    <s:textfield name="exam.finalSubmitDate" id="create.examPeriod" required="true" class="form-control" pattern="^(31|30|0[1-9]|[12][0-9]|[1-9])\.(0[1-9]|1[012]|[1-9])\.((18|19|20)\d{2}|\d{2})$" placeholder="%{getText('create.submitDate')}" />
  </div>
  <center>
    <s:submit key="create.finalSubmit" class="btn btn-primary">
    </s:submit>
  </center>
</s:form>
