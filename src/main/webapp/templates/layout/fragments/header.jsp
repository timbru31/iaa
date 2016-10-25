<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="/iaa-multiple-choice/"><s:text name="header.title"/></a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Exams <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">Show</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="/iaa-multiple-choice/exam/create">Create (Lecturer Only)</a></li>
          </ul>
        </li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <s:if test="%{#session.email == null}">
          <li>
            <s:form action="login">
              <s:submit key="login.button" class="btn btn-primary navbar-btn" />
            </s:form>
          </li>
          <li>
            <s:form action="registration">
              <s:submit key="registration.button" class="btn btn-primary navbar-btn btn-margin-left" />
            </s:form>
          </li>
        </s:if>
        <s:else>
          <li><p class="navbar-text"><s:text name="header.signedInAs" /> <s:property value="#session.name"/></p></li>
          <li>
            <s:form action="logout">
              <s:submit key="logout.button" class="btn btn-primary navbar-btn" />
            </s:form>
          </li>
        </s:else>
      </ul>
    </div>
  </div>
</nav>
<%-- <div>
  <s:url var="localeEN" namespace="/" action="locale" >
    <s:param name="lang">en</s:param>
  </s:url>
  <s:url var="localeDE" namespace="/" action="locale" >
    <s:param name="lang">de</s:param>
  </s:url>
  <s:a href="%{localeEN}" >English</s:a>
  <s:a href="%{localeDE}" >German</s:a>
</div>
 --%>