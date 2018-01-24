<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str1283'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/highcharts.jsp" %>
	<style type="text/css">
		span.input-group-btn {
			display: none;
		}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/tools/crossArea/crossAreas"><spring:message code='str1283'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/tools/crossArea/crossAreas" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label><spring:message code='str1284'/></label>
	    <input id="serverId" name="serverId" value="${paramMap.serverId}"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed" style="table-layout:fixed;">
		<thead>
		<tr><th width="50"><spring:message code='str1285'/>ID</th> <th width="50"><spring:message code='str1286'/>ID</th> <th width="60"><spring:message code='str1287'/></th> <th width="60"><spring:message code='str1288'/></th> <th width="50"><spring:message code='str1289'/></th>
			<th width="100"><spring:message code='str1290'/>IP</th> <th width="50"><spring:message code='str1290'/><spring:message code='str1186'/></th> <th width="50">web<spring:message code='str1186'/></th> <th width="50"><spring:message code='str1293'/><spring:message code='str1186'/></th> <th width="60"><spring:message code='str1293'/>IP</th>
			<%--<th><spring:message code='str56'/>ID</th> --%>
			<th width="50"><spring:message code='str1294'/></th>
			<%--<th><spring:message code='str1295'/>ID</th>--%>
			<th width="400"><spring:message code='str1296'/>ID</th>
			<th width="70"><spring:message code='str149'/></th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${crossServerList}" var="item">
			<tr>
				<td>${item.worldId}</td>
				<td>${item.id}</td>
				<td>${item.worldName}</td>
				<td>${item.name}</td>
				<td>${item.type}</td>

				<td>${item.externalIp}</td>
				<td>${item.tcpPort}</td>
				<td>${item.webPort}</td>
				<td>${item.innerPort}</td>
				<td>${item.innerIp}</td>
				<%--<td>${fns:getGamePlatformNameById(item.platformId,item.platformId)}</td>--%>
				<td>
				<c:choose>
                    <c:when test="${item.status == 0}"><span class="label label-info"><spring:message code="${fns:getDictKeys(item.status,'server_status',item.status)}"/></span></c:when>
                    <c:when test="${item.status == 1}"><span class="label label-warning"><spring:message code="${fns:getDictKeys(item.status,'server_status',item.status)}"/></span></c:when>
                    <c:otherwise><span class="label label-important"><spring:message code="${fns:getDictKeys(item.status,'server_status',item.status)}"/></span></c:otherwise>
                   </c:choose>
				</td>
				<%--<td>${item.followerId}</td>--%>
				<td style="width:560px;WORD-WRAP: break-word">${fns:getGameServerListByCrossId(item.worldId)}</td>
				<td>
					<a href="${ctx}/tools/crossArea/form?crossId=${item.worldId}">
						<spring:message code='str1365'/>
					</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
	<script type="text/javascript">
	$(document).ready(function(){
		$('table.highchart').highchartTable();
		$("#s2id_pids").hide();
		$("#pids").multiselect({
			includeSelectAllOption:true,
			enableFiltering:true
		});
	});
	</script>

</html>
