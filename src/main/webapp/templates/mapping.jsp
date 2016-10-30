<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="page-header">
  <h1>
    <s:text name="mapping.title" />
    <small><s:text name="mapping.subtitle" /></small>
  </h1>
</div>
<div class="panel panel-default">
  <table class="table table-hover mapping-table">
    <tr>
      <th><s:text name="create.examName" /></th>
      <th><s:text name="mapping.students" /></th>
    </tr>
    <s:iterator value="lecturer.exams">
      <tr>
        <th><s:property value="name" /></th>
        <td>
        <s:form action="examMappingAction">
          <s:fielderror></s:fielderror>
          <s:hidden name="examId" value="%{id}"/>
          <div class="input-group">
            <%-- placeholder attribute is invalid, but bootstrap-taginput checks for this attribute when transforming to a input field --%>
            <select name="studentEmails" multiple data-role="tagsinput" class="mapping form-control"
                placeholder="user@nordakademie.de"></select> <span class="input-group-btn"> <s:submit
                class="btn btn-default" type="button" key="mapping.link"></s:submit>
              </span>
            </div>
          </s:form>
        </td>
      </tr>
    </s:iterator>
  </table>
</div>
<s:text name="mapping.hint" />
<div>
  <s:a action="lecturer" class="btn btn-primary navbar-btn"><s:text name="create.back" /></s:a>
</div>

<script src="https://cdn.jsdelivr.net/bootstrap.tagsinput/0.8.0/bootstrap-tagsinput.min.js"
  integrity="sha256-tQ3x4V2JW+L0ew/P3v2xzL46XDjEWUExFkCDY0Rflqc=" crossorigin="anonymous"></script>

<script>
$('.mapping').tagsinput({
  tagClass: 'label label-primary',
  trimValue: true
});
</script>
