<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str470'/></title>
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
	<style type="text/css">
		span.input-group-btn {
			display: none;
		}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/global/openServiceIncome/openServiceIncomeMany"><spring:message code='str470'/> (<spring:message code='str471'/>)</a></li>
		<li><a href="${ctx}/global/openServiceIncome/openServiceIncomeSingle"><spring:message code='str470'/> (<spring:message code='str472'/>)</a></li>
	</ul>
	<form id="searchForm" action="${ctx}/global/openServiceIncome/openServiceIncomeMany" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		
		<label><spring:message code='str3'/> <spring:message code='str4'/></label>
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
		<tr><th><spring:message code='str198'/></th>	<th><spring:message code='str56'/></th>	<th><spring:message code='str473'/></th>	<th><spring:message code='str474'/></th>	<th><spring:message code='str475'/></th>
			<th><spring:message code='str476'/></th>	<th><spring:message code='str477'/></th>	<th><spring:message code='str476'/></th>	<th><spring:message code='str478'/></th>	<th><spring:message code='str476'/></th>
			<th><spring:message code='str479'/></th>	<th><spring:message code='str476'/></th>	<th><spring:message code='str480'/></th>	<th><spring:message code='str476'/></th>	<th><spring:message code='str481'/></th>
			<th><spring:message code='str476'/></th>	<th><spring:message code='str482'/></th>	<th><spring:message code='str476'/></th>	<th><spring:message code='str483'/></th>	<th><spring:message code='str476'/></th></tr>
		</thead>
		<tbody>
		<c:forEach items="${openManyServiceIncome.list}" var="item">
			<tr>
				<td>${createDateStart}-${createDateEnd}</td>
				<td>${fns:getGamePlatformNameById(item.pid,item.pid)}</td>
				<td>${item.sum_pv}</td>
				<td>${item.cpa_cost}</td>
				<td>${item.income_1}</td>

				<td <c:if test="${item.income_back_rate1 > 100 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber value="${item.income_back_rate1}" pattern="0.00"></fmt:formatNumber>%</td>
				<td>${item.income_3}</td>
				<td <c:if test="${item.income_back_rate3 > 100 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber value="${item.income_back_rate3}" pattern="0.00"></fmt:formatNumber>%</td>
				<td>${item.income_7}</td>
				<td <c:if test="${item.income_back_rate7 > 100 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber value="${item.income_back_rate7}" pattern="0.00"></fmt:formatNumber>%</td>

				<td>${item.income_10}</td>
				<td <c:if test="${item.income_back_rate10 > 100 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber value="${item.income_back_rate10}" pattern="0.00"></fmt:formatNumber>%</td>
				<td>${item.income_14}</td>
				<td <c:if test="${item.income_back_rate14 > 100 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber value="${item.income_back_rate14}" pattern="0.00"></fmt:formatNumber>%</td>
				<td>${item.income_30}</td>

				<td <c:if test="${item.income_back_rate1 > 100 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber value="${item.income_back_rate30}" pattern="0.00"></fmt:formatNumber>%</td>
				<td>${item.income_60}</td>
				<td <c:if test="${item.income_back_rate60 > 100 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber value="${item.income_back_rate60}" pattern="0.00"></fmt:formatNumber>%</td>
				<td>${item.income_90}</td>
				<td <c:if test="${item.income_back_rate90 > 100 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber value="${item.income_back_rate90}" pattern="0.00"></fmt:formatNumber>%</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${openManyServiceIncome}</div>

</body>
</html>
