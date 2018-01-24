<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str1224'/></title>
	<meta name="decorator" content="default"/>
	<link href="${ctxStatic}/bootstrap-switch/dist/css/bootstrap2/bootstrap-switch.css" type="text/css" rel="stylesheet" />
	<script src="${ctxStatic}/bootstrap-switch/dist/js/bootstrap-switch.js" type="text/javascript"></script>
	<script src="${ctxStatic}/bootstrap-switch/dist/js/highlight.js" type="text/javascript"></script>
	<script src="${ctxStatic}/bootstrap-switch/dist/js/main.js" type="text/javascript"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/tools/coop/coopBaseList"><spring:message code='coop.coopBase'/></a></li>
		<li><a href="${ctx}/tools/coop/coopBaseForm"><spring:message code='str1226'/></a></li>
	</ul>
	 <form id="searchForm" action="${ctx}/tools/coop/coopBaseList" method="post" class="breadcrumb form-search">
		<spring:message code='str56'/>:<input type="text" name ="coopname" size="10" value="${paramMap.coopname }"/> <input type="submit" value="<spring:message code='str83'/>"/>
    </form>
		<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<tr>
			<th><spring:message code='str149'/></th><th><spring:message code='str230'/></th><th><spring:message code='str1236'/>Key</th><th><spring:message code='str547'/>Key</th><th><spring:message code='str1229'/>Url</th><th><spring:message code='str1230'/>Url</th><th><spring:message code='str547'/>Url</th><th><spring:message code='str1231'/>Url</th><th><spring:message code='str1232'/>Url</th><th><spring:message code='str1233'/>Url</th><th><spring:message code='str1234'/>Url</th>
		</tr>
		<c:forEach items="${list}" var="item">
			<tr>
			<td><a href='${ctx}/tools/coop/coopBaseList?coopname=${item.coopname}&flag=1'><spring:message code='str1365'/></a></td>
				<td>${item.coopname}</td>
				<td>${item.key}</td><td>${item.rechargeKey}</td><td>${item.baseUrl}</td><td>${item.errorUrl}</td>
				<td>${item.payUrl}</td><td>${item.bbsUrl}</td><td>${item.suggestUrl}</td><td>${item.cmUrl}</td><td>${item.microClientUrl}</td>			
				
			</tr>
		</c:forEach>
		</table>
</body>

</html>
