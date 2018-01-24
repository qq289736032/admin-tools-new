<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str368'/></title>
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
		<li class="active"><a href="${ctx}/global/rechargeConsume/monthlyRecharge"><spring:message code='str368'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/global/rechargeConsume/monthlyRecharge" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

		<label><spring:message code='str3'/> <spring:message code='str4'/></label>
		<input type="text" name="createDateStart" class="input_search" size="10" readonly="readonly" maxlength="20" value="${paramMap.createDateStart}" id="startDatePicker" onfocus="WdatePicker({dateFmt:'yyyy-MM',maxDate:'#F{$dp.$D(\'endDatePicker\')}'})">
		-&nbsp;<input type="text" name="createDateEnd"   class="input_search" size="10" readonly="readonly" value="${paramMap.createDateEnd}"   id="endDatePicker" onfocus="WdatePicker({dateFmt:'yyyy-MM',minDate:'#F{$dp.$D(\'startDatePicker\')}'})">
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
			
	<div class=" panel panel-primary">
		<div class="panel-heading"><spring:message code='str368'/></div>
		<table id="contentTable1" class="datatable table table-striped table-bordered table-condensed" style="overflow: scroll">
			<thead>
			<tr>
			<th><spring:message code='str369'/></th>
			<th><spring:message code='str234'/></th>
			<th><spring:message code='str201'/></th>
			<th><spring:message code='str217'/></th>
			<th><spring:message code='str200'/></th>
			<th><spring:message code='str348'/></th>
			<th><spring:message code='str209'/></th>
			<th><spring:message code='str275'/></th>
			<th><spring:message code='str205'/></th>
			<th>ARPU</th>
			<th><spring:message code='str220'/>ARPU</th>
			<th><spring:message code='str239'/></th>
			<th><spring:message code='str370'/></th>
			<th><spring:message code='str240'/></th>
			<th><spring:message code='str241'/></th>
			<th><spring:message code='str277'/></th>
			<th><spring:message code='str242'/></th>
			<th><spring:message code='str243'/></th>
			<th><spring:message code='str244'/>ARPU</th>
			<th><spring:message code='str371'/></th>
			<th><spring:message code='str372'/></th>
			<th><spring:message code='str373'/></th>
			<th><spring:message code='str374'/></th>
			<th><spring:message code='str375'/>ARPU</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${rechargeList}" var="item">
				<tr>
					<td>${item.log_month}</td>
					<td>${item.kaifuNum}</td>
					<td><fmt:formatNumber value="${item.income/100}" pattern="0.00"/></td>
					<td>${item.ru}</td>
					<td>${item.au}</td>
					<td>${item.ou}</td>
					<td>${item.pa}</td>
					<td>${item.pay_times}</td>
					<td <c:if test="${item.pay_rate > 1 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber value="${item.pay_rate * 100}" pattern="0.00"/>%</td>
					<td><fmt:formatNumber value="${item.arpu/100}" pattern="0.00"/></td>
					<td><fmt:formatNumber value="${item.active_arpu/100}" pattern="0.00"/></td>
					<td>${item.first_pay_user}</td>
					<td>${item.first_pay_times}</td>
					<td><fmt:formatNumber value="${item.first_pay_value/100}" pattern="0.00"/></td>
					<td>${item.mnn_pa}</td>
					<td>${item.nn_pay_times}</td>
					<td><fmt:formatNumber value="${item.nn_pay_value/100}" pattern="0.00"/></td>
					<td <c:if test="${item.nn_pay_rate > 1 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber value="${item.nn_pay_rate * 100}" pattern="0.00"/>%</td>
					<td><fmt:formatNumber value="${item.nn_arpu/100}" pattern="0.00"/></td>
					<td>${item.d_n_pa}</td>
					<td>${item.df_times}</td>
					<td><fmt:formatNumber value="${item.i_f_pay_value/100}" pattern="0.00"/></td>
					<td <c:if test="${item.on_pay_rate > 1 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber value="${item.on_pay_rate * 100}" pattern="0.00"/>%</td>
					<td><fmt:formatNumber value="${item.on_arpu/100}" pattern="0.00"/></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	
	</div>
	
	<div class=" panel panel-primary" >
		<div class="panel-heading"><spring:message code='str376'/></div>
		<table id="contentTable4" class="datatable table table-striped table-bordered table-condensed" style="overflow: scroll">
			<thead>
			<tr><th><spring:message code='str369'/></th><th><spring:message code='str351'/></th><th><spring:message code='str352'/></th><th><spring:message code='str377'/></th><th><spring:message code='str378'/></th><th><spring:message code='str379'/></th><th><spring:message code='str355'/></th><th><spring:message code='str356'/>ARPU</th>
			<th><spring:message code='str357'/></th><th><spring:message code='str380'/></th><th><spring:message code='str381'/></th><th><spring:message code='str382'/></th><th><spring:message code='str360'/></th><th><spring:message code='str361'/>ARPU</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${newOldList}" var="item">
				<tr>
					<td>${item.log_month}</td>
					<td><fmt:formatNumber value="${item.income/100}" pattern="0.00"/></td>
					<td>${item.new_au}</td>
					<td>${item.new_pa}</td>
					<td>${item.new_pay_times}</td>
					<td><fmt:formatNumber value="${item.new_income/100}" pattern="0.00"/></td>
					<td <c:if test="${item.new_pay_rate > 1 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber value="${item.new_pay_rate * 100}" pattern="0.00"/>%</td>
					<td><fmt:formatNumber value="${item.new_arpu/100}" pattern="0.00"/></td>
					<td>${item.old_au}</td>
					<td>${item.old_pa}</td>
					<td>${item.old_pay_times}</td>
					<td><fmt:formatNumber value="${item.old_income/100}" pattern="0.00"/></td>
					<td<c:if test="${item.old_pay_rate > 1 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber value="${item.old_pay_rate * 100}" pattern="0.00"/>%</td>
					<td><fmt:formatNumber value="${item.old_arpu/100}" pattern="0.00"/></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	
	<div class=" panel panel-primary">
		<div class="panel-heading"><spring:message code='str301'/></div>
		<table id="contentTable" class="datatable table table-striped table-bordered table-condensed" style="overflow: scroll">
			<thead>
			<tr>
			<th><spring:message code='str369'/></th>
			<th><spring:message code='str56'/></th>
			<th><spring:message code='str200'/></th>
			<th><spring:message code='str238'/></th>
			<th><spring:message code='str275'/></th>
			<th><spring:message code='str253'/></th>
			<th><spring:message code='str205'/></th>
			<th>ARPU</th>
			<th><spring:message code='str220'/>ARPU</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${platformList}" var="item">
				<tr>
					<td>${item.log_month}</td>
					<td>${fns:getGamePlatformNameById(item.pid,item.pid)}</td>
					<td>${item.au}</td>
					<td>${item.pa}</td>
					<td>${item.pay_times}</td>
					<td><fmt:formatNumber value="${item.income/100}" pattern="0.00"/></td>
					<td <c:if test="${item.pay_rate > 1 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber value="${item.pay_rate * 100}" pattern="0.00"/>%</td>
					<td><fmt:formatNumber value="${item.arpu/100}" pattern="0.00"/></td>
					<td><fmt:formatNumber value="${item.active_arpu/100}" pattern="0.00"/></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
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
