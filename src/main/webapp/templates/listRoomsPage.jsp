<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<h2><s:text name="header.listRooms"/></h2>
<table class="table table-hover">
  <tr>
    <th>#</th>
    <th><s:text name="room.building"/></th>
    <th><s:text name="room.number"/></th>
    <th><s:text name="room.seats"/></th>
    <th><s:text name="room.presenterAvailable"/></th>
  </tr>
  <s:iterator value="rooms">
    <tr>
      <th><s:property value="id"/></th>
      <td><s:property value="building"/></td>
      <td><s:property value="number"/></td>
      <td><s:property value="seats"/></td>
      <td><s:property value="presenterAvailable"/></td>
    </tr>
  </s:iterator>
</table>
