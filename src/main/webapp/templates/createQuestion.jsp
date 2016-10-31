<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="page-header">
  <h1>
    <s:text name="create.question" />
  </h1>
</div>

<s:form action="saveQuestion">
  <s:hidden name="examId" value="%{#attr.examId}" />
  <s:hidden name="questionType" id="questionType" value="" />


  <script type="text/javascript">
      let
      value = 2;

      $(document).ready(function() {
        $('.navbar-btn').on('show.bs.tab', function(event) {
          $('#questionType').val(event.target.hash.substr(1));
        });
      })

      function addRadioButton(divName) {
        let
        newdiv = document.createElement('div');
        newdiv.className = "input-group choice";
        newdiv.innerHTML = "<span class='input-group-addon'><input type='radio' name='sc' value='" + value + "'></span><input name='rawAnswerTextsSc' type='text' class='form-control'>";
        document.querySelector(divName).appendChild(newdiv);
        value++;
      };

      function addCheckbox(divName) {
        let
        newdiv = document.createElement('div');
        newdiv.className = "input-group choice";
        newdiv.innerHTML = "<span class='input-group-addon'><input type='checkbox' name='mc' value='" + value + "'></span><input name='rawAnswerTextsMc' type='text' class='form-control'>";
        document.querySelector(divName).appendChild(newdiv);
        value++;
      };
    </script>

  <nav class="navbar navbar-default">
    <div class="container-fluid">
      <a href="#sc" data-toggle="tab" type="button"
        class="btn btn-default navbar-btn"> <s:text name="create.Question1" />
      </a> <a href="#mc" data-toggle="tab" type="button"
        class="btn btn-default navbar-btn"> <s:text name="create.Question2" />
      </a> <a href="#fitbt" data-toggle="tab" type="button"
        class="btn btn-default navbar-btn"> <s:text name="create.Question3" />
      </a>

      <s:submit key="createQuestion.submit"
        class="btn btn-success navbar-btn navbar-right" />
    </div>
  </nav>

  <div class="question">
    <s:textfield name="question.text" class="form-control"
      placeholder="%{getText('create.enterQuestion')}" />
  </div>

  <div class="tab-content" id="tabs">

    <div class="tab-pane" id="sc">
      <div id="addRadio">
        <div class="input-group choice">
          <span class="input-group-addon"> <input type="radio" name="sc"
            value="0">
          </span> <input name="rawAnswerTextsSc" type="text" class="form-control">
        </div>
        <div class="input-group choice">
          <span class="input-group-addon"> <input type="radio" name="sc"
            value="1">
          </span> <input name="rawAnswerTextsSc" type="text" class="form-control">
        </div>
      </div>

      <button onclick="addRadioButton('div[id=addRadio]')" type="button"
        class="btn btn-default">
        <span class="glyphicon glyphicon-plus"></span>
      </button>
    </div>

    <div class="tab-pane" id="mc">
      <div id="addCheckbox">
        <div class="input-group choice">
          <span class="input-group-addon"> <input type="checkbox"
            name="mc" value="0">
          </span> <input name="rawAnswerTextsMc" type="text" class="form-control">
        </div>
        <div class="input-group choice">
          <span class="input-group-addon"> <input type="checkbox"
            name="mc" value="1">
          </span> <input name="rawAnswerTextsMc" type="text" class="form-control">
        </div>
      </div>

      <button onclick="addCheckbox('div[id=addCheckbox]')" type="button"
        class="btn btn-default">
        <span class="glyphicon glyphicon-plus"></span>
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
    <div class="col-md-10">
      <s:textfield name="question.points" id="points" class="form-control"
        placeholder="Anzahl Punkte" />
    </div>
  </div>

  <div class="panel panel-default">
    <div class="panel-body">
      <s:text name="create.rightanswer" />
    </div>
  </div>

  <center>
    <nav>
      <ul class="pagination">
        <li class="disabled"><a href="#" aria-label="Previous"><span
            aria-hidden="true">&laquo;</span></a></li>
        <li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li>
        <li class=""><a href="#">2 <span class="sr-only">(current)</span></a></li>
        <li class=""><a href="#">3 <span class="sr-only">(current)</span></a></li>
        <li class=""><a href="#">4 <span class="sr-only">(current)</span></a></li>
        <li class=""><a href="#">5 <span class="sr-only">(current)</span></a></li>
        <li class=""><a href="#">6 <span class="sr-only">(current)</span></a></li>
        <li class=""><a href="#">7 <span class="sr-only">(current)</span></a></li>
        <li class=""><a href="#">8 <span class="sr-only">(current)</span></a></li>

        <li class="disabled"><a href="#"><span
            aria-hidden="true">&raquo;</span></a></li>
      </ul>
    </nav>

  </center>
</s:form>
