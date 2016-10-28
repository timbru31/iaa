<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:form action="lecturer">
  <s:submit name="back" key="create.back"
    class="btn btn-primary navbar-btn" />
</s:form>
<h1>
  <s:text name="mapping.title" />
</h1>
<br />
<p>
  <s:text name="mapping.map" />
</p>

<br />
<div class="panel panel-default">
  <table class="table table-hover">
    <tr>
      <th><s:text name="create.examName" /></th>
      <th>Student</th>
    </tr>
    <s:iterator value="lecturer.exams">
      <tr>
        <th><s:property value="name" /></th>
        <td>
          <div class="input-group">
            <input type="text" class="form-control"
              placeholder="Student wählen..."> <span
              class="input-group-btn">
              <s:submit class="btn btn-default" type="button" key="mapping.link"></s:submit>
            </span>
          </div>
        </td>
      </tr>
    </s:iterator>
  </table>
</div>

<s:text name="mapping.hint" />