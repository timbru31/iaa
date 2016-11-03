<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="page-header">
  <h1>
    <s:text name="registration.title" />
  </h1>
</div>
<s:form action="registerLecturer" id="registrationForm">
  <s:if test="hasErrors()">
    <div class="alert alert-danger" role="alert">
      <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span> <span class="sr-only"><s:text name="validation.error" /></span>
      <strong><s:text name="validation.errorIntro" /></strong>
      <s:fielderror />
      <s:actionerror />
    </div>
  </s:if>
  <div class="form-group ${fieldErrors.containsKey('firstName') ? 'has-error' : ''}">
    <label class="control-label" for="firstName"><s:text name="user.firstName" /></label>
    <s:textfield name="firstName" id="firstName" required="true" requiredLabel="true" class="form-control"
      placeholder="Max" />
  </div>
  <div class="form-group ${fieldErrors.containsKey('lastName') ? 'has-error' : ''}">
    <label class="control-label" for="lastName"><s:text name="user.lastName" /></label>
    <s:textfield name="lastName" id="lastName" required="true" requiredLabel="true" class="form-control"
      placeholder="Mustermann" />
  </div>
  <div class="form-group ${fieldErrors.containsKey('email') ? 'has-error' : ''}">
    <label class="control-label" for="email"><s:text name="user.email" /> <span class="label label-warning"><s:text
          name="user.emailHint" /></span></label>
    <s:textfield name="email" id="email" required="true" requiredLabel="true" class="form-control"
      pattern="[a-z0-9._%+-]+@nordakademie.de$" placeholder="user@nordakademie.de" />
  </div>
  <div class="form-group ${fieldErrors.containsKey('password') ? 'has-error' : ''}">
    <label class="control-label" for="password"><s:text name="user.password" /></label>
    <s:textfield name="password" id="password" required="true" requiredLabel="true" type="password" class="form-control" />
  </div>
  <div class="form-group ${fieldErrors.containsKey('password') ? 'has-error' : ''}">
    <label class="control-label" for="passwordRepeat"><s:text name="user.passwordRepeat" /></label>
    <s:textfield name="passwordRepeat" id="passwordRepeat" required="true" requiredLabel="true" type="password"
      class="form-control" />
  </div>
  <div class="form-group">
    <label class="control-label" for="roles"><s:text name="user.role" /></label>
    <s:set var="roleLecturer" value="getText('user.roleLecturer')" />
    <s:set var="roleStudent" value="getText('user.roleStudent')" />
    <s:select class="form-control" name="role" id="role" list="#{'Lecturer': #roleLecturer, 'Student': #roleStudent}" />
  </div>
  <div
    class="form-group ${fieldErrors.containsKey('studentNumber') ? 'has-error' : ''} collapse ${role == 'Student' ? 'in' : ''}"
    id="studentNumberForm">
    <label class="control-label" for="studentNumber"><s:text name="user.studentNumber" /></label>
    <s:textfield name="studentNumber" id="studentNumber" type="number" inputmode="numeric" requiredLabel="true"
      class="form-control" min="1" max="9999" pattern="[0-9]*" placeholder="1234" />
  </div>
  <s:submit class="btn btn-success" value="%{getText('registration.button')}" />
</s:form>

<script type="text/javascript">
  $(function() {
    $(document).ready(
        function() {
          $("#registrationForm").attr("action",
              "register" + $("#role").find("option:selected").val());
        });
    $("#role").on("change", function() {
      var selected = $(this).find("option:selected").val();
      if (selected === 'Student') {
        $("#studentNumberForm").collapse("show");
        $("#studentNumber").attr("required", true);
        $("#registrationForm").attr("action", "registerStudent");
      } else {
        $("#studentNumberForm").collapse("hide");
        $("#studentNumber").attr("required", false);
        $("#registrationForm").attr("action", "registerLecturer");
      }
    });
  });
</script>
