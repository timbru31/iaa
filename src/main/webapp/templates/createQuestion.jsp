<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="page-header">
  <s:if test="hasFieldErrors()">
    <div class="alert alert-danger" role="alert">
      <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
      <span class="sr-only"><s:text name="validation.error" /></span> <strong><s:text
          name="validation.errorIntro" /></strong>
      <s:fielderror />
    </div>
  </s:if>
  <h1>
    <s:text name="create.question" />
  </h1>
</div>

<s:form action="saveQuestion">
  <s:hidden name="examId" value="%{examId}" />
  <s:hidden name="questionType" id="questionType" />

  <nav class="navbar navbar-default">
    <div class="container-fluid">
      <a href="#sc" data-toggle="tab" type="button"
        class="btn btn-default navbar-btn"> <s:text name="create.Question1" />
      </a> <a href="#mc" data-toggle="tab" type="button"
        class="btn btn-default navbar-btn"> <s:text name="create.Question2" />
      </a> <a href="#fitbt" data-toggle="tab" type="button"
        class="btn btn-default navbar-btn"> <s:text name="create.Question3" />
      </a>

      <s:submit class="btn btn-success navbar-btn navbar-right"
        value="%{getText('createQuestion.submit')}" />
    </div>
  </nav>

  <div
    class="question ${fieldErrors.containsKey('question.text') ? 'has-error' : ''}">
    <s:textfield name="question.text" class="form-control"
      placeholder="%{getText('create.enterQuestion')}" />
  </div>

  <div class="tab-content" id="tabs">

    <div class="tab-pane" id="sc">
      <div id="addRadio">
        <div class="input-group rd">
          <span class="input-group-addon"> <input type="radio" name="sc"
            value="0">
          </span> <input name="rawAnswerTextsSc" type="text" class="form-control">
        </div>
        <div class="input-group rd">
          <span class="input-group-addon"> <input type="radio" name="sc"
            value="1">
          </span> <input name="rawAnswerTextsSc" type="text" class="form-control">
        </div>
      </div>

      <button onclick="addRadio('div[id=addRadio]')" type="button"
        class="btn btn-default">
        <span class="glyphicon glyphicon-plus"></span>
      </button>
      <button onclick="removeRadio('div[id=removeRadio]')" type="button"
        class="btn btn-default">
        <span class="glyphicon glyphicon-minus"></span>
      </button>
    </div>

    <div class="tab-pane" id="mc">
      <div id="addCheckbox">
        <div class="input-group cb">
          <span class="input-group-addon"> <input type="checkbox"
            name="mc" value="0">
          </span> <input name="rawAnswerTextsMc" type="text" class="form-control">
        </div>
        <div class="input-group cb">
          <span class="input-group-addon"> <input type="checkbox"
            name="mc" value="1">
          </span> <input name="rawAnswerTextsMc" type="text" class="form-control">
        </div>
      </div>

      <button onclick="addCheckbox('div[id=addCheckbox]')" type="button"
        class="btn btn-default">
        <span class="glyphicon glyphicon-plus"></span>
      </button>
      <button onclick="removeCheckbox('div[id=removeCheckbox]')" type="button"
        class="btn btn-default">
        <span class="glyphicon glyphicon-minus"></span>
      </button>
    </div>

    <div class="tab-pane" id="fitbt"></div>

  </div>

  <div class="row question">
    <div class="col-md-2">
      <button type="button" class="btn btn-default">
        <s:text name="create.deleteQuestion" />
      </button>
    </div>
    <div
      class="col-md-10 ${fieldErrors.containsKey('question.points') ? 'has-error' : ''}">
      <s:textfield name="question.points" id="points" class="form-control"
        placeholder="Anzahl Punkte" />
    </div>
  </div>

  <div class="panel panel-default">
    <div class="panel-body">
      <s:text name="create.rightanswer" />
    </div>
  </div>

  <div class="center">
    <nav>
      <ul class="pagination">
        <li class="prev"><a href="#"><span>&laquo;</span></a></li>
        <li class="active"><a href="#">1</a></li>
        <li class="next"><a href="#"><span>&raquo;</span></a></li>
      </ul>
    </nav>
  </div>
</s:form>
<script type="text/javascript" src="/iaa-multiple-choice/static/js/addQuestion.js"></script>
