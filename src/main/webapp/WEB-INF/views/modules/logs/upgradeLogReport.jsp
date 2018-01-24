<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str1091'/></title>
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
	
	<form id="searchForm" action="${ctx}/log/upgradeLog/upgradeLogReport" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label><spring:message code='str653'/> <spring:message code='str4'/></label>
		<input id="roleName" name="roleName" type="text" value="${roleName}"/>
		<label><spring:message code='str3'/> <spring:message code='str4'/></label>
		<input type="text" name="createDateStart" class="input-small" readonly="readonly" maxlength="20" value="${paramMap.createDateStart}" id="startDatePicker" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDatePicker\')}'})">
		-&nbsp;<input type="text" name="createDateEnd"   class="input-small" readonly="readonly" value="${paramMap.createDateEnd}"   id="endDatePicker" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatePicker\')}'})">		
		<label><spring:message code='str825'/> <spring:message code='str4'/></label>
			<select name="operateType">
					<option value=""><spring:message code='str627'/></option>
				<c:forEach items="${fns:getOperaTypeMap()}" var="item">
					<option value="${item.key}"
						<c:if test="${item.key==paramMap.operateType}">selected="selected"</c:if>
					>${item.value.chDes}</option>
			 	</c:forEach>
			</select>
		&nbsp;
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
		<shiro:hasPermission name="log.upgrade.export">
		<input id="btnExport" class="btn btn-primary" type="button" value="<spring:message code='str5'/>" onclick="exportXls('${ctx}/log/upgradeLog/exportXls')"/>
		</shiro:hasPermission>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr><th><spring:message code='str7'/></th>	<th><spring:message code='str889'/></th>	<th><spring:message code='str116'/></th>	<th><spring:message code='str1092'/></th>	<th><spring:message code='str930'/></th>
				<th><spring:message code='str1093'/></th>	<th><spring:message code='str1094'/></th>	<th><spring:message code='str3'/></th>	
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${upgradeLog.list}" var="item">
			<tr>
				<td>${item.role_name}</td>
				<td>${fns:getOperationType(item.operation_type)}</td>
				<td>${item.equip_name}</td>
				<td>${item.item_name}</td>
				<td>${item.num}</td>
				<td>${item.before_level}</td>
				<td>${item.after_level}</td>
				<td>${item.log_time}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${upgradeLog}</div>
	
	<script type="text/javascript">
		$(document).ready(function(){
			$('table.highchart').highchartTable();
		});
	</script>

</body>
</html>
