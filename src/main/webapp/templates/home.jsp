<!-- author: Tim Brust
start page -->
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="home-header">
  <div class="nak">
    <a href="nordakademie.de"> <img src="/iaa-multiple-choice/static/img/Nordakademie_Logo.png" width=200px
      height=auto /></a>
  </div>
  <div class="nakgs">
    <a href="nordakademie-gs.de"> <img src="/iaa-multiple-choice/static/img/NA-Dockland-Logo.png" width=300px
      height=auto /></a>
  </div>
  <div>
    <h1 class=center>
      <s:text name="welcome.title" />
    </h1>
  </div>
</div>
<hr class="bigHr" />
<p>
  <s:text name="welcome.general"></s:text>
</p>
<p>
  <s:text name="welcome.info"></s:text>
</p>
