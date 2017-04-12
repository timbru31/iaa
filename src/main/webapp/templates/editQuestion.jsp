<%-- authors: Yannick Rump, Hannes Peterson and Tim Brust --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="page-header">
  <h1>
    <s:text name="updateQuestion.title" />
  </h1>
</div>

<div class="alert alert-info" role="alert">
  <s:text name="create.questionHint" /><br />
  <s:text name="create.gapExample" />
</div>

  <s:if test="hasFieldErrors()">
    <div class="alert alert-danger" role="alert">
      <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
      <span class="sr-only"><s:text name="validation.error" /></span> <strong><s:text
          name="validation.errorIntro" /></strong>
      <s:fielderror />
    </div>
  </s:if>

<s:form>
  <s:hidden name="examId" value="%{examId}" />
  <s:hidden name="questionId" value="%{questionId}" />
  <s:hidden name="question.type" value="%{question.type}" />

  <div class="form-group ${fieldErrors.containsKey('question.text') ? 'has-error' : ''}">
    <label class="control-label" for="questionText"><s:text name="create.enterQuestion" /></label>
    <s:textfield name="question.text" id="questionText" class="form-control" placeholder="%{getText('create.enterQuestion')}"
      required="true" />
  </div>

  <div class="tab-content answers">
    <div class="tab-pane ${question.type == 'SINGLE_CHOICE' ? 'active' : ''}" id="SINGLE_CHOICE">
      <label class="control-label"><s:text name="create.answers" /></label>
      <div id="radios">
        <s:if test="%{rawAnswerTextsSc != null}">
          <s:iterator value="rawAnswerTextsSc" var="text" status="it">
            <div class="input-group rd">
              <span class="input-group-addon"> <input type="radio"
                name="sc" value="${it.index}" ${rightAnswer == true ? 'checked' : ''}>
              </span> <input name="rawAnswerTextsSc" value="${text}" type="text"
                class="form-control">
            </div>
          </s:iterator>
        </s:if>
        <s:else>
          <s:iterator value="question.answers" status="it">
            <div class="input-group rd">
              <span class="input-group-addon"> <input type="radio"
                name="sc" value="${it.index}" ${rightAnswer == true ? 'checked' : ''}>
              </span> <input name="rawAnswerTextsSc" value="${text}" type="text"
                class="form-control">
            </div>
          </s:iterator>
        </s:else>
      </div>

      <button onclick="addRadio()" type="button" class="btn btn-default">
        <span class="glyphicon glyphicon-plus"></span>
      </button>
      <button onclick="removeRadio()" type="button" class="btn btn-default">
        <span class="glyphicon glyphicon-minus"></span>
      </button>
    </div>

    <div class="tab-pane ${question.type == 'MULTIPLE_CHOICE' ? 'active' : ''}" id="MULTIPLE_CHOICE">
      <label class="control-label"><s:text name="create.answers" /></label>
      <div id="checkboxes">
        <s:if test="%{rawAnswerTextsMc != null}">
          <s:iterator value="rawAnswerTextsMc" var="text" status="it">
            <div class="input-group cb">
              <span class="input-group-addon"> <input type="checkbox" name="mc" value="${it.index}" ${rightAnswer == true ? 'checked' : ''}>
              </span> <input name="rawAnswerTextsMc" value="${text}" type="text" class="form-control">
            </div>
          </s:iterator>
        </s:if>
        <s:else>
          <s:iterator value="question.answers" status="it">
            <div class="input-group cb">
              <span class="input-group-addon"> <input type="checkbox" name="mc" value="${it.index}" ${rightAnswer == true ? 'checked' : ''}>
              </span> <input name="rawAnswerTextsMc" value="${text}" type="text" class="form-control">
            </div>
          </s:iterator>
       </s:else>
      </div>

      <button onclick="addCheckbox()" type="button" class="btn btn-default">
        <span class="glyphicon glyphicon-plus"></span>
      </button>
      <button onclick="removeCheckbox()" type="button" class="btn btn-default">
        <span class="glyphicon glyphicon-minus"></span>
      </button>
    </div>
    <div class="tab-pane ${question.type == 'FILL_IN_THE_BLANK' ? 'active' : ''}" id="FILL_IN_THE_BLANK"></div>
  </div>

  <div class="form-group ${fieldErrors.containsKey('question.points') ? 'has-error' : ''}">
    <label class="control-label" for="questionPoints"><s:text name="create.enterPoints" /></label>
    <s:textfield name="question.points" id="questionPoints" class="form-control" placeholder="%{getText('create.enterPoints')}"
      type="number" inputmode="numeric" min="1" pattern="[0-9]*" required="true" />
  </div>

  <div class="center">
  <s:submit action="updateQuestion" class="btn btn-success" value="%{getText('updateQuestion.submit')}" />
  <s:submit action="deleteQuestion" class="btn btn-danger" value="%{getText('create.deleteQuestion')}" />
  <s:url var="createQuestionURL" namespace="/" action="createQuestion">
    <s:param name="examId">${exam.id}</s:param>
  </s:url>
  </div>
</s:form>
<s:url var="back" namespace="/" action="editExam">
  <s:param name="examId">${exam.id}</s:param>
</s:url>
<s:a href="%{back}" class="btn btn-primary navbar-btn">
  <s:text name="create.back" />
</s:a>
<s:a class="btn btn-primary navbar-btn navbar-right" href="%{createQuestionURL}"><s:text name="create.question" /></s:a>
<%@ include file="fragments/questionPagination.jspf" %>
<script type="text/javascript" src="/iaa-multiple-choice/static/js/question.js"></script>
