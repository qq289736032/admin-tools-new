<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str651'/></title>
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
		<li><a href="${ctx}/log/moneyConsumeLog/moneyConsumeLogReport"><spring:message code='str651'/></a></li>
		<li class="active"><a href="${ctx}/log/moneyConsumeLog/dailyOperationConsumeReport"><spring:message code='str887'/>(<spring:message code='str825'/>)<spring:message code='str851'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/log/moneyConsumeLog/dailyOperationConsumeReport" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label><spring:message code='str653'/> <spring:message code='str4'/></label>
		<input id="roleName" name="roleName" type="text" value="${roleName}"/>
		
		<label><spring:message code='str888'/> <spring:message code='str4'/></label>
			<select name="moneyType">
					<option value=""><spring:message code='str627'/></option>
			 	<c:forEach items="${fns:getDictList('money_type')}" var="item">
					<option value="${item.value}"
						<c:if test="${item.value==paramMap.moneyType}">selected="selected"</c:if>
					><spring:message code="${item.internationalKey}"/></option>
			 	</c:forEach>
			</select>
		<label><spring:message code='str654'/> <spring:message code='str4'/></label>
			<select name="operateType">
					<option value=""><spring:message code='str627'/></option>
				<c:forEach items="${fns:getOperaTypeMap()}" var="item">
					<option value="${item.key}"
						<c:if test="${item.key==paramMap.operateType}">selected="selected"</c:if>
					>${item.value.chDes }</option>
			 	</c:forEach>
			</select>
		&nbsp;
		<label><spring:message code='str185'/></label>

		<input type="text" name="createDateStart" class="input-small" readonly="readonly" maxlength="20" value="${paramMap.createDateStart}" id="startDatePicker" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'endDatePicker\',{d:-15})}',maxDate:'#F{$dp.$D(\'endDatePicker\')}'})">
		-&nbsp;<input type="text" name="createDateEnd"   class="input-small" readonly="readonly" value="${paramMap.createDateEnd}"   id="endDatePicker" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatePicker\')}'})"> 

		<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
		<%--<shiro:hasPermission name="log.moneyConsume.export">--%>
		<%--<input id="btnExport" class="btn btn-primary" type="button" value="<spring:message code='str5'/>" onclick="exportXls('${ctx}/log/moneyConsumeLog/exportXls')"/>--%>
		<%--</shiro:hasPermission>--%>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
		<tr><th><spring:message code='str654'/></th>	<th><spring:message code='str93'/></th>	<th><spring:message code='str94'/></th>
			<th><spring:message code='str896'/></th>	</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="item">
			<tr>
				<td>${fns:getOperationType(item.operate_type)}</td>
				<td>${item.yb}</td>
				<td>${item.bdyb}</td>
				<td>${item.jb}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	 <div class="alert">
		 <button type="button" class="close"  data-dismiss="alert">&times;</button>
		 <strong>Warning!</strong> <spring:message code='str893'/>,<spring:message code='str727'/>15<spring:message code='str897'/>
	 </div>
	<script type="text/javascript">
		$(document).ready(function(){
			$('table.highchart').highchartTable();
		});
	</script>

</body>
</html>
