<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<h2><s:text name="header.listLectures"/></h2>
<table class="table table-hover">
  <tr>
    <th>#</th>
    <th><s:text name="lecture.beginDate"/></th>
    <th><s:text name="lecture.endDate"/></th>
    <th><s:text name="course.id"/></th>
    <th><s:text name="room.id"/></th>
  </tr>
  <s:iterator value="lectures">
    <tr>
      <th><s:property value="id"/></th>
      <td><s:property value="beginDate"/></td>
      <td><s:property value="endDate"/></td>
      <td><s:property value="course.id"/></td>
      <td><s:property value="room.id"/></td>
    </tr>
  </s:iterator>
</table>