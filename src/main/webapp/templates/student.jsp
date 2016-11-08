<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<div class="jumbotron backoffice">
  <h1>
    <s:text name="student.title" />
  </h1>
  <p>
    <s:text name="student.body" />
  </p>
  <p>
    <s:url var="resultList" namespace="/" action="resultList" />
    <s:a class="btn btn-default btn-lg" href="%{resultList}" role="button">
      <span class="glyphicon glyphicon-education glyph-btn"></span>
      <s:text name="resultList.title" />
    </s:a>
  </p>
</div>

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
        <th><s:text name="create.minPointsShort" /></th>
        <th><s:text name="create.evaluationMethodShort" /></th>
        <th><s:text name="create.startDate" /></th>
        <th><s:text name="create.endDate" /></th>
        <th><s:text name="student.status" /></th>
      </tr>
      <s:iterator value="student.registeredExams" status="it">
        <tr>
          <th><s:property value="name" /></th>
          <td><s:property value="creditPoints.getValue()" /></td>
          <td><s:property value="examTime" /></td>
          <td><s:property value="minPoints" /></td>
          <td><s:property value="%{getText(evaluationMethod.getShortText())}" /></td>
          <td><s:property value="formatStartDate()" /></td>
          <td><s:property value="formatEndDate()" /></td>
          <td>
            <s:url var="detailExam" namespace="/" action="showExam">
              <s:param name="examId">${id}</s:param>
            </s:url>
            <s:a class="btn btn-primary" href="%{detailExam}"><s:text name="details"></s:text></s:a>
          </td>
        </tr>
      </s:iterator>
    </table>
  </div>
</s:else>
