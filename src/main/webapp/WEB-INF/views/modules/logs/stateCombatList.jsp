<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code='str1051'/></title>
<meta name="decorator" content="default" />
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/log/state/stateCombatList"><spring:message code='str1051'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/log/state/stateCombatList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" /> 
		<label><spring:message code='str3'/> <spring:message code='str4'/></label>
		<input type="text" name="createDateStart" class="input-small" maxlength="20" readonly="readonly" value="${paramMap.createDateStart}" id="startDatePicker" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDatePicker\')}',minDate:'#F{$dp.$D(\'endDatePicker\',{d:-6})}'})">
		-&nbsp;<input type="text" name="createDateEnd" class="input-small" readonly="readonly" value="${paramMap.createDateEnd}" id="endDatePicker" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatePicker\')}',maxDate:'#F{$dp.$D(\'startDatePicker\',{d:6})}'})">
		<label><spring:message code='str55'/></label>
		<input id="serverIds" name="serverIds" value="${paramMap.serverIds}" readonly/>
        <input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="showContent('${ctx}/tools/gameArea/openGameServer')">
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();" />
	</form>
	<tags:message content="${message}" />
 	<div class="row-fluid">
			<table id="contentTable" class="table table-striped table-bordered table-condensed">

					<tr>
						<th width='200'><spring:message code='str637'/></th><td width='200'>${active[0].num}</td>
					</tr>
					<tr>
						<th><spring:message code='str789'/></th><td><c:if test="${participation[0].num ==null}"> 0</c:if>
					<c:if test="${participation[0].num !=null}">${participation[0].num}</c:if></td>
					</tr>
					<tr>
						<th><spring:message code='str1052'/></th><td>${statue[0].num}</td>
					</tr>
					<tr>
						<th><spring:message code='str1053'/></th><td><c:if test="${dead[0].num ==null}"> 0</c:if>
					<c:if test="${dead[0].num !=null}">${dead[0].num }</c:if></td>
					</tr>
			</table>
 	</div>
</body>
</html>
