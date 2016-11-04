<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="page-header">
  <h1>
    <s:text name="show.title" />
  </h1>
</div>
<table class="table">
  <tr>
    <td><b><s:text name="create.examName" /></b></td>
    <td><s:property value="exam.name" /></td>
  </tr>
  <tr>
    <td><b><s:text name="create.examTime" /></b></td>
    <td><s:property value="exam.examTime" /></td>
  </tr>
  <tr>
    <td><b><s:text name="create.minPoints" /></b></td>
    <td><s:property value="exam.minPoints" /></td>
  </tr>
  <tr>
    <td><b><s:text name="create.examCP" /></b></td>
    <td><s:property value="exam.creditPoints.getValue()" /></td>
  </tr>
  <tr>
    <td><b><s:text name="create.evaluationMethod" /></b></td>
    <td><s:property value="%{getText(exam.evaluationMethod.getText())}" /></td>
  </tr>
  <tr>
    <td><b><s:text name="create.startDate" /></b></td>
    <td><s:property value="exam.formatStartDate()" /></td>
  </tr>
  <tr>
    <td><b><s:text name="create.endDate" /></b></td>
    <td><s:property value="exam.formatEndDate()" /></td>
  </tr>
</table>

<s:if test="%{hasFieldErrors()}">
  <div class="alert alert-danger" role="alert">
    <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span> <span class="sr-only"><s:text name="validation.error" /></span>
    <strong><s:text name="validation.errorIntro" /></strong>
    <s:fielderror />
  </div>
</s:if>

<div class="center">
  <s:if test="%{exam.isDueDated()}">
    <s:form class="enroll-form" action="enroll">
      <s:hidden name="examId" value="%{exam.id}" />
      <div class="input-group">
        <s:textfield name="token" type="text" class="form-control" placeholder="%{getText('student.enrollPlaceholder')}" required="true" />
        <span class="input-group-btn">
          <s:submit class="btn btn-default" type="button"><s:text name="student.enroll" /></s:submit>
        </span>
      </div>
    </s:form>
  </s:if>
  <s:else>
    <button class="btn btn-danger disabled ">
      <s:text name="student.enrollForbidden" />
    </button>
  </s:else>
</div>