<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <tiles:importAttribute name="title" />
    <title><s:text name="%{#attr['title']}" /></title>
    <link rel="icon" type="image/png" sizes="16x16 32x32 96x96 192x192"  href="/iaa-multiple-choice/static/img/favicon.png">
    <!-- CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
      integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
      crossorigin="anonymous">
    <link rel="stylesheet"  href="/iaa-multiple-choice/static/css/iaa-multiple-choice.css">
    <link rel="stylesheet" href="/iaa-multiple-choice/static/css/flags.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/bootstrap.tagsinput/0.8.0/bootstrap-tagsinput.css"
      integrity="sha256-uKEg9s9/RiqVVOIWQ8vq0IIqdJTdnxDMok9XhiqnApU="
      crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/css/bootstrap-datepicker3.min.css"
      integrity="sha256-nFp4rgCvFsMQweFQwabbKfjrBwlaebbLkE29VFR0K40="
      crossorigin="anonymous" />
    <!-- Bootstrap and jQuery -->
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"
      integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
      crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
      integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
      crossorigin="anonymous"></script>
    
  </head>
  <body>
    <div class="container">
      <tiles:insertAttribute name="header" />
      <tiles:insertAttribute name="content" />
      <tiles:insertAttribute name="footer" />
    </div>
  </body>
</html>
