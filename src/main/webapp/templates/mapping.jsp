<%-- author: Tim Brust --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="page-header">
  <h1>
    <s:text name="mapping.title" />
  </h1>
</div>

<s:if test="%{lecturer.exams.isEmpty()}">
  <div class="panel panel-default">
    <div class="panel-body">
      <s:text name="lecturer.noExams" />
    </div>
  </div>
</s:if>
<s:else>
  <div class="panel panel-default">
    <div class="table-responsive">
      <table class="table table-hover mapping-table">
        <tr>
          <th><s:text name="create.examName" /></th>
          <th><s:text name="mapping.students" /></th>
        </tr>
        <s:iterator value="lecturer.exams" status="it">
          <tr>
            <th class="examName"><s:property value="name" /></th>
            <td>
              <s:if test="%{hasActionErrors() && id == examId}">
                <div class="alert alert-danger" role="alert">
                  <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span> <span class="sr-only"><s:text name="validation.error" /></span>
                  <strong><s:text name="validation.errorIntro" /></strong>
                  <s:actionerror />
                </div>
              </s:if>
              <s:if test="%{hasActionMessages() && id == examId}">
                <div class="alert alert-success alert-dismissible" role="alert">
                  <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span></button>
                  <s:actionmessage escape="false" />
                </div>
              </s:if>
                <s:if test="!isEditable()">
                  <div class="input-group disabled">
                    <select id="taginput-${it.index}" disabled multiple data-role="tagsinput" class="mapping disabled form-control"></select>
                    <span class="input-group-btn">
                      <button class="btn btn-danger disabled "><s:text name="mapping.linkDisabled" /></button>
                    </span>
                  </div>
                </s:if>
                <s:else>
                  <div class="input-group">
                    <s:form action="examMappingAction" id="examMapping-%{#it.index}" class="exam-mapping">
                      <s:hidden name="examId" value="%{id}" />
                      <%-- placeholder attribute is invalid, but bootstrap-taginput checks for this attribute when transforming to an input field --%>
                      <select id="taginput-${it.index}" name="studentEmails" multiple data-role="tagsinput" class="mapping form-control" placeholder="user@nordakademie.de"></select>
                      <span class="input-group-btn"><s:submit class="btn btn-default" type="button" value="%{getText('mapping.link')}" /></span>
                    </s:form>
                  </div>
                </s:else>
            </td>
          </tr>
          <%-- Add existing participants to the list --%>
          <s:iterator value="tokenList" var="tokenListElement">
            <script type="text/javascript">
              $(document).ready(
                  function() {
                    $('#taginput-${it.index}').tagsinput('add',
                        '${tokenListElement.key.email}');
                  });
            </script>
          </s:iterator>
        </s:iterator>
      </table>
    </div>
  </div>
  <div class="panel panel-default">
    <div class="panel-body">
      <s:text name="mapping.hint" />

    </div>
  </div>
</s:else>
<div>
  <s:a action="lecturer" class="btn btn-primary navbar-btn">
    <s:text name="create.back" />
  </s:a>
</div>

<script
  src="https://cdn.jsdelivr.net/bootstrap.tagsinput/0.8.0/bootstrap-tagsinput.min.js"
  integrity="sha256-tQ3x4V2JW+L0ew/P3v2xzL46XDjEWUExFkCDY0Rflqc="
  crossorigin="anonymous"></script>

<script type="text/javascript" src="/iaa-multiple-choice/static/js/jquery.auto-grow-input.min.js"></script>
<script type="text/javascript">
  $('.mapping').tagsinput({
    tagClass : 'label label-primary',
    trimValue : true
  });
  $('.bootstrap-tagsinput input').autoGrowInput({ minWidth: 165, maxWidth: 600, comfortZone: 20 });
</script>
