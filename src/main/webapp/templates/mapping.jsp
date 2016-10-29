<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:form action="lecturer">

  <h1>
    <s:text name="mapping.title" />
  </h1>
  <br />
  <p>
    <s:text name="mapping.map" />
  </p>

  <br />
  <div class="panel panel-default">
    <table class="table table-hover mapping-table">
      <tr>
        <th><s:text name="create.examName" /></th>
        <th>Student</th>
      </tr>
      <s:iterator value="lecturer.exams">
        <tr>
          <th><s:property value="name" /></th>
          <td>
            <div class="input-group">
              <%-- placeholder attribute is invalid, but bootstrap-taginput checks for this attribute when transforming to a input field --%>
              <select multiple id="mapping"  data-role="tagsinput" class="form-control" placeholder="user@nordakademie.de"></select> <span
                class="input-group-btn"> <s:submit class="btn btn-default" type="button" key="mapping.link"></s:submit>
              </span>
            </div>
          </td>
        </tr>
      </s:iterator>
    </table>
  </div>
  <s:text name="mapping.hint" />
  <div>
    <s:submit name="back" key="create.back" class="btn btn-primary navbar-btn" />
  </div>
</s:form>

<script src="https://cdn.jsdelivr.net/bootstrap.tagsinput/0.8.0/bootstrap-tagsinput.min.js"
  integrity="sha256-tQ3x4V2JW+L0ew/P3v2xzL46XDjEWUExFkCDY0Rflqc=" crossorigin="anonymous"></script>
  
<script>
$('#mapping').tagsinput({
  tagClass: 'label label-primary',
  trimValue: true
});
</script>