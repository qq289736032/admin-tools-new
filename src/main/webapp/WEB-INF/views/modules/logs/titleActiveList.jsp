<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code='str1072'/></title>
<meta name="decorator" content="default" />
</head>

<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/log/title/titleActiveList"><spring:message code='str1072'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/log/title/titleActiveList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" /> 
		<label><spring:message code='str3'/> <spring:message code='str4'/></label> 
		<input type="text" name="createDateStart" class="input-small" maxlength="20" readonly="readonly" value="${paramMap.createDateStart}" id="startDatePicker" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDatePicker\')}'})">
		-&nbsp;<input type="text" name="createDateEnd" class="input-small" readonly="readonly" value="${paramMap.createDateEnd}" id="endDatePicker" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatePicker\')}'})">
		<label><spring:message code='str1073'/></label> 
        <select id="titleId" name="titleId">
        	<option value=""><spring:message code='str627'/></option>
            <c:forEach var='item' items="${fns:getDictList('title_type')}">
            	<option value='${item.value}'
            	<c:if test="${item.value==paramMap.titleId}">selected="selected"</c:if>
            	><spring:message code="${item.internationalKey}"/></option>
         	</c:forEach>

        </select> 
		<label><spring:message code='str55'/></label> 
		<input id="serverIds" name="serverIds" value="${paramMap.serverIdList}" readonly="readonly" /> 
		<input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="showContent('${ctx}/tools/gameArea/openGameServer')">
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();" />
	</form>
	<tags:message content="${message}" />
	<form id="tableForm" action="">
		<table id="contentTable" class="table table-striped table-bordered table-condensed ">
			<tr ><th><spring:message code='str688'/></th><th><spring:message code='str689'/></th><th><spring:message code='str1074'/></th></tr>
			<c:forEach items="${page.list}" var="item">
				<tr>
					<td><spring:message code="${fns:getDictKeys(item.title_id, 'title_type', item.title_id)}"/></td>
					<td>${item.count}</td><%--<spring:message code='str689'/>--%>
					<td>${item.title_id}</td><!-- <spring:message code='str1074'/> -->
				</tr>
			</c:forEach>
		</table>
	</form>
	 <div class="pagination">${page}</div> 

</body>
</html>
