<%-- author: Tim Brust --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="jumbotron">
  <h2>
    <s:text name="activationSuccess.headline" />
  </h2>
  <p>
    <s:text name="activationSuccess.text" />
  </p>
  <p>
  <s:url var="home" namespace="/" action="home" />
    <s:a class="btn btn-default btn-lg" href="%{home}" role="button">
      <span class="glyphicon glyphicon-home glyph-btn"></span>
      <s:text name="home.goTo" />
    </s:a>
  </p>
</div>
