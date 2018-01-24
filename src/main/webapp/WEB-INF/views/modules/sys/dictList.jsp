<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str1122'/></title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/dict/"><spring:message code='str1579'/></a></li>
		<shiro:hasPermission name="sys:dict:edit"><li><a href="${ctx}/sys/dict/form?sort=10"><spring:message code='str1580'/></a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="dict" action="${ctx}/sys/dict/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label><spring:message code='str700'/>：</label><form:select id="type" path="type" class="input-small"><form:option value="" label=""/><form:options items="${typeList}" htmlEscape="false"/></form:select>
		&nbsp;&nbsp;<label><spring:message code='str1407'/> <spring:message code='str4'/></label><form:input path="description" htmlEscape="false" maxlength="50" class="input-small"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='str83'/>"/>
	</form:form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th><spring:message code='str1581'/></th><th><spring:message code='str1582'/></th><th><spring:message code='str700'/></th><th><spring:message code='str1407'/></th><th><spring:message code='str1556'/></th><shiro:hasPermission name="sys:dict:edit"><th><spring:message code='str149'/></th></shiro:hasPermission></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="dict">
			<tr>
				<td>${dict.value}</td>
				<td><a href="${ctx}/sys/dict/form?id=${dict.id}"><spring:message code="${dict.internationalKey}"/></a></td>
				<td><a href="javascript:" onclick="$('#type').val('${dict.type}');$('#searchForm').submit();return false;">${dict.type}</a></td>
				<td>${dict.description}</td>
				<td>${dict.sort}</td>
				<shiro:hasPermission name="sys:dict:edit"><td>
    				<a href="${ctx}/sys/dict/form?id=${dict.id}"><spring:message code='str1365'/></a>
					<a href="${ctx}/sys/dict/delete?id=${dict.id}" onclick="return confirmx('<spring:message code='str1596'/>', this.href)"><spring:message code='str22'/></a>
    				<a href="<c:url value='${fns:getAdminPath()}/sys/dict/form?type=${dict.type}&sort=${dict.sort+10}'><c:param name='description' value='${fns:urlEncode(dict.description)}'/></c:url>"><spring:message code='str1583'/></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
