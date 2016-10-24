<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<h4><s:text name="registration.title"/></h4>
<s:form validate="true" >
  <s:if test="hasFieldErrors()"> 
    <div class="alert alert-danger" role="alert">
      <s:fielderror />
    </div>
   </s:if>
  <div class="form-group ${fieldErrors.containsKey('firstName') ? 'has-error' : ''}">
    <label class="control-label" for="firstName"><s:text name="user.firstName"/></label>
    <s:textfield name="lecturer.firstName" id="firstName" required="true" requiredLabel="true" class="form-control" placeholder="Max" />
  </div>
  <div class="form-group ${fieldErrors.containsKey('lastName') ? 'has-error' : ''}">
    <label class="control-label" for="lastName"><s:text name="user.lastName"/></label>
    <s:textfield name="lecturer.lastName" id="lastName" required="true" requiredLabel="true" class="form-control" placeholder="Mustermann" />
  </div>
  <div class="form-group ${fieldErrors.containsKey('email') ? 'has-error' : ''}">
    <label class="control-label" for="email"><s:text name="user.email"/> <span class="label label-info"><s:text name="user.emailHint"/></span></label>
    <s:textfield name="lecturer.email" id="email" required="true" requiredLabel="true" class="form-control" pattern="[a-z0-9._%+-]+@nordakademie.de$" placeholder="user@nordakademie.de"/>
  </div>
  <div class="form-group ${fieldErrors.containsKey('password') ? 'has-error' : ''}">
    <label class="control-label" for="password"><s:text name="user.password"/></label>
    <s:textfield name="lecturer.password" id="password" required="true" requiredLabel="true" type="password" class="form-control" />
  </div>
  <div class="form-group">
    <label class="control-label" for="role"><s:text name="user.role" /></label>
    <select class="form-control" id="role">
      <option><s:text name="user.roleLecturer" /></option>
      <option><s:text name="user.roleStudent" /></option>
    </select>
  </div>
  <div class="collapse form-group ${fieldErrors.containsKey('studentNumber') ? 'has-error' : ''}" id="studentNumberForm">
    <label class="control-label" for="studentNumber"><s:text name="user.studentNumber"/></label>
    <s:textfield name="student.studentNumber" id="studentNumber" type="text" inputmode="numeric" requiredLabel="true" class="form-control" maxlength="4" pattern="^[0-9]{4}$" placeholder="1234"/>
  </div>
  <s:submit action="registerLecturer" key="registration.button" class="btn btn-primary" />
</s:form>

<script type="text/javascript">
$(function() {
  $("#role").on("change", function() {
    var selected = $(this).find("option:selected").val();
    if (selected === 'Student') {
      $("#studentNumberForm").collapse("show");
      $('#studentNumber').attr("required", true);
      $("input").each(function(index) {
        var name = $(this).attr('name');
        if (name) {
          if (name.indexOf("lecturer") !== -1) {
            $(this).attr('name', name.replace("lecturer", "student"));
          } else if (name.indexOf("action:register") !== -1) {
            $(this).attr('name', name.replace("Lecturer", "Student"));
          }
        }
      });
    } else {
      $("#studentNumberForm").collapse("hide");
      $('#studentNumber').attr("required", false);
      $("input").each(function(index) {
        var name = $(this).attr("name");
        if (name) {
          if (name.indexOf("student") !== -1) {
            $(this).attr("name", name.replace("student", "lecturer"));
          } else if (name.indexOf("action:register") !== -1) {
            $(this).attr("name", name.replace("Student", "Lecturer"));
          }
        }
      });
    }
  });
});
</script>
