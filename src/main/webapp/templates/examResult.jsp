<%-- author: Tim Brust --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="page-header">
  <h1>
    <s:text name="examResult.title" />
  </h1>
</div>
<table class="table">
  <tr>
    <td><b><s:text name="create.examName" /></b></td>
    <td><s:property value="examResult.exam.name" /></td>
  </tr>
  <tr>
    <td><b><s:text name="create.examTime" /></b></td>
    <td><s:property value="examResult.exam.examTime" /></td>
  </tr>
  <tr>
    <td><b><s:text name="create.minPoints" /></b></td>
    <td><s:property value="examResult.exam.minPoints" /></td>
  </tr>
  <tr>
    <td><b><s:text name="create.examCP" /></b></td>
    <td><s:property value="examResult.exam.creditPoints.getValue()" /></td>
  </tr>
  <tr>
    <td><b><s:text name="create.evaluationMethod" /></b></td>
    <td><s:property value="%{getText(examResult.exam.evaluationMethod.getText())}" /></td>
  </tr>
  <tr>
    <td><b><s:text name="create.startDate" /></b></td>
    <td><s:property value="examResult.exam.formatStartDate()" /></td>
  </tr>
  <tr>
    <td><b><s:text name="create.endDate" /></b></td>
    <td><s:property value="examResult.exam.formatEndDate()" /></td>
  </tr>
  <tr>
    <td><b><s:text name="points" /></b></td>
    <td><s:property value="examResult.points" />/<s:property value="examResult.exam.getMaxPoints()" /></td>
  </tr>
  <tr>
    <td><b><s:text name="resultList.result" /></b></td>
    <td>
      <s:if test="examResult.passed">
        <button class="btn btn-success btn-result"><s:text name="resultList.passed" /></button>
      </s:if>
      <s:else>
        <button class="btn btn-danger btn-result"><s:text name="resultList.failed" /></button>
      </s:else>
    </td>
  </tr>
</table>
<s:url var="back" namespace="/" action="resultList" />
<s:a href="%{back}" class="btn btn-primary navbar-btn">
  <s:text name="create.back" />
</s:a>
