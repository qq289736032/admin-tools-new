<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code='str778'/></title>
<meta name="decorator" content="default" />
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/log/equip/equipStrengthenList"><spring:message code='str778'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/log/equip/equipStrengthenList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" /> 
		<label><spring:message code='str3'/> <spring:message code='str4'/></label>
		<input name="startDate" id="startDate" maxlength="50"  value="${paramMap.startDate}" class="input-small required" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
		<label><spring:message code='str779'/></label> 
		<select name="partId">
			<c:forEach var="item" items="${fns:getDictList('equip_type')}">
				<option value="${item.value}"
				<c:if test="${item.value == paramMap.partId }">selected = "selected"</c:if>
				><spring:message code="${item.internationalKey}"/></option>
			</c:forEach>
		</select> 
		<label><spring:message code='str55'/></label>
	    <input id="serverIds" name="serverIds" value="${paramMap.serverIdList}" readonly="readonly"/>
		<input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="showContent('${ctx}/tools/gameArea/openGameServer')">
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();" />
	</form>
	<tags:message content="${message}" />
	<div class="row-fluid">
		<div class="span12 panel panel-primary">
			<div class="panel-heading"><spring:message code='str780'/></div>
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<tr>
					<th width="200px"><spring:message code='str781'/></th><th width="200px"><spring:message code='str206'/></th>
				</tr>
				<c:forEach items="${equip}" var="item">
					<tr>
						<td>${item.level}</td>
						<td>${item.num}</td>
					</tr>
				</c:forEach>
			</table>
 		</div>
 	</div>
	<%-- <div class="pagination">${page}</div> --%>
</body>
</html>
