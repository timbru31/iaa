<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<h2><s:text name="header.listCourses"/></h2>
<table class="table table-hover">
  <tr>
    <th>#</th>
    <th><s:text name="course.fieldOfStudy"/></th>
    <th><s:text name="course.number"/></th>
    <th><s:text name="course.lecturer"/></th>
    <th><s:text name="course.title"/></th>
  </tr>
  <s:iterator value="courses">
    <tr>
      <th><s:property value="id"/></th>
      <td><s:property value="fieldOfStudy"/></td>
      <td><s:property value="number"/></td>
      <td><s:property value="lecturer"/></td>
      <td><s:property value="title"/></td>
    </tr>
  </s:iterator>
</table>