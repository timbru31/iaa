<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="page-header countdown-header">
  <h1>
    <span><s:text name="show.title" /></span>
    <small class="countdown" id="clock"></small>
  </h1>
</div>
Exam time: <s:property value="exam.examTime"/><br>
Start time: <s:property value="testResult.startTime"/><br>
<div class="panel panel-default" data-toggle="tooltip" data-placement="top" title="" data-original-title="Beautifull, insn't it?">
  <div class="panel-body">

  </div>
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