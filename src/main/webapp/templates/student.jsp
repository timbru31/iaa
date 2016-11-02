<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="page-header">
  <h1>
    <s:text name="student.title" />
    <small><s:text name="student.body" /></small>
  </h1>
</div>

<s:if test="%{hasActionErrors()}">
  <div class="alert alert-danger" role="alert">
    <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span> <span class="sr-only"><s:text name="validation.error" /></span>
    <strong><s:text name="validation.errorIntro" /></strong>
    <s:actionerror />
  </div>
</s:if>

<s:if test="%{student.registeredExams.isEmpty()}">
  <div class="panel panel-default">
    <div class="panel-body">
      <s:text name="student.noExams" />
    </div>
  </div>
</s:if>
<s:else>
  <div class="table-responsive">
    <table class="table table-hover">
      <tr>
        <th><s:text name="create.examName" /></th>
        <th><s:text name="create.examCP" /></th>
        <th><s:text name="create.examTime" /></th>
        <th><s:text name="create.minPoints" /></th>
        <th><s:text name="create.startDate" /></th>
        <th><s:text name="create.endDate" /></th>
        <th><s:text name="student.status" /></th>
      </tr>
      <s:iterator value="student.registeredExams" status="it">
        <tr>
          <th><s:property value="name" /></th>
          <td><s:property value="creditPoints" /></td>
          <td><s:property value="examTime" /></td>
          <td><s:property value="minPoints" /></td>
          <td><s:property value="formatStartDate()" /></td>
          <td><s:property value="formatEndDate()" /></td>
          <td>
            <s:if test="isDueDated()">
              <s:form action="enroll" id="exam-%{#it.index}">
                <s:if test="%{hasFieldErrors() && id == examId}">
                  <div class="alert alert-danger" role="alert">
                    <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span> <span class="sr-only"><s:text name="validation.error" /></span>
                    <strong><s:text name="validation.errorIntro" /></strong>
                    <s:fielderror />
                  </div>
                </s:if>
                <s:hidden name="examId" value="%{id}" />
                <div class="input-group">
                  <s:textfield name="token" type="text" class="form-control" placeholder="%{getText('student.enrollPlaceholder')}" required />
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
          </td>
        </tr>
      </s:iterator>
    </table>
  </div>
</s:else>
