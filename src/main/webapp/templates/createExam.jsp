<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="page-header">
  <h1>
    <s:text name="create.title" />
  </h1>
</div>

<s:form action="saveExam">
  <div class="form-group">
    <label for="create.examName"><s:text name="create.examName" /></label>
    <s:textfield name="exam.name" id="create.examName" type="text" required="true" class="form-control"
      placeholder="%{getText('create.examName')}" />
  </div>
  <div class="form-group">
    <label for="create.examTime"><s:text name="create.examTime" /></label>
    <s:textfield name="exam.examTime" id="create.examTime" type="number" inputmode="numeric" min="1" pattern="[0-9]*" required="true" class="form-control"
      placeholder="%{getText('create.examTime')}" />
  </div>
  <div class="form-group">
    <label for="create.minPoints"><s:text name="create.minPoints" /></label>
    <s:textfield name="exam.minPoints" id="create.minPoints" type="number" inputmode="numeric" min="1" max="100" pattern="[0-9]*" required="true" class="form-control"
      placeholder="%{getText('create.minPoints')}" />
  </div>
  <div class="form-group">
    <label for="create.examCP"><s:text name="create.examCP" /></label>
    <s:textfield name="exam.creditPoints" id="create.examCP" type="number" inputmode="numeric" min="0" step="0.5" required="true"
      class="form-control" placeholder="%{getText('create.examCP')}" />
  </div>
  <div class="form-group">
    <label for="examStart"><s:text name="create.examPeriod" /></label>
    <div class="input-daterange input-group" id="datepicker">
      <s:textfield type="text" class="input-sm form-control" name="exam.startDate" id="examStart" required="true" />
      <span class="input-group-addon"><s:text name="create.examPeriodTo" /></span>
      <s:textfield type="text" class="input-sm form-control" name="exam.finalSubmitDate" id="examEnd" required="true" />
    </div>
  </div>
  <center>
    <s:submit key="create.finalSubmit" class="btn btn-success">
    </s:submit>
  </center>
</s:form>

<s:form action="lecturer">
  <s:submit name="back" key="create.back" class="btn btn-primary navbar-btn" />
</s:form>

<script>
$('#datepicker').datepicker({
  todayHighlight: true,
  calendarWeeks: true,
  startDate: "today",
  maxViewMode: 1,
  clearBtn: true,
  <s:if test="#request.locale.language=='de'">
    language: "de",
    format: "dd.mm.yyyy"
  </s:if>
  <s:else>
    language: "en"
  </s:else>
});

</script>