<!-- author: Tim Brust
exam result page -->
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="page-header">
  <h1>
    <s:text name="examResults.title" />
  </h1>
</div>

<div class="panel panel-default">
  <table class="table table-hover">
    <tr>
      <th><s:text name="user.roleStudent" /></th>
      <th><s:text name="user.email" /></th>
      <th><s:text name="examResult.title" /></th>
    </tr>
    <s:iterator value="exam.examResults">
      <tr>
        <th><s:property value="student.firstName" /> <s:property
            value="student.lastName" /></th>
        <td><s:property value="student.email" /></td>
        <td><s:if test="passed">
            <button class="btn btn-success btn-result">
              <s:text name="resultList.passed" />
            </button>
          </s:if> <s:else>
            <button class="btn btn-danger btn-result">
              <s:text name="resultList.failed" />
            </button>
          </s:else></td>
      </tr>
    </s:iterator>
  </table>
</div>

<s:url var="back" namespace="/" action="lecturer" />
<s:a href="%{back}" class="btn btn-primary navbar-btn">
  <s:text name="create.back" />
</s:a>