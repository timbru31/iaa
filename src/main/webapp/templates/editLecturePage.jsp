<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<h2><s:text name="header.editLecture"/></h2>
<s:form>
  <div class="form-group">
    <label for="lecture.beginDate"><s:text name="lecture.beginDate"/></label>
    <s:textfield name="lecture.beginDate" id="lecture.beginDate" requiredLabel="true" class="form-control" placeholder="10.10.2016"/>
  </div>
  <div class="form-group">
    <label for="lecture.endDate"><s:text name="lecture.endDate"/></label>
    <s:textfield name="lecture.endDate" id="lecture.endDate" requiredLabel="true" type="endDate" class="form-control" placeholder="24.12.2016"/>
  </div>
  <div class="form-group">
    <label for="courseNaturalId"><s:text name="courseNaturalId"/></label>
    <s:textfield name="courseNaturalId" id="courseNaturalId" requiredLabel="true" class="form-control" placeholder="I213"/>
  </div>
  <div class="form-group">
    <label for="roomNaturalId"> <s:text name="roomNaturalId"/> </label>
    <s:textfield name="roomNaturalId" id="roomNaturalId" requiredLabel="true" class="form-control" placeholder="A001"/>
  </div>
  <s:submit action="saveLecture" key="button.addLecture" class="btn btn-primary"/>
</s:form>