<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<h2><s:text name="header.editCourse"/></h2>
<s:form>
  <div class="form-group">
    <label for="course.fieldOfStudy"><s:text name="course.fieldOfStudy"/></label>
    <s:textfield name="course.fieldOfStudy" id="course.fieldOfStudy" requiredLabel="true" class="form-control" placeholder="I"/>
  </div>
  <div class="form-group">
    <label for="course.number"><s:text name="course.number"/></label>
    <s:textfield name="course.number" id="course.number" requiredLabel="true" type="number" class="form-control" placeholder="213"/>
  </div>
  <div class="form-group">
    <label for="course.lecturer"><s:text name="course.lecturer"/></label>
    <s:textfield name="course.lecturer" id="course.lecturer" requiredLabel="true" class="form-control" placeholder="Frank Zimmermann"/>
  </div>
  <div class="form-group">
    <label for="course.title"> <s:text name="course.title"/> </label>
    <s:textfield name="course.title" id="course.title" requiredLabel="true" class="form-control" placeholder="Mathe I"/>
  </div>
  <s:submit action="saveCourse" key="button.addCourse" class="btn btn-primary"/>
</s:form>