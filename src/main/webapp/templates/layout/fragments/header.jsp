<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<h1><s:text name="header.title"/></h1>
<div>
  <s:url var="localeEN" namespace="/" action="locale" >
    <s:param name="lang">en</s:param>
  </s:url>
  <s:url var="localeDE" namespace="/" action="locale" >
    <s:param name="lang">de</s:param>
  </s:url>
  <s:a href="%{localeEN}" >English</s:a>
  <s:a href="%{localeDE}" >German</s:a>
</div>
