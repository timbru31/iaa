<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="page-header">
  <h1>
    <s:if test="examId == null">
      <s:text name="create.title" />
    </s:if>
    <s:else>
      <s:text name="edit.title" />
    </s:else>
  </h1>
</div>

<s:form action="saveExam">
  <s:if test="hasFieldErrors()">
    <div class="alert alert-danger" role="alert">
      <span class="glyphicon glyphicon-exclamation-sign"
        aria-hidden="true"></span> <span class="sr-only"><s:text
          name="validation.error" /></span> <strong><s:text
          name="validation.errorIntro" /></strong>
      <s:fielderror />
    </div>
  </s:if>
  <s:hidden name="examId" value="%{#attr.examId}" />
  <div
    class="form-group ${fieldErrors.containsKey('exam.name') ? 'has-error' : ''}">
    <label for="create.examName"><s:text name="create.examName" /></label>
    <s:textfield name="exam.name" id="create.examName" type="text"
      class="form-control" required="true"
      placeholder="%{getText('create.examName')}" />
  </div>
  <div
    class="form-group ${fieldErrors.containsKey('exam.examTime') ? 'has-error' : ''}">
    <label for="create.examTime"><s:text name="create.examTime" /></label>
    <s:textfield name="exam.examTime" id="create.examTime" type="number"
      inputmode="numeric" min="1" pattern="[0-9]*" class="form-control"
      required="true" placeholder="%{getText('create.examTime')}" />
  </div>
  <div
    class="form-group ${fieldErrors.containsKey('exam.minPoints') ? 'has-error' : ''}">
    <label for="create.minPoints"><s:text
        name="create.minPoints" /></label>
    <s:textfield name="exam.minPoints" id="create.minPoints"
      type="number" inputmode="numeric" min="1" max="100"
      pattern="[0-9]*" class="form-control" required="true"
      placeholder="%{getText('create.minPoints')}" />
  </div>
  <div
    class="form-group ${fieldErrors.containsKey('exam.creditPoints') ? 'has-error' : ''}">
    <label for="create.examCP"><s:text name="create.examCP" /></label>
    <s:select required="true" class="form-control" id="create.examCP" headerKey='' headerValue="" listValue="value" name="exam.creditPoints" list="@de.nordakademie.iaa_multiple_choice.domain.CreditPointType@values()" />
  </div>
  <div
    class="form-group ${fieldErrors.containsKey('exam.evaluationMethod') ? 'has-error' : ''}">
    <label for="create.evaluationMethod"><s:text name="create.evaluationMethod" /></label>
    <s:select required="true" class="form-control" id="create.evaluationMethod" headerKey='' headerValue="" listValue="%{getText(text)}" name="exam.evaluationMethod" list="@de.nordakademie.iaa_multiple_choice.domain.WrongAnswerEvaluationMethod@values()" />
  </div>
  <div class="form-group ${fieldErrors.containsKey('exam.endDate') ? 'has-error' : ''}">
    <label for="examStart"><s:text name="create.examPeriod" /></label>
    <div class="input-daterange input-group" id="datepicker">
      <s:textfield type="text" class="input-sm form-control"
        name="rawStartDate" id="examStart" required="true" />
      <span class="input-group-addon"><s:text name="create.examPeriodTo" /></span>
      <s:textfield type="text" class="input-sm form-control"
        name="rawEndDate" id="examEnd" required="true" />
    </div>
  </div>
  <div class="center">
    <s:if test="examId == null">
      <s:submit class="btn btn-success" value="%{getText('create.submit')}" />
    </s:if>
    <s:else>
      <s:submit class="btn btn-success" value="%{getText('edit.submit')}" />
    </s:else>
  </div>
</s:form>

<s:url var="back" namespace="/" action="lecturer" />
<s:a href="%{back}" class="btn btn-primary navbar-btn">
  <s:text name="create.back" />
</s:a>

<script
  src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/js/bootstrap-datepicker.min.js"
  integrity="sha256-urCxMaTtyuE8UK5XeVYuQbm/MhnXflqZ/B9AOkyTguo="
  crossorigin="anonymous"></script>
<script
  src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/locales/bootstrap-datepicker.de.min.js"
  integrity="sha256-MRg0FdDDqvQkQ3VIUMZCZ39M6O40kpoIYqCGU2rRyxE="
  crossorigin="anonymous"></script>

<script type="text/javascript">
$("#datepicker").datepicker({
  todayHighlight: true,
  calendarWeeks: true,
  startDate: "tomorrow",
  maxViewMode: 1,
  clearBtn: true,
  <s:if test="#request.locale.language == 'de'">
    language: "de",
    format: "dd.mm.yyyy"
  </s:if>
  <s:else>
    language: "en"
  </s:else>
});
</script>
