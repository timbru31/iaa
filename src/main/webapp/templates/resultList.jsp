<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="page-header">
  <h1>
    <s:text name="resultList.title" />
  </h1>
</div>
<s:if test="%{student.testResults.isEmpty()}">
  <div class="panel panel-default">
    <div class="panel-body">
      <s:text name="resultList.noExamsTaken" />
    </div>
  </div>
</s:if>
<s:else>
  <div class="table-responsive">
    <table class="table table-hover">
      <tr>
        <th><s:text name="create.examName" /></th>
        <th><s:text name="resultList.result" /></th>
      </tr>
      <s:iterator value="student.testResults">
        <s:if test="isExpired()">
          <tr>
            <td><b><s:text name="exam.getName()" /></b></td>
            <td>
              <s:if test="passed">
                <button class="btn btn-success btn-result"><s:text name="resultList.passed" /></button>
              </s:if>
              <s:else>
                <button class="btn btn-danger btn-result"><s:text name="resultList.failed" /></button>
              </s:else>
            </td>
          </tr>
        </s:if>
      </s:iterator>
    </table>
  </div>
</s:else>
