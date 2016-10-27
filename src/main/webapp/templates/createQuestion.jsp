<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:form>
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
    <form>
      <s:textfield id="simpleChoice" class="form-control"
        placeholder="%{getText('create.enterQuestion')}" />
      <div>
        <input type="Radio" name="answer" /> <input type="text">
      </div>
      <input type="Radio" name="answer" /> <input type="text">
      <button type="button" class="btn btn-default navbar-btn">
        <span class="glyphicon glyphicon-plus"></span>
      </button>
    </form>
  </div>

  <div role="tabpanel" class="tab-pane" id="MultipleChoice">
    <form>
      <s:textfield id="multipleChoice" class="form-control"
        placeholder="%{getText('create.enterQuestion')}" />
      <div>
        <input type="checkbox" name="answer" /> <input>
      </div>
      <input type="checkbox" name="answer" /> <input type="text">

      <button type="button" class="btn btn-default navbar-btn">
        <s:text name="create.nextAnswer" />
      </button>
    </form>
  </div>

  <div role="tabpanel" class="tab-pane" id="Fitb">
    <form>
      <s:textfield id="fitb" class="form-control"
        placeholder="%{getText('create.enterGapQuestion')}" />
    </form>
  </div>
</div>

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
  <button type="button" class="btn btn-primary">
    <s:text name="createQuestion.submit" />
  </button>
</center>
</s:form>