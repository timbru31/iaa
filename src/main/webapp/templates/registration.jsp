<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<h4><s:text name="registration.title"/></h4>
<s:form action="registerLecturer" validate="true" id="registrationForm">
  <s:if test="hasFieldErrors()"> 
    <div class="alert alert-danger" role="alert">
      <s:fielderror />
    </div>
   </s:if>
  <div class="form-group ${fieldErrors.containsKey('firstName') ? 'has-error' : ''}">
    <label class="control-label" for="firstName"><s:text name="user.firstName"/></label>
    <s:textfield name="firstName" id="firstName" required="true" requiredLabel="true" class="form-control" placeholder="Max" />
  </div>
  <div class="form-group ${fieldErrors.containsKey('lastName') ? 'has-error' : ''}">
    <label class="control-label" for="lastName"><s:text name="user.lastName"/></label>
    <s:textfield name="lastName" id="lastName" required="true" requiredLabel="true" class="form-control" placeholder="Mustermann" />
  </div>
  <div class="form-group ${fieldErrors.containsKey('email') ? 'has-error' : ''}">
    <label class="control-label" for="email"><s:text name="user.email"/> <span class="label label-warning"><s:text name="user.emailHint"/></span></label>
    <s:textfield name="email" id="email" required="true" requiredLabel="true" class="form-control" pattern="[a-z0-9._%+-]+@nordakademie.de$" placeholder="user@nordakademie.de"/>
  </div>
  <div class="form-group ${fieldErrors.containsKey('password') ? 'has-error' : ''}">
    <label class="control-label" for="password"><s:text name="user.password"/></label>
    <s:textfield name="password" id="password" required="true" requiredLabel="true" type="password" class="form-control" />
  </div>
  <div class="form-group ${fieldErrors.containsKey('password') ? 'has-error' : ''}">
    <label class="control-label" for="passwordRepeat"><s:text name="user.passwordRepeat"/></label>
    <s:textfield name="passwordRepeat" id="passwordRepeat" required="true" requiredLabel="true" type="password" class="form-control" />
  </div>
  <div class="form-group">
    <label class="control-label" for="roles"><s:text name="user.role" /></label>
    <s:set var="roleLecturer" value="getText('user.roleLecturer')"/>
    <s:set var="roleStudent" value="getText('user.roleStudent')"/>
    <s:select class="form-control" id="role" name="role" list="#{'Lecturer': #roleLecturer, 'Student': #roleStudent}" />
  </div>
  <div class="form-group ${fieldErrors.containsKey('studentNumber') ? 'has-error' : ''} collapse ${role == 'Student' ? 'in' : ''}" id="studentNumberForm">
    <label class="control-label" for="studentNumber"><s:text name="user.studentNumber"/></label>
    <s:textfield name="studentNumber" id="studentNumber" type="text" inputmode="numeric" requiredLabel="true" class="form-control" maxlength="4" pattern="^[0-9]{4}$" placeholder="1234"/>
  </div>
  <s:submit key="registration.button" class="btn btn-primary" />
</s:form>

<script type="text/javascript">
$(function() {
  $(document).ready(function() {
    $("#registrationForm").attr("action", "register" + $("#role").find("option:selected").val());
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
