<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="page-header">
  <h1>
    <s:text name="login.title" />
  </h1>
</div>
<s:form action="loginUser" validate="true">
  <s:if test="hasFieldErrors()">
    <div class="alert alert-danger" role="alert">
      <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span> <span class="sr-only"><s:text name="validation.error" /></span>
      <strong><s:text name="validation.errorIntro" /></strong>
      <s:fielderror />
    </div>
  </s:if>
  <div class="form-group ${fieldErrors.containsKey('loginFailed') ? 'has-error' : ''}">
    <label class="control-label" for="user.email"><s:text name="user.email" /> <span
      class="label label-warning"><s:text name="user.emailHint" /></span></label>
    <div class="input-group">
      <span class="input-group-addon"> <i class="glyphicon glyphicon-user"></i>
      </span>
      <s:textfield name="email" id="user.email" required="true" requiredLabel="true" type="email"
        pattern="[a-z0-9._%+-]+@nordakademie.de$" class="form-control" placeholder="user@nordakademie.de" />
    </div>
  </div>
  <div class="form-group ${fieldErrors.containsKey('loginFailed') ? 'has-error' : ''}">
    <label class="control-label" for="user.password"><s:text name="user.password" /></label>
    <div class="input-group">
      <span class="input-group-addon"> <i class="glyphicon glyphicon-lock"></i>
      </span>
      <s:textfield name="password" id="user.password" required="true" requiredLabel="true" type="password"
        class="form-control" />
    </div>
  </div>
  <s:submit name="submit" key="login.button" class="btn btn-success" />
</s:form>
