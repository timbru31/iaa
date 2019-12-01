<%-- author: Yannick Rump --%> <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ taglib
uri="/struts-tags" prefix="s"%> <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
		<tiles:importAttribute name="title" />
		<title><s:text name="%{#attr['title']}" /></title>
		<link rel="icon" type="image/png" sizes="16x16 32x32 96x96 192x192" href="/iaa-multiple-choice/static/img/favicon.png" />
		<%-- CSS --%>
		<link
			rel="stylesheet"
			href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
			integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
			crossorigin="anonymous"
		/>
		<link rel="stylesheet" href="/iaa-multiple-choice/static/css/iaa-multiple-choice.css" />
		<link rel="stylesheet" href="/iaa-multiple-choice/static/css/flags.css" />
		<link
			rel="stylesheet"
			href="/iaa-multiple-choice/static/css/bootstrap-tagsinput.css"
		/>
		<link
			rel="stylesheet"
			href="/iaa-multiple-choice/static/css/bootstrap-datepicker.min.css"
		/>
		<%-- Bootstrap and jQuery --%>
		<script
      type="text/javascript" src="/iaa-multiple-choice/static/js/jquery-3.4.1.min.js"
		></script>
		<script
			src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
			integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
			crossorigin="anonymous"
		></script>
	</head>
	<body>
		<div class="container">
			<tiles:insertAttribute name="header" />
			<tiles:insertAttribute name="content" />
			<tiles:insertAttribute name="footer" />
		</div>
	</body>
</html>
