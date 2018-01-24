<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str657'/></title>
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
		<li class="active"><a href="${ctx}/log/axpGetLog/axpGetLogReport">AXP<spring:message code='str658'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/log/axpGetLog/axpGetLogReport" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label><spring:message code='str653'/> <spring:message code='str4'/></label>
		<input id="roleName" name="roleName" type="text" value="${roleName}"/>
		<label><spring:message code='str3'/> <spring:message code='str4'/></label>
		<input type="text" name="createDateStart" class="input-small" readonly="readonly" maxlength="20" value="${paramMap.createDateStart}" id="startDatePicker" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
		<%---&nbsp;<input type="text" name="createDateEnd"   class="input-small" readonly="readonly" value="${paramMap.createDateEnd}"   id="endDatePicker" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatePicker\')}'})">		--%>
		<label><spring:message code='str654'/>：</label>
		<select name="operateType">
				<option value=""><spring:message code='str627'/></option>
				<c:forEach items="${fns:getOperaTypeMap()}" var="item">
					<option value="${item.key}"
						<c:if test="${item.key==paramMap.operateType}">selected="selected"</c:if>
					>${item.value.chDes }</option>
			 </c:forEach>
		</select>
	
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
		<shiro:hasPermission name="log.moneyGain.export">
		<input id="btnExport" class="btn btn-primary" type="button" value="<spring:message code='str5'/>" onclick="exportXls('${ctx}/log/axpGetLog/exportXls')"/>
		</shiro:hasPermission>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
		<tr><th><spring:message code='str7'/></th>	<th><spring:message code='str654'/></th>	<th>AXP<spring:message code='str659'/></th>	<th>AXP<spring:message code='str656'/></th>	<th><spring:message code='str3'/></th></tr>
		</thead>
		<tbody>
		<c:forEach items="${axpGetLog.list}" var="item">
			<tr>
				<td>${item.role_name}</td>
				<td>${fns:getOperationType(item.operate_type)}</td>
				<td>${item.value}</td>
				<td>${item.curr_value}</td>
				<td>${item.logTime}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${axpGetLog}</div>
	
	<script type="text/javascript">
		$(document).ready(function(){
			$('table.highchart').highchartTable();
		});
	</script>

</body>
</html>
