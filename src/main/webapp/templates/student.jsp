<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="page-header">
  <h1>
    <s:text name="student.title" />
    <small><s:text name="student.body" /></small>
  </h1>
</div>

<s:if test="%{student.registeredExams.isEmpty()}">
  <div class="panel panel-default">
    <div class="panel-body">
      <s:text name="student.noExams" />
    </div>
  </div>
</s:if>
<s:else>
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
    <s:iterator value="student.registeredExams">
      <tr>
        <th><s:property value="name" /></th>
        <td><s:property value="creditPoints" /></td>
        <td><s:property value="examTime" /></td>
        <td><s:property value="minPoints" /></td>
        <td><s:property value="formatStartDate()" /></td>
        <td><s:property value="formatEndDate()" /></td>
        <td>
          <s:if test="isDueDated()">
            <div class="input-group">
              <s:textfield type="text" class="form-control" placeholder="%{getText('student.enrollPlaceholder')}" />
              <span class="input-group-btn">
                <button class="btn btn-default" type="button"><s:text name="student.enroll" /></button>
              </span>
            </div>
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
  </s:else>
