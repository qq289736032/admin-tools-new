<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str155'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/highcharts.jsp" %>
	<script type="text/javascript">
	$(document).ready(function(){
		$("#s2id_pids").hide();
		$("#pids").multiselect({
			includeSelectAllOption:true,
			enableFiltering:true
		});
	});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/log/guildLog/getGuildList"><spring:message code='str155'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/log/guildLog/getGuildList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label><spring:message code='str156'/> <spring:message code='str4'/></label>
		<input id="name" name="name" type="text" value="${name}"/>
		<label><spring:message code='str3'/> <spring:message code='str4'/></label>
		<input name="createDateStart" id="createDateStart" maxlength="50"  value="${paramMap.createDateStart}" class="input-small required" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
		-
		<input name="createDateEnd" maxlength="50"  class="input-small required" value="${paramMap.createDateEnd}" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'createDateStart\')}'})"/>
		&nbsp;
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
		<input id="btnExport" class="btn btn-primary" type="button" value="<spring:message code='str5'/>" onclick="exportXls('${ctx}/log/moneyGetLog/exportXls')"/>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
		<tr><th><spring:message code='str157'/></th>	<th><spring:message code='str158'/></th>	<th><spring:message code='str101'/></th>	<th><spring:message code='str159'/></th>	<th><spring:message code='str160'/></th>
			<th><spring:message code='str161'/></th>	<th><spring:message code='str162'/></th> <th><spring:message code='str163'/></th></tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="item">
			<tr>
				<td>${item.name}</td>
				<td>${item.leader_name}</td>
				<td><fmt:formatDate value="${item.add_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${item.level}</td>
				<td>${item.combat_power}</td>
				<td>${item.current_member_num}</td>
				<td></td>
				<td><a href="${ctx}/log/guildLog/deleteGuild" onclick="return confirmx('<spring:message code='str164'/>', this.href)"><spring:message code='str22'/><a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	
	<script type="text/javascript">
		$(document).ready(function(){
			$('table.highchart').highchartTable();
		});
	</script>

</body>
</html>
