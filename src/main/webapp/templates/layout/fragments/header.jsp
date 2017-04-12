<%-- author: Yannick Rump --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<s:url var="localeEN" namespace="/" action="locale">
  <s:param name="lang">en</s:param>
</s:url>
<s:url var="localeDE" namespace="/" action="locale">
  <s:param name="lang">de</s:param>
</s:url>
<s:url var="home" namespace="/" action="home" />

<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
        data-target="#navbar-collapse" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span
          class="icon-bar"></span>
      </button>
      <s:a class="navbar-brand" href="%{home}"> <span class="glyphicon glyphicon-home"
        aria-hidden="true"></span> <s:text name="header.title" />
      </s:a>
    </div>

    <%-- Collect the nav links, forms, and other content for toggling --%>
    <div class="collapse navbar-collapse" id="navbar-collapse">
      <ul class="nav navbar-nav navbar-right">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
            aria-expanded="false"><s:text name="header.changeLanguage" /><span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li>
              <s:a href="%{localeDE}">
                <img src="data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7"
                  class="flag flag-de" alt="<s:text name='header.german' />">
                <s:text name="header.german" />
              </s:a>
            </li>
            <li>
              <s:a href="%{localeEN}">
                <img src="data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7"
                  class="flag flag-us" alt="<s:text name='header.english' />">
                <s:text name="header.english" />
              </s:a>
            </li>
          </ul>
        </li>
        <s:if test="%{#session.userEmail == null}">
          <li>
            <s:form action="login">
              <s:submit class="btn btn-primary navbar-btn btn-margin-left" value="%{getText('login.button')}" />
            </s:form>
          </li>
          <s:if test="%{registrationEnabled}">
            <li>
              <s:form action="registration">
                <s:submit class="btn btn-primary navbar-btn btn-margin-left" value="%{getText('registration.button')}" />
              </s:form>
            </li>
          </s:if>
        </s:if>
        <s:else>
          <li>
            <p class="navbar-text">
              <s:text name="header.signedInAs" />
              <s:property value="#session.userName" />
            </p>
          </li>
          <li>
            <s:form action="logout">
              <s:submit class="btn btn-primary navbar-btn" value="%{getText('logout.button')}" />
            </s:form>
          </li>
        </s:else>
      </ul>
    </div>
  </div>
</nav>
