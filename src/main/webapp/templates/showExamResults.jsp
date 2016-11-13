<!-- author: Tim Brust
exam result page -->
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="page-header">
  <h1>
    <s:text name="examResults.title" />
  </h1>
</div>

<s:if test="%{lecturer.exams.isEmpty()}">
  <div class="panel panel-default">
    <div class="panel-body">
      <s:text name="lecturer.noExams" />
    </div>
  </div>
</s:if>
<s:else>
  <div class="panel panel-default">
    <table class="table table-hover">
      <tr>
        <th><s:text name="create.examName" /></th>
        <th><s:text name="create.examCP" /></th>
        <th><s:text name="create.examTime" /></th>
        <th><s:text name="create.minPoints" /></th>
        <th><s:text name="create.evaluationMethodShort" /></th>
        <th><s:text name="create.startDate" /></th>
        <th><s:text name="create.endDate" /></th>
        <th><span class="glyphicon glyphicon-align-justify"></span></th>
        <th><span class="glyphicon glyphicon-edit"></span></th>
        <s:if test="%{mailerDisabled}">
          <th><s:text name="lecturer.tokenList" /></th>
        </s:if>
      </tr>
      <s:iterator value="lecturer.exams">
        <tr>
          <th><s:property value="name" /></th>
          <td><s:property value="creditPoints.getValue()" /></td>
          <td><s:property value="examTime" /></td>
          <td><s:property value="minPoints" /></td>
          <td><s:property
              value="%{getText(evaluationMethod.getShortText())}" /></td>
          <td><s:property value="formatStartDate()" /></td>
          <td><s:property value="formatEndDate()" /></td>






          <td><s:if test="examResults.isEmpty()">
              <button class="btn btn-default" disabled="disabled">
                <span class="glyphicon glyphicon-eye-close"></span>
              </button>
            </s:if> <s:else>
              <s:url var="showExamResults" namespace="/" action="showExamResults">
                <s:param name="examId">${id}</s:param>
              </s:url>
              <s:a href="%{showExamResults}" class="btn btn-primary">
                <span class="glyphicon glyphicon-eye-open"></span>
              </s:a>
            </s:else></td>






          <td><s:if test="isEditable()">
              <s:url var="editExam" namespace="/" action="editExam">
                <s:param name="examId">${id}</s:param>
              </s:url>
              <s:a href="%{editExam}" class="btn btn-success">
                <s:text name="yes" />
              </s:a>
            </s:if> <s:else>
              <button class="btn btn-danger" disabled="disabled">
                <s:text name="no" />
              </button>
            </s:else></td>
          <s:if test="%{mailerDisabled}">
            <td><s:url var="editExam" namespace="/" action="tokenList">
                <s:param name="examId">${id}</s:param>
              </s:url> <s:a href="%{editExam}" class="btn btn-primary">
                <s:text name="lecturer.tokenList" />
              </s:a></td>
          </s:if>
        </tr>
      </s:iterator>
    </table>
  </div>
</s:else>