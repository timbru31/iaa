<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="page-header">
  <h1>
    <s:text name="create.question" />
  </h1>
</div>

<s:form action="saveQuestion">
  <s:hidden name="examId" value="%{#attr.examId}" />

  <script type="text/javascript">
      function activeTab(tab) {
        $('.nav-tabs a[href="#' + tab + '"]').tab('show');
      };
    </script>

  <nav class="navbar navbar-default">
    <div class="container-fluid">
        <button href="#sc" data-toggle="tab" type="button"
          class="btn btn-default navbar-btn">
          <s:text name="create.Question1" />
        </button>
        <button href="#mc" data-toggle="tab" type="button"
          class="btn btn-default navbar-btn">
          <s:text name="create.Question2" />
        </button>
        <button href="#fitbt" data-toggle="tab" type="button"
          class="btn btn-default navbar-btn">
          <s:text name="create.Question3" />
        </button>
        
        <s:submit key="createQuestion.submit"
          class="btn btn-success navbar-btn navbar-right" />
    </div>
  </nav>

  <!-- 
  <ul class="nav nav-pills">
    <li><a href="#sc" data-toggle="tab"><s:text name="create.Question1" /></a></li>
    <li><a href="#mc" data-toggle="tab"><s:text name="create.Question2" /></a></li>
    <li><a href="#fitbt" data-toggle="tab"><s:text
          name="create.Question3" /></a></li>
          
    <li><s:submit key="createQuestion.submit" class="btn btn-primary navbar-btn" /></li>
  </ul> 
-->

  <div class="tab-pane">
    <s:textfield name="question.text" class="form-control"
      placeholder="%{getText('create.enterQuestion')}" />
  </div>

  <div class="tab-content" id="tabs">

    <div class="tab-pane" id="sc">
      <div class="input-group">
        <span class="input-group-addon"> <input type="radio"
          aria-label="...">
        </span> <input type="text" class="form-control">
      </div>
      <div class="input-group">
        <span class="input-group-addon"> <input type="radio"
          aria-label="...">
        </span> <input type="text" class="form-control">
      </div>

      <button type="button" class="btn btn-default">
        <span class="glyphicon glyphicon-plus"></span>
      </button>
    </div>

    <div class="tab-pane" id="mc">
      <div class="input-group">
        <span class="input-group-addon"> <input type="checkbox">
        </span> <input type="text" class="form-control" />
      </div>
      <div class="input-group">
        <span class="input-group-addon"> <input type="checkbox">
        </span> <input type="text" class="form-control" />
      </div>

      <button type="button" class="btn btn-default">
        <span class="glyphicon glyphicon-plus"></span>
      </button>
    </div>

    <div class="tab-pane" id="fitbt">
      <s:textfield name="question.text" id="fitb" class="form-control"
        placeholder="%{getText('create.enterGapQuestion')}" />
    </div>

  </div>

  <div class="row">
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

        <li class="disabled"><a href="#" aria-label="Next"><span
            aria-hidden="true">&raquo;</span></a></li>
      </ul>
    </nav>

  </center>
</s:form>