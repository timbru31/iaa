<%-- author: Yannick Rump --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="page-header">
  <h1>
    <s:text name="tokenList.title">
      <s:param>${exam.name}</s:param>
    </s:text>
  </h1>
</div>
<s:if test="%{exam.tokenList.isEmpty()}">
  <div class="panel panel-default">
    <div class="panel-body">
      <s:text name="tokenList.noStudents" />
    </div>
  </div>
</s:if>
<s:else>
  <div class="table-responsive">
    <table class="table table-hover">
      <tr>
        <th><s:text name="user.name" /></th>
        <th><s:text name="tokenList.token" /></th>
      </tr>
      <s:iterator value="exam.tokenList">
        <tr>
          <td class="bold"><s:text name="key.getFullName()" /></td>
          <td><s:property value="value" /></td>
        </tr>
      </s:iterator>
    </table>
  </div>
</s:else>
<s:url var="back" namespace="/" action="lecturer" />
<s:a href="%{back}" class="btn btn-primary navbar-btn">
  <s:text name="create.back" />
</s:a>
