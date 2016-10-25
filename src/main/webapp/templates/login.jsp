<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<h4><s:text name="login.title"/></h4>
<s:form validate="true">
  <s:if test="hasFieldErrors()"> 
    <div class="alert alert-danger" role="alert">
      <s:fielderror />
    </div>
   </s:if>
  <div class="form-group ${fieldErrors.containsKey('email') ? 'has-error' : ''}">
    <label class="control-label" for="user.email"><s:text name="user.email" /> <span class="label label-info"><s:text name="user.emailHint"/></span></label>
    <s:textfield name="user.email" id="user.email" required="true" requiredLabel="true" type="email" pattern="[a-z0-9._%+-]+@nordakademie.de$" class="form-control" placeholder="user@nordakademie.de" />
  </div>
  <div class="form-group ${fieldErrors.containsKey('password') ? 'has-error' : ''}">
    <label class="control-label" for="user.password"><s:text name="user.password" /></label>
    <s:textfield name="user.password" id="user.password" required="true" requiredLabel="true" type="password" class="form-control" />
  </div>
  <s:submit action="loginUser" key="login.button" class="btn btn-primary" />
</s:form>
