<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str515'/></title>
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
		<li class="active"><a href="${ctx}/global/platformHistoryMonthData/platformHistoryMonthData"><spring:message code='str515'/> </a></li>
	</ul>
	<form id="searchForm" action="${ctx}/global/platformHistoryMonthData/platformHistoryMonthData" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		
		<label><spring:message code='str3'/> <spring:message code='str4'/></label>
		<input type="text" name="createDateStart" class="input_search" size="10" readonly="readonly" maxlength="20" value="${paramMap.createDateStart}" id="startDatePicker" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDatePicker\')}',dateFmt:'yyyy-MM'})">
		-&nbsp;<input type="text" name="createDateEnd"   class="input_search" size="10" readonly="readonly" value="${paramMap.createDateEnd}"   id="endDatePicker" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatePicker\')}',dateFmt:'yyyy-MM'})">
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
	<div class="container-fluid">
		<div class="panel panel-primary">
			<div class="panel-heading"><spring:message code='str516'/></div>
			<div class="" style="width:100%;height:300px;line-height:300px;overflow:scroll;">
		     <table id="contentTable1" class="table table-striped table-bordered table-condensed">
			<thead>
			<tr><th><spring:message code='str369'/></th>	<th><spring:message code='str230'/></th>	<th><spring:message code='str384'/></th>	<th><spring:message code='str385'/></th>	<th><spring:message code='str386'/></th>
				<th><spring:message code='str387'/></th>	<th>ACCU</th>	<th>PCCU</th>	<th>DT<spring:message code='str236'/>(<spring:message code='str237'/>)</th>	<th><spring:message code='str388'/></th>
				<th><spring:message code='str389'/></th>	<th><spring:message code='str390'/></th>	<th><spring:message code='str392'/></th>	<th><spring:message code='str393'/>ARPU</th>	<th><spring:message code='str387'/>ARPU</th>	
				<th><spring:message code='str394'/></th>	<th><spring:message code='str396'/></th>	<th><spring:message code='str397'/></th>	<th><spring:message code='str399'/></th>	<th><spring:message code='str400'/></th>	
				<th><spring:message code='str401'/>ARPU</th>	<th><spring:message code='str402'/></th>	<th><spring:message code='str404'/></th>	<th><spring:message code='str405'/></th>	<th><spring:message code='str406'/>ARPU</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${monthData}" var="item">
				<tr>
					<td>${item.log_month}</td>
					<td>${fns:getGamePlatformNameById(item.pid,item.pid)}</td>
					<td>${item.new_server_num}</td>
					<td><fmt:formatNumber value="${item.income}" pattern="0.00"></fmt:formatNumber></td>
					<td>${item.ru}</td>
					
					<td>${item.au}</td>
					<td><fmt:formatNumber value="${item.acu}" pattern="0.00"></fmt:formatNumber></td>
					<td>${item.pcu}</td>
					<td><fmt:formatNumber value="${item.dt/60000}" pattern="0.00"></fmt:formatNumber></td>
					<td>${item.ou}</td>
					
					<td <c:if test="${item.mr_r > 1}">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber pattern="0.00" value="${item.mr_r * 100}"/>%</td>
					<td>${item.pa}</td>
					<td <c:if test="${item.pa_au >1 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber pattern="0.00" value="${item.pa_au * 100}"/>%</td>
					<td><fmt:formatNumber value="${item.income_pa}" pattern="0.00"></fmt:formatNumber></td>
					<td><fmt:formatNumber value="${item.income_au}" pattern="0.00"></fmt:formatNumber></td>
					
					<td>${item.first_pay_user}</td>
					<td><fmt:formatNumber value="${item.first_pay_value}" pattern="0.00"></fmt:formatNumber></td>
					<td>${item.nn_pa}</td>
					<td><fmt:formatNumber value="${item.nn_pay_value}" pattern="0.00"></fmt:formatNumber></td>
					<td <c:if test="${item.nn_pa_ru >1 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber pattern="0.00" value="${item.nn_pa_ru * 100}"/>%</td>
					
					<td><fmt:formatNumber value="${item.nn_pay_value_nn_pa}" pattern="0.00"></fmt:formatNumber></td>
					<td>${item.ou_pa}</td>
					<td><fmt:formatNumber value="${item.ou_pay_value}" pattern="0.00"></fmt:formatNumber></td>
					<td <c:if test="${item.ou_pa_ou >1 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber pattern="0.00" value="${item.ou_pa_ou * 100}"/>%</td>
					<td><fmt:formatNumber value="${item.ou_pay_value_ou_pa}" pattern="0.00"></fmt:formatNumber></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		</div>
	</div>
	<div class="panel panel-primary">
		<div class="panel-heading"><spring:message code='str517'/> </div>
		<table id="contentTable" class="table table-striped table-bordered table-condensed">
			<thead>
			<tr><th><spring:message code='str369'/></th>	<th><spring:message code='str230'/></th>	<th><spring:message code='str518'/></th>	<th><spring:message code='str385'/></th>	<th><spring:message code='str386'/></th>
				<th><spring:message code='str387'/></th>	<th>ACCU</th>	<th>PCCU</th>	<th>DT<spring:message code='str236'/></th>	<th><spring:message code='str390'/></th>	
				<th><spring:message code='str392'/></th>	<th><spring:message code='str393'/>ARPU</th>	<th><spring:message code='str387'/>ARPU</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${newServerMonthData}" var="item">
				<tr>
					<td>${item.log_month}</td>
					<td>${fns:getGamePlatformNameById(item.pid,item.pid)}</td>
					<td>${item.new_server_num}</td>
					<td><fmt:formatNumber value="${item.income}" pattern="0.00"></fmt:formatNumber></td>
					<td>${item.ru}</td>
					
					<td>${item.au}</td>
					<td><fmt:formatNumber value="${item.acu}" pattern="0.00"></fmt:formatNumber></td>
					<td>${item.pcu}</td>
					<td><fmt:formatNumber value="${item.dt}" pattern="0.00"></fmt:formatNumber></td>
					<td>${item.pa}</td>
					
					<td ><fmt:formatNumber type="percent" value="${item.pa_au}"/></td>
					<td><fmt:formatNumber value="${item.income_pa}" pattern="0.00"></fmt:formatNumber></td>
					<td><fmt:formatNumber value="${item.income_au}" pattern="0.00"></fmt:formatNumber></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="panel panel-primary">
		<div class="panel-heading"><spring:message code='str519'/> </div>
		<table id="contentTable" class="table table-striped table-bordered table-condensed">
			<thead>
			<tr><th><spring:message code='str369'/></th>	<th><spring:message code='str230'/></th>	<th><spring:message code='str520'/></th>	<th><spring:message code='str385'/></th>	<th><spring:message code='str386'/></th>	
				<th><spring:message code='str387'/></th>	<th>ACCU</th>	<th>PCCU</th>	<th>DT<spring:message code='str236'/></th>	<th><spring:message code='str390'/></th>	
				<th><spring:message code='str392'/></th>	<th><spring:message code='str393'/>ARPU</th>	<th><spring:message code='str387'/>ARPU</th>	<th><spring:message code='str394'/></th>	<th><spring:message code='str395'/></th>	
				<th><spring:message code='str521'/></th>	<th><spring:message code='str522'/>ARPU</th>	<th><spring:message code='str402'/></th>	<th><spring:message code='str404'/></th>	<th><spring:message code='str405'/></th>	
				<th><spring:message code='str406'/>ARPU</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${oldServerMonthData}" var="item">
				<tr>
					<td>${item.log_month}</td>
					<td>${fns:getGamePlatformNameById(item.pid,item.pid)}</td>
					<td>${item.new_server_num}</td>
					<td><fmt:formatNumber value="${item.income}" pattern="0.00"></fmt:formatNumber></td>
					<td>${item.ru}</td>
					
					<td>${item.au}</td>
					<td><fmt:formatNumber value="${item.acu}" pattern="0.00"></fmt:formatNumber></td>
					<td>${item.pcu}</td>
					<td><fmt:formatNumber value="${item.dt}" pattern="0.00"></fmt:formatNumber></td>
					<td>${item.pa}</td>
					
					<td <c:if test="${item.pa_au >1 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber type="percent" value="${item.pa_au}"/></td>
					<td><fmt:formatNumber value="${item.income_pa}" pattern="0.00"></fmt:formatNumber></td>
					<td><fmt:formatNumber value="${item.income_au}" pattern="0.00"></fmt:formatNumber></td>
					<td>${item.first_pay_user}</td>
					<td><fmt:formatNumber value="${item.first_pay_value}" pattern="0.00"></fmt:formatNumber></td>
					
					<td <c:if test="${item.nn_pa_ru >1 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber type="percent" value="${item.nn_pa_ru}"/></td>
					<td><fmt:formatNumber value="${item.nn_pay_value_nn_pa}" pattern="0.00"></fmt:formatNumber></td>
					<td>${item.ou_pa}</td>
					<td><fmt:formatNumber value="${item.ou_pay_value}" pattern="0.00"></fmt:formatNumber></td>
					<td <c:if test="${item.ou_pa_ou >1 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber type="percent" value="${item.ou_pa_ou}"/></td>
					
					<td><fmt:formatNumber value="${item.ou_pay_value_ou_pa}" pattern="0.00"></fmt:formatNumber></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	</div>
	
<script type="text/javascript">
	$(document).ready(function(){
		$('table.highchart').highchartTable();
		$("#s2id_pids").hide();
		$("#pids").multiselect({
			includeSelectAllOption:true,
			enableFiltering:true
		});
	});
	function selectMonth() {  
		  WdatePicker({ dateFmt: 'yyyy-MM', isShowToday: false, isShowClear: false });  
	}  
</script>

</body>
</html>
