<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code='str857'/></title>
<meta name="decorator" content="default" />
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/log/jewel/jewelUpgradeList"><spring:message code='str857'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/log/jewel/jewelUpgradeList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" /> 
		<label><spring:message code='str3'/> <spring:message code='str4'/></label> 
		<input type="text" name="createDateStart" class="input-small" maxlength="20" readonly="readonly" value="${paramMap.createDateStart}" id="startDatePicker" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDatePicker\')}'})">
		-&nbsp;<input type="text" name="createDateEnd" class="input-small" readonly="readonly" value="${paramMap.createDateEnd}" id="endDatePicker" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatePicker\')}'})">
        <label><spring:message code='str779'/></label> 
        <select id=partId name="partId">
        	<option value=""><spring:message code='str627'/></option>
         	<c:forEach var="item" items="${fns:getDictList('equip_type')}">
				<option value="${item.value}"
				<c:if test="${item.value == paramMap.partId }">selected = "selected"</c:if>
				><spring:message code="${item.internationalKey}"/></option>
			</c:forEach>

        </select> 
		<label><spring:message code='str55'/></label> 
		<input id="serverIds" name="serverIds" value="${paramMap.serverIdList}" readonly="readonly" /> 
		<!-- <input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="openSeverDialog();">  -->
		<input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="showContent('${ctx}/tools/gameArea/openGameServer')">
		 &nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
	</form>
	<tags:message content="${message}" />
	<form id="tableForm" action="">
		<table id="contentTable" class="table table-striped table-bordered table-condensed">
			<tr ><th ><spring:message code='str858'/></th><th><spring:message code='str206'/></th></tr>
			<c:forEach items="${jewel }" var="item">
				<tr>
					<td>${item.level}</td>
					<td>${item.count}</td><%--<spring:message code='str206'/>--%>
				</tr>
			</c:forEach>
		</table>
	</form>
</body>
</html>
