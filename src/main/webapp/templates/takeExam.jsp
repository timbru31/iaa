<!-- author: Tim Brust
working on exam page -->
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="page-header countdown-header">
  <h1>
    <span><s:text name="show.title" /></span>
    <span>
      <small class="countdown" id="clock"></small>
      <s:form action="submitExam" class="inline-form">
        <s:hidden name="examId" value="%{exam.id}" />
        <s:submit class="btn btn-success" value="%{getText('submit.exam')}" />
      </s:form>
    </span>
  </h1>
</div>
<div>
<div class="progress">
  <div class="progress-bar progress-bar-success progress-bar-striped" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: ${progress}%; min-width: 2em;">
    <s:property value="progress"/>%
  </div>
</div>
  <s:if test="%{question.type == @de.nordakademie.iaa_multiple_choice.domain.QuestionType@SINGLE_CHOICE}">
    <div class="answer-header">
      <h4>
        <s:property value="question.text"/>
        <strong><s:property value="question.points"/> <s:text name="points" /></strong>
      </h4>
    </div>
    <s:form action="submitAnswer">
      <s:hidden name="examId" value="%{exam.id}" />
      <s:hidden name="questionId" value="%{question.id}" />
      <s:iterator value="question.answers" status="it">
        <div class="answer input-group">
          <span class="input-group-addon"><input required type="radio" name="sc" value="${it.index}" ${testResult.getSubmittedAnswers().get(question) != null && testResult.getSubmittedAnswers().get(question).getAnswers().toArray()[it.index].rightAnswer ? 'checked' : ''}></span>
          <span class="form-control"><s:property value="text"/></span>
        </div>
      </s:iterator>
      <div class="center">
        <s:submit class="btn btn-success" value="%{getText('submit.answer')}" />
      </div>
    </s:form>
  </s:if>
  <s:elseif test="%{question.type == @de.nordakademie.iaa_multiple_choice.domain.QuestionType@MULTIPLE_CHOICE}">
    <div class="answer-header">
      <h4>
        <s:property value="question.text"/>
        <strong><s:property value="question.points"/> <s:text name="points" /></strong>
      </h4>
    </div>
    <s:form action="submitAnswer">
      <s:hidden name="examId" value="%{exam.id}" />
      <s:hidden name="questionId" value="%{question.id}" />
      <s:iterator value="question.answers" status="it">
        <div class="answer input-group">
          <span class="input-group-addon"><input type="checkbox" name="mc" value="${it.index}" ${testResult.getSubmittedAnswers().get(question) != null && testResult.getSubmittedAnswers().get(question).getAnswers().toArray()[it.index].rightAnswer ? 'checked' : ''}></span>
          <span class="form-control"><s:property value="text"/></span>
        </div>
      </s:iterator>
      <div class="center">
        <s:submit class="btn btn-success" value="%{getText('submit.answer')}" />
      </div>
    </s:form>
  </s:elseif>
  <s:elseif test="%{question.type == @de.nordakademie.iaa_multiple_choice.domain.QuestionType@FILL_IN_THE_BLANK}">
    <div class="answer-header">
      <h4>
        <s:text name="fillInTheBlank"/>
        <strong><s:property value="question.points"/> <s:text name="points" /></strong>
      </h4>
    </div>
    <s:form action="submitAnswer">
      <s:hidden name="examId" value="%{exam.id}" />
      <s:hidden name="questionId" value="%{question.id}" />
      <s:iterator value='question.getFormattedQuestionText().split("\\\[\\\]")' status="it">
        <s:property />
        <s:if test="#it.last == false || question.getFormattedQuestionText().endsWith('[]')">
          <s:textfield class="form-group blank" name="fillInTheBlankAnswers" type="text" required="true" value="%{testResult.getSubmittedAnswers().get(question) == null ? '' : testResult.getSubmittedAnswers().get(question).getAnswers().toArray()[#it.index].getText()}" />
        </s:if>
      </s:iterator>
      <div class="center">
        <s:submit class="btn btn-success" value="%{getText('submit.answer')}" />
      </div>
    </s:form>
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
<script type="text/javascript" src="/iaa-multiple-choice/static/js/jquery.auto-grow-input.min.js"></script>
<script type="text/javascript">
  function getRemainingTime() {
    return new Date(<s:property value="endTimeMillis" /> * 1000);
  }

  $('#clock').countdown(getRemainingTime(), function(event) {
    $(this).html(event.strftime('%H:%M:%S'));
  }).on('finish.countdown', function() {
    document.submitExam.submit();
  });
  
  $('.blank').autoGrowInput({ minWidth: 165, maxWidth: 600, comfortZone: 20 });
</script>
