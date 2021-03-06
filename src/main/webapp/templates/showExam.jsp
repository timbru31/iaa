<%-- author: Tim Brust --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="page-header">
  <h1>
    <s:text name="show.title" />
  </h1>
</div>
<table class="table">
  <tr>
    <td class="bold"><s:text name="create.examName" /></td>
    <td><s:property value="exam.name" /></td>
  </tr>
  <tr>
    <td class="bold"><s:text name="create.examTime" /></td>
    <td><s:property value="exam.examTime" /></td>
  </tr>
  <tr>
    <td class="bold"><s:text name="create.minPoints" /></td>
    <td><s:property value="exam.minPoints" /></td>
  </tr>
  <tr>
    <td class="bold"><s:text name="create.examCP" /></td>
    <td><s:property value="exam.creditPoints.getValue()" /></td>
  </tr>
  <tr>
    <td class="bold"><s:text name="create.evaluationMethod" /></td>
    <td><s:property value="%{getText(exam.evaluationMethod.getText())}" /></td>
  </tr>
  <tr>
    <td class="bold"><s:text name="create.startDate" /></td>
    <td><s:property value="exam.formatStartDate()" /></td>
  </tr>
  <tr>
    <td class="bold"><s:text name="create.endDate" /></td>
    <td><s:property value="exam.formatEndDate()" /></td>
  </tr>
</table>

<s:if test="%{exam.isDueDated()}">
  <div class="alert alert-warning" role="alert">
    <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span> <span class="sr-only"><s:text name="warning" /></span>
    <strong><s:text name="warning" /></strong>
    <s:text name="show.hint" />
  </div>
</s:if>

<s:if test="%{hasErrors()}">
  <div class="alert alert-danger" role="alert">
    <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span> <span class="sr-only"><s:text name="validation.error" /></span>
    <strong><s:text name="validation.errorIntro" /></strong>
    <s:fielderror />
    <s:actionerror />
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
