<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="page-header countdown-header">
  <h1>
    <span><s:text name="show.title" /></span>
    <small class="countdown" id="clock"></small>
  </h1>
</div>
<div>
<div class="progress">
  <div class="progress-bar progress-bar-success progress-bar-striped" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 40%">
    40%
  </div>
</div>
  <s:if test="%{question.type == @de.nordakademie.iaa_multiple_choice.domain.QuestionType@SINGLE_CHOICE}">
    <h4><s:property value="question.text"/></h4>
    <s:iterator value="question.answers">
      <div class="answer input-group">
        <span class="input-group-addon"><input type="radio" name="sc"></span>
        <span class="form-control"><s:property value="text"/></span>
      </div>
    </s:iterator>
  </s:if>
  <s:elseif test="%{question.type == @de.nordakademie.iaa_multiple_choice.domain.QuestionType@MULTIPLE_CHOICE}">
    <h4><s:property value="question.text"/></h4>
    <s:iterator value="question.answers">
      <div class="answer input-group">
        <span class="input-group-addon"><input type="checkbox" name="mc"></span>
        <span class="form-control"><s:property value="text"/></span>
      </div>
    </s:iterator>
  </s:elseif>
  <s:elseif test="%{question.type == @de.nordakademie.iaa_multiple_choice.domain.QuestionType@FILL_IN_THE_BLANK}">
    <h4><s:text name="fillInTheBlank"/></h4>
    <s:iterator value='question.getFormattedQuestionText().split("\\\[\\\]")'>
      <s:property /><input type="text">
    </s:iterator>
  </s:elseif>
</div>

<div class="center">
  <nav>
    <ul class="pagination">
      <s:if test="%{exam.isFirstQuestion(question)}">
        <li class="disabled"><span>&laquo;</span></li>
      </s:if>
      <s:else>
        <s:url var="prevQuestionURL" namespace="/" action="takeExam">
          <s:param name="examId">${exam.id}</s:param>
          <s:param name="questionId">${exam.getPreviousQuestion(question).getId()}</s:param>
        </s:url>
        <li class="prev"><s:a href="%{prevQuestionURL}"><span>&laquo;</span></s:a></li>
      </s:else>
      <s:iterator value="exam.questions" status="it">
        <s:url var="questionURL" namespace="/" action="takeExam">
          <s:param name="examId">${exam.id}</s:param>
          <s:param name="questionId">${id}</s:param>
        </s:url>
        <s:if test="question.id == id">
          <li class="active"><span><s:property value="%{#it.count}" /></span></li>
        </s:if>
        <s:else>
          <li><s:a href="%{questionURL}"><s:property value="%{#it.count}" /></s:a></li>
        </s:else>
      </s:iterator>
      <s:if test="%{exam.isLastQuestion(question)}">
        <li class="disabled"><span>&raquo;</span></li>
      </s:if>
      <s:else>
        <s:url var="nextQuestionURL" namespace="/" action="takeExam">
          <s:param name="examId">${exam.id}</s:param>
          <s:param name="questionId">${exam.getNextQuestion(question).getId()}</s:param>
        </s:url>
        <li class="next"><s:a href="%{nextQuestionURL}"><span>&raquo;</span></s:a></li>
      </s:else>
    </ul>
  </nav>
</div>

<script src="https://cdn.jsdelivr.net/jquery.countdown/2.2.0/jquery.countdown.min.js" integrity="sha256-Ikk5myJowmDQaYVCUD0Wr+vIDkN8hGI58SGWdE671A8=" crossorigin="anonymous"></script>
<script type="text/javascript">
  function getRemainingTime() {
    return new Date(<s:property value="endTimeMillis" /> * 1000);
  }

  $('#clock').countdown(getRemainingTime(), function(event) {
    $(this).html(event.strftime('%H:%M:%S'));
  });
</script>
