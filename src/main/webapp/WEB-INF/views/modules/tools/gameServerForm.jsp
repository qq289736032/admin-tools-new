<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str1376'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treeview.jsp" %>


</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/tools/gameServer/"><spring:message code='str1377'/></a></li>
		<li class="active"><a href="${ctx}/tools/gameServer/form?id=${gameServer.id}"><c:if test="${not empty gamePlatform.id }"> <spring:message code='str1365'/></c:if>
		  <c:if test="${ empty gamePlatform.id }"> <spring:message code='operation.add'/></c:if><spring:message code='str1296'/></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="gameServer" action="${ctx}/tools/gameServer/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<tags:message content="${message}"/>
		<div class="control-group">
			<label class="control-label"><spring:message code='str824'/>:</label>
            <div class="controls">
                <form:input path="serverId" htmlEscape="false" maxlength="50" class="required"/>
            </div>
		</div>
        <div class="control-group">
            <label class="control-label"><spring:message code='str561'/>:</label>
            <div class="controls">
                <form:input path="serverName" htmlEscape="false" maxlength="50" class="required"/>
            </div>
        </div>
		<div class="control-group">
			<label class="control-label"><spring:message code='str1215'/>:</label>
			<div class="controls">
                <form:textarea path="description" htmlEscape="false" rows="3" maxlength="100" class="input-xlarge"/>
			</div>
		</div>
        <div class="control-group">
            <label class="control-label"><spring:message code='str484'/>:</label>
            <div class="controls">
                <form:select path="gamePlatform.id">
                    <form:options items="${fns:getGamePlatformList()}" itemLabel="name" itemValue="id" htmlEscape="false"/>
                </form:select>
            </div>
		</div>

        <div class="control-group">
            <label class="control-label"><spring:message code='str1378'/>:</label>
            <div class="controls">
                <form:select path="isTest">
                    <form:option value="0"><spring:message code='str1379'/></form:option>
                    <form:option value="1"><spring:message code='str98'/></form:option>
                </form:select>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label"><spring:message code='str1380'/>:</label>
            <div class="controls">
                <form:select path="serverStatus">
                    <form:option value="1"><spring:message code='str1381'/></form:option>
                    <form:option value="0"><spring:message code='str876'/></form:option>
                </form:select>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label"><spring:message code='str1382'/>:</label>
            <div class="controls">
                <form:select path="isHefu">
                    <form:option value="0"><spring:message code='str1379'/></form:option>
                    <form:option value="1"><spring:message code='str98'/></form:option>
                </form:select>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label"><spring:message code='str1383'/>:</label>
            <div class="controls">
                <form:input path="gameDbUrl" htmlEscape="false" maxlength="150" class="required" cssClass="input-xxlarge"/>
                <a class="alert alert-danger">*(<spring:message code='str1384'/>:192.168.1.1:3306/dbName;username;passwd)</a>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label"><spring:message code='str1385'/>:</label>
            <div class="controls">
                <form:input path="LogDbUrl" htmlEscape="false" maxlength="150" class="required" cssClass="input-xxlarge"/>
                <a class="alert alert-danger">*(<spring:message code='str1384'/>:192.168.1.1:3306/dbName;username;passwd)</a>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label"><spring:message code='str1386'/>:</label>
            <div class="controls">
                <form:input path="gateUrl" htmlEscape="false" maxlength="150" class="required" cssClass="input-xxlarge"/>
                <a class="alert alert-danger">*(<spring:message code='str1387'/>:http://192.168.1.111:8080/servlet)</a>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label"><spring:message code='str1388'/>:</label>
            <div class="controls">
                <form:input path="gameServerRemoteUrl" htmlEscape="false" maxlength="150" class="required" cssClass="input-xxlarge"/>
                <a class="alert alert-danger">*(<spring:message code='str1387'/>:http://192.168.1.111:9999)</a>
            </div>
        </div>


		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='str1176'/> <spring:message code='str1177'/>"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="<spring:message code='str1117'/> <spring:message code='str1118'/>" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
