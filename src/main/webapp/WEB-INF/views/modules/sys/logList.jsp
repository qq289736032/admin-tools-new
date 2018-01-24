<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str1133'/></title>
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
		<li class="active"><a href="${ctx}/sys/log/"><spring:message code='str1590'/></a></li>
	</ul>
	<form:form id="searchForm" action="${ctx}/sys/log/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<div>
			<label><spring:message code='str143'/>ID<spring:message code='str4'/></label><input id="createById" name="createById" type="text" maxlength="50" class="input-small" value="${createById}"/>
			<label>URI<spring:message code='str4'/></label><input id="requestUri" name="requestUri" type="text" maxlength="50" class="input-small" value="${requestUri}"/>
			&nbsp;
			<label><spring:message code='str1591'/></label><input id="beginDate" name="beginDate" type="text" readonly="readonly" maxlength="20" class="input-small Wdate"
				value="${beginDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			<label><spring:message code='str1592'/></label><input id="endDate" name="endDate" type="text" readonly="readonly" maxlength="20" class="input-small Wdate"
				value="${endDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			<label for="exception"><input id="exception" name="exception" type="checkbox"${exception eq '1'?' checked':''} value="1"/><spring:message code='str1589'/></label>
			&nbsp;&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='str83'/>"/>
		</div>
	</form:form>
	<tags:message content="${message}"/>
	<div class="table-scrollable">
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><!-- <th><spring:message code='str1138'/></th><th><spring:message code='str1139'/></th> --><th><spring:message code='str1585'/></th><th>URI</th><th><spring:message code='str1586'/></th><th><spring:message code='str827'/>IP</th><th><spring:message code='str101'/></th></thead>
		<tbody>
		<c:forEach items="${page.list}" var="log">
			<tr>
				<%-- <td>${log.createBy.company.name}</td>
				<td>${log.createBy.office.name}</td> --%>
				<td>${log.createBy.name}</td>
				<td><strong>${log.requestUri}</strong></td>
				<td>${log.method}</td>
				<td>${log.remoteAddr}</td>
				<td><fmt:formatDate value="${log.createDate}" type="both"/></td>
			</tr>
			<tr>
				<td colspan="8"><spring:message code='str1587'/>: ${log.userAgent}<br/><spring:message code='str1588'/>: ${fns:escapeHtml(log.params)}
				<c:if test="${not empty log.exception}"><br/><spring:message code='str1589'/>: <br/><%request.setAttribute("strEnter", "\n"); %>
				${fn:replace(fns:escapeHtml(log.exception), strEnter, '<br/>')}</c:if></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
	<div class="pagination">${page}</div>
</body>
</html>
