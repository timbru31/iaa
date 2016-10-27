<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:form action ="createQuestion">
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
  $(function() {
    $(document).ready(
        function() {
          $("#registrationForm").attr("action",
              "register" + $("#role").find("option:selected").val());
        });
    $("#role").on("change", function() {
      var selected = $(this).find("option:selected").val();
      if (selected === 'Student') {
        $("#studentNumberForm").collapse("show");
        $("#studentNumber").attr("required", true);
        $("#registrationForm").attr("action", "registerStudent");
      } else {
        $("#studentNumberForm").collapse("hide");
        $("#studentNumber").attr("required", false);
        $("#registrationForm").attr("action", "registerLecturer");
      }
    });
  });
</script>

<!-- Tab panes -->
<div class="tab-content">
  <div role="tabpanel" class="tab-pane active" id="SingleChoice">
      <s:textfield name="question.text" id="simpleChoice" class="form-control"
        placeholder="%{getText('create.enterQuestion')}" />
      <div>
        <input type="radio" name="answer" /> <input type="text">
      </div>
      <input type="radio" name="answer" /> <input type="text">
      <button type="button" class="btn btn-default navbar-btn">
        <span class="glyphicon glyphicon-plus"></span>
      </button>
  </div>

  <div role="tabpanel" class="tab-pane" id="MultipleChoice">
      <s:textfield name="question.text" id="multipleChoice" class="form-control"
        placeholder="%{getText('create.enterQuestion')}" />
      <div>
        <input type="checkbox" name="answer" /> <input>
      </div>
      <input type="checkbox" name="answer" /> <input type="text">

      <button type="button" class="btn btn-default navbar-btn">
        <s:text name="create.nextAnswer" />
      </button>
  </div>

  <div role="tabpanel" class="tab-pane" id="Fitb">
      <s:textfield name="question.text" id="fitb" class="form-control"
        placeholder="%{getText('create.enterGapQuestion')}" />
  </div>
</div>

<s:textfield name="question.points" id="points" class="form-control"
        placeholder="Anzahl Punkte" />

<button type="button" class="btn btn-default navbar-btn">
  <s:text name="create.deleteQuestion" />
</button>
<nav>
  <ul class="pagination">
    <li class="disabled"><a href="#" aria-label="Previous"><span
        aria-hidden="true">&laquo;</span></a></li>
    <li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li>
  </ul>
</nav>
<center>
  <s:submit key="createQuestion.submit" class="btn btn-primary" />
</center>
</s:form>