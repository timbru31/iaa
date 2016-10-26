<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<center>
  <h1>
    <s:text name="create.title" />
  </h1>
</center>

<s:form>
  <div class="form-group">
    <label for="create.examName"><s:text name="create.examName"/></label>
    <input type="examName" class="form-control" id="create.examName" placeholder="<s:text name="create.examName"/>">
  </div>
  <div class="form-group">
    <label for="create.examTime"><s:text name="create.examTime"/></label>
    <input type="examTime" class="form-control" id="create.examTime" placeholder="<s:text name="create.examTime"/>">
  </div>
  <div class="form-group">
    <label for="create.minPoints"><s:text name="create.minPoints"/></label>
    <input type="minPoints" class="form-control" id="create.minPoints" placeholder="<s:text name="create.minPoints"/>">
  </div>
  <div class="form-group">
    <label for="create.examCP"><s:text name="create.examCP"/></label>
    <input type="examCP" class="form-control" id="create.examCP" placeholder="<s:text name="create.examCP"/>">
  </div>
  <div class="form-group">
    <label for="create.examPeriod"><s:text name="create.examPeriod"/></label>
    <input type="examPeriod" class="form-control" id="create.examPeriod" placeholder="<s:text name="create.examPeriod"/>">
  </div>

  <center>
    <s:submit key="create.finalSubmit" class="btn btn-primary">
    </s:submit>
  </center>

</s:form>

