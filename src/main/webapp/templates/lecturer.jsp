<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="jumbotron backoffice">
  <h1>
    <s:text name="lecturer.title" />
  </h1>
  <p>
    <s:text name="lecturer.subtitle" />
  </p>
  <p>
    <s:url var="createExam" namespace="/" action="createExam" />
    <s:url var="mapping" namespace="/" action="mapping" />
    <s:a class="btn btn-default btn-lg" href="%{createExam}" role="button">
      <span class="glyphicon glyphicon-education"></span>
      <s:text name="lecturer.newExam" />
    </s:a>
    <s:a class="btn btn-default btn-lg" href="%{mapping}" role="button">
      <span class="glyphicon glyphicon-list-alt"></span>
      <s:text name="lecturer.mapStudent" />
    </s:a>
  </p>
</div>

<div class="panel panel-default">
  <table class="table table-hover">
    <tr>
      <th><s:text name="create.examName" /></th>
      <th><s:text name="create.examCP" /></th>
      <th><s:text name="create.examTime" /></th>
      <th><s:text name="create.minPoints" /></th>
      <th><s:text name="create.startDate" /></th>
      <th><s:text name="create.submitDate" /></th>
      <th><span class="glyphicon glyphicon-edit"></span></th>
    </tr>
    <s:iterator value="lecturer.exams">
      <tr>
        <th><s:property value="name" /></th>
        <td><s:property value="creditPoints" /></td>
        <td><s:property value="examTime" /></td>
        <td><s:property value="minPoints" /></td>
        <td><s:property value="startDate" /></td>
        <td><s:property value="finalSubmitDate" /></td>
        <td><s:if test="editable">
            <s:url var="editExam" namespace="/" action="editExam">
              <s:param name="examId">${id}</s:param>
            </s:url>
            <s:a href="%{editExam}" class="btn btn-success">
              <s:text name="yes" />
            </s:a>
          </s:if> <s:else>
            <button class="btn btn-danger disabled ">
              <s:text name="no" />
            </button>
          </s:else></td>
      </tr>
    </s:iterator>
  </table>
</div>
