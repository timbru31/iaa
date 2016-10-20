<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title><tiles:insertAttribute name="title"/></title>
  <link href="/iaa-multiple-choice/static/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
<div class="container">
  <tiles:insertAttribute name="header"/>
  <hr/>
  <tiles:insertAttribute name="content"/>
</div>
<script type="application/javascript" src="/iaa-multiple-choice/static/js/jquery-3.1.0.min.js"></script>
<script type="application/javascript" src="/iaa-multiple-choice/static/js/bootstrap.js"></script>
</body>
</html>
