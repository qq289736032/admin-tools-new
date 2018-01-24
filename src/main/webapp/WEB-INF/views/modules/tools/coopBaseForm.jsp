<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<html>
<head>
	<title><spring:message code='str1224'/></title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li ><a href="${ctx}/tools/coop/coopBaseList"><spring:message code='coop.coopBase'/></a></li>
		<c:if test="${flag==1}">
			<li class="active"><a href="${ctx}/tools/coop/coopBaseList?coopname=${list[0].coopname }&flag=1"><spring:message code='str1225'/></a></li>
		</c:if>
		<c:if test="${flag!=1}">
		<li class="active"><a href="${ctx}/tools/coop/coopBaseForm"><spring:message code='str1226'/></a></li>
		</c:if>
	</ul>
  	<tags:message content="${message}"/>
	  <form id="inputForm"  action="${ctx}/tools/coop/coopBaseAdd" method="post" class="form-horizontal">
		<div class="control-group">
            <label class="control-label"><spring:message code='str230'/>:</label>
            <div class="controls">
            	<input type="text" name="coopname" value="${list[0].coopname }"  class="input-xlarge required"/>
            	<span class="help-inline"><spring:message code='str1227'/>37<spring:message code='str1228'/> 37</span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">key:</label>
            <div class="controls">
            	<input type="text" name="key" value="${list[0].key }"  class="input-xlarge required"/>
            	<span class="help-inline"><spring:message code='str1227'/>12345678</span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><spring:message code='str547'/>key:</label>
            <div class="controls">
            	<input type="text" name="rechargeKey" value="${list[0].rechargeKey }"  class="input-xlarge required"/>
            	<span class="help-inline"><spring:message code='str1227'/>12345678</span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">baseUrl(<spring:message code='str1229'/>Url):</label>
            <div class="controls">
            	<input type="text" name="baseUrl" value="${list[0].baseUrl }"  class="input-xlarge required"/>
            	<span class="help-inline"><spring:message code='str1227'/>http://www.37.com</span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">errorUrl(<spring:message code='str1230'/>Url):</label>
            <div class="controls">
            	<input type="text" name="errorUrl" value="${list[0].errorUrl }"  class="input-xlarge required"/>
            	<span class="help-inline"><spring:message code='str1227'/>http://www.37.com</span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">payUrl(<spring:message code='str547'/>Url):</label>
            <div class="controls">
            	<input type="text" name="payUrl" value="${list[0].payUrl }"  class="input-xlarge required"/>
            	<span class="help-inline"><spring:message code='str1227'/>http://www.37.com</span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">bbsUrl(<spring:message code='str1231'/>Url):</label>
            <div class="controls">
            	<input type="text" name="bbsUrl" value="${list[0].bbsUrl }"  class="input-xlarge required"/>
            	<span class="help-inline"><spring:message code='str1227'/>http://www.37.com</span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">suggestUrl(<spring:message code='str1232'/>Url):</label>
            <div class="controls">
            	<input type="text" name="suggestUrl" value="${list[0].suggestUrl }"  class="input-xlarge required"/>
            	<span class="help-inline"><spring:message code='str1227'/>http://www.37.com</span>
            </div>
        </div>
		<div class="control-group">
            <label class="control-label">cmUrl(<spring:message code='str1233'/>Url):</label>
            <div class="controls">
            	<input type="text" name="cmUrl" value="${list[0].cmUrl }"  class="input-xlarge required"/>
            	<span class="help-inline"><spring:message code='str1227'/>http://www.37.com</span>
            </div>
        </div>		
		<div class="control-group">
            <label class="control-label">microClientUrl(<spring:message code='str1234'/>Url):</label>
            <div class="controls">
            	<input type="text" name="microClientUrl" value="${list[0].microClientUrl }"  class="input-xlarge required"/>
            	<span class="help-inline"><spring:message code='str1227'/>http://www.37.com</span>
            </div>
        </div>
        <div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='str1216'/>"/>&nbsp;&nbsp;
			<input id="btnSubmit" class="btn" type="button" value="<spring:message code='str1235'/>" onclick="history.go(-1)"/>
		</div>
			
		</form>	

</body>
</html>
