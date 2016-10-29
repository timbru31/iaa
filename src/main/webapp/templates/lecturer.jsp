<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<h1>
  <s:text name="lecturer.title" />
</h1>
<div>
  <a href="/iaa-multiple-choice/createExam"> <img
    src="/iaa-multiple-choice/static/img/ExamIcon.png" alt="ExamIcon" /></a>
  <s:text name="lecturer.newExam" />
</div>
<div>
  <a href="/iaa-multiple-choice/lecturer/mapping"> <img
    src="/iaa-multiple-choice/static/img/StudentIcon.png" alt="StudentIcon" /></a>
  <s:text name="lecturer.mapStudent" />
</div>
<p></p>
<h1>
  <s:text name="lecturer.title2" />
</h1>
<table class="table table-hover">
  <tr>
    <th><s:text name="create.examName"/></th>
    <th><s:text name="create.examCP"/></th>
    <th><s:text name="create.examTime"/></th>
    <th><s:text name="create.minPoints"/></th>
    <th><s:text name="create.startDate"/></th>
    <th><s:text name="create.submitDate"/></th>
    <th><s:text name="lecturer.editable"/></th>
  </tr>
  <s:iterator value="lecturer.exams">
    <tr>
      <th><s:property value="name"/></th>
      <td><s:property value="creditPoints"/></td>
      <td><s:property value="examTime"/></td>
      <td><s:property value="minPoints"/></td>
      <td><s:property value="startDate"/></td>
      <td><s:property value="finalSubmitDate"/></td>
      <td>
        <s:if test="editable">
          <button class="btn btn-success"><s:text name="yes" /></button>
        </s:if>
        <s:else>
          <button class="btn btn-danger disabled "><s:text name="no" /></button>
        </s:else>
      </td>
    </tr>
  </s:iterator>
</table>
<s:text name="lecturer.editableExam" />
