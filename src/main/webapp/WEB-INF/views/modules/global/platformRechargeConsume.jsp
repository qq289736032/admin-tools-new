<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str502'/></title>
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
		<li class="active"><a href="${ctx}/global/platformRechargeConsume/platformRechargeConsume"><spring:message code='str503'/> </a></li>
		<li><a href="${ctx}/global/platformRechargeConsume/platformRechargeTimePeriod"><spring:message code='str504'/></a></li>
		<li><a href="${ctx}/global/platformRechargeConsume/platformConsume"><spring:message code='str505'/></a></li>
		<li><a href="${ctx}/global/platformRechargeConsume/platformConsumeTimePeriod"><spring:message code='str506'/> </a></li>
		<li><a href="${ctx}/global/platformRechargeConsume/platformConsumeBandingTimePeriod"><spring:message code='str507'/> </a></li>
	</ul>
	<form id="searchForm" action="${ctx}/global/platformRechargeConsume/platformRechargeConsume" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		
		<input type="text" name="createDateStart" class="input_search" size="10" readonly="readonly" maxlength="20" value="${paramMap.createDateStart}" id="startDatePicker" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDatePicker\')}'})">
		-&nbsp;<input type="text" name="createDateEnd"   class="input_search" size="10" readonly="readonly" value="${paramMap.createDateEnd}"   id="endDatePicker" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatePicker\')}'})">
		<label><spring:message code='str56'/> <spring:message code='str4'/></label>
		<!-- <spring:message code='str344'/> -->
		<select id="pids" name="pids" multiple="multiple">
			<c:forEach items="${fns:getGamePlatformList()}" var="item">
				<c:choose>
					<c:when test="${fn:length(selectedPids)==0}">
						<option value="${item.pid}" >${item.name}</option>
					</c:when>
					<c:otherwise>
					<option value="${item.pid}" 
						<c:forEach items="${selectedPids}" var="pid">
							<c:if test="${pid==item.pid}">
								selected="selected"
							</c:if>
						</c:forEach>
					>${item.name}</option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
		<tr><th><spring:message code='str198'/></th>	<th><spring:message code='str56'/></th>	<th><spring:message code='str218'/></th>	<th><spring:message code='str253'/></th>	<th><spring:message code='str529'/></th>
			<th><spring:message code='str205'/></th>	<th>ARPU</th>	<th><spring:message code='str220'/>ARPU</th>	<th><spring:message code='str530'/></th>	<th><spring:message code='str242'/></th>
			<th><spring:message code='str243'/></th>	<th><spring:message code='str244'/>ARPU</th>	<th><spring:message code='str531'/></th>	<th><spring:message code='str246'/></th>	<th><spring:message code='str247'/></th>
			<th><spring:message code='str248'/>ARPU</th>	<th><spring:message code='str532'/></th>	<th><spring:message code='str373'/></th>	<th><spring:message code='str374'/></th>	<th><spring:message code='str375'/>ARPU</th>
		</tr>
		</thead>
		<tbody>
			<c:forEach items="${platformRecharge.list}" var="item">
				<tr>
					<td>${item.log_day}</td>
					<td>${fns:getGamePlatformNameById(item.pid,item.pid)}</td>
					<td>${item.dau}</td>
					<td>${item.income}</td>
					<td>${item.dpa}</td>
					
					<td <c:if test="${item.dpa_dau > 1 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber pattern="#0.00" value="${item.dpa_dau*100} "/>%</td>
					<td><fmt:formatNumber value="${item.income_dpa}" pattern="#0.00" /></td>
					<td><fmt:formatNumber value="${item.income_dau}" pattern="#0.00" /></td>
					<td>${item.nn_pa}</td>
					<td>${item.nn_pay_value}</td>
					
					<td <c:if test="${item.nn_pa_dau > 1 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber pattern="#0.00" value="${item.nn_pa_dau*100}"/>%</td>
					<td><fmt:formatNumber value="${item.nn_pay_value_nn_pa}" pattern="#0.00" /></td>
					<td>${item.on_pa}</td>
					<td>${item.on_pay_value}</td>
					<td <c:if test="${item.on_pa_dau > 1 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber pattern="#0.00" value="${item.on_pa_dau*100}"/>%</td>
					
					<td>${item.on_pay_value_on_pa}</td>
					<td>${item.oo_pa}</td>
					<td>${item.oo_pay_value}</td>
					<td <c:if test="${item.oo_pa_dau > 1 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber pattern="#0.00" value="${item.oo_pa_dau*100}"/>%</td>
					<td><fmt:formatNumber value="${item.oo_pay_value_oo_pa}" pattern="#0.00" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${platformRecharge}</div>

	
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

</body>
</html>
