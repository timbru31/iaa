<%-- author: Tim Brust --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<div class="jumbotron welcome">
  <p>
    <s:a class="btn btn-default btn-lg nak-btn"
      href="https://www.nordakademie.de" role="button">
      Nordakademie Hochschule
    </s:a>
    <s:a class="btn btn-default btn-lg nak-btn"
      href="https://www.nordakademie-gs.de" role="button">
      Nordakademie Graduate School
    </s:a>
  </p>
  <h1>
    <s:text name="welcome.title" />
  </h1>
</div>

<hr />
<center>
  <div class="panel panel-default">
    <div class="panel-body">
      <s:text name="welcome.subtitle" />
    </div>
  </div>
</center>
