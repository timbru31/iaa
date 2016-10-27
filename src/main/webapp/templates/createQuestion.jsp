<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:form action="saveQuestion">
  <s:hidden name="examId" value="%{#attr.examId}" />
  <div class="panel panel-default">
    <div class="panel-body">
      <s:text name="create.rightanswer" />
    </div>
  </div>

  <div class="btn-group btn-group-justified" role="group">

    <div class="btn-group">
      <button type="button" class="btn btn-primary">
        <s:text name="create.Question1" />
      </button>
    </div>

    <div class="btn-group">
      <button type="button" class="btn btn-primary">
        <s:text name="create.Question2" />
      </button>
    </div>

    <div class="btn-group">
      <button type="button" class="btn btn-primary">
        <s:text name="create.Question3" />
      </button>
    </div>

  </div>

  <script type="text/javascript">
      function activeTab(tab) {
        $('.nav-tabs a[href="#' + tab + '"]').tab('show');
      };

      activeTab('aaa');
    </script>

  <!-- Tab panes -->
  <div class="tab-content">

    <div class="tab-pane active">
      <s:textfield name="question.text" class="form-control"
        placeholder="%{getText('create.enterQuestion')}" />
    </div>

    <div class="tab-pane active">

      <div class="radio">
        <label> <input type="radio" checked> <input type="text">
        </label>
      </div>
      <div class="radio">
        <label> <input type="radio"> <input type="text">
        </label>
      </div>
      <div class="radio">
        <label> <input type="radio"> <input type="text">
        </label>
      </div>

      <button type="button" class="btn btn-default">
        <span class="glyphicon glyphicon-plus"></span>
      </button>

    </div>

    <div role="tabpanel" class="tab-pane">
      <s:textfield name="question.text" class="form-control"
        placeholder="%{getText('create.enterQuestion')}" />
      <div>
        <input type="checkbox" name="answer" /> <input type="text">
      </div>
      <div>
        <input type="checkbox" name="answer" /> <input type="text">
      </div>

      <button type="button" class="btn btn-default navbar-btn">
        <s:text name="create.nextAnswer" />
      </button>
    </div>

    <div role="tabpanel" class="tab-pane" id="Fitb">
      <s:textfield name="question.text" id="fitb" class="form-control"
        placeholder="%{getText('create.enterGapQuestion')}" />
    </div>
  </div>

  <div class="row">
    <div class="col-md-1">
      <button type="button" class="btn btn-default">
        <s:text name="create.deleteQuestion" />
      </button>
    </div>
    <div class="col-md-11">
      <s:textfield name="question.points" id="points" class="form-control"
        placeholder="Anzahl Punkte" />
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

  <center>
    <s:submit key="createQuestion.submit" class="btn btn-primary" />
  </center>
</s:form>