<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str913'/></title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/log/powerRank/powerRank"><spring:message code='str913'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/log/powerRank/powerRank" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

		<label><spring:message code='str3'/> <spring:message code='str4'/></label>
	    <input name="createDateStart" style="width: 216px" readonly="readonly" value="${paramMap.createDateStart}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
		
		<label><spring:message code='str55'/></label>
	    <input id="serverIds" name="serverIds" value="${paramMap.serverIdList}" readonly="readonly"/>
	    <%--<input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="openSeverDialog();">--%>
		<input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="showContent('${ctx}/tools/gameArea/openGameServer')">
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
	</form>
	<tags:message content="${message}"/>
	
	<br/>
	<form id="tableForm" action="">
				<table id="powerRank" class="datatable table table-striped table-bordered table-condensed">
					<thead>
					<th><spring:message code='str768'/></th><th><spring:message code='str138'/>ID</th><th><spring:message code='str7'/></th><th><spring:message code='str769'/></th><th><spring:message code='str910'/></th><th><spring:message code='str911'/></th>
					<th>vip<spring:message code='str85'/></th><th><spring:message code='str54'/></th><th>7<spring:message code='str912'/></th><th>30<spring:message code='str912'/></th><th><spring:message code='str773'/></th><th><spring:message code='str774'/></th>
					</thead>
					<tbody>
					<c:forEach items="${page.list}" var="item">
						<tr>
							<td>${item.area_id}</td>
							<td>${item.role_id}</td>
							<td>${item.role_name}</td>
							<td> </td>
							<td>${item.today_max_power}</td>
							<td>${item.his_max_power}</td>
							<td>${fns:getVipLevel(item.vip_level)}</td>
							<td>${item.tra}</td>
							<td>${item.seven_day_recharge}</td>
							<td>${item.thirty_day_recharge}</td>
							<td><fmt:formatDate value="${item.create_role_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							<td><fmt:formatDate value="${item.last_login_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			<div class="pagination">${page}</div>
    </form>
		

<script type="text/javascript">
	
</script>

</body>
</html>
