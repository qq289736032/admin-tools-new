<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str516'/></title>
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
		<li><a href="${ctx}/global/platformStatistics/platformStatistics"><spring:message code='str516'/></a></li>
		<li  class="active"><a href="${ctx}/global/platformStatistics/platformStatisticsTwo"><spring:message code='str476'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/global/platformStatistics/platformStatisticsTwo" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

		<label><spring:message code='str3'/> <spring:message code='str4'/></label>
		<input type="text" name="createDateStart" class="input_search" size="10" readonly="readonly" maxlength="20" value="${paramMap.createDateStart}" id="startDatePicker" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDatePicker\')}'})">
		-&nbsp;<input type="text" name="createDateEnd"   class="input_search" size="10" readonly="readonly" value="${paramMap.createDateEnd}"   id="endDatePicker" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatePicker\')}'})">
		<label><spring:message code='str56'/> <spring:message code='str4'/></label>
		<!-- <spring:message code='str315'/> -->
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

<%-- 	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span6 panel panel-primary">
				<div class="panel-heading"><spring:message code='str474'/></div>
				<div style="height: 300px;line-height: 300px">
					<div class="highchart-income-pie" style="height: 300px"></div>
					<table class="highchart table"
						   data-graph-container=".highchart-income-pie" data-graph-type="pie" data-graph-pie-show-in-legend="1" data-graph-datalabels-enabled="1" style="display: none">
						<caption><spring:message code='str517'/></caption>
						<thead>
						<tr>
							<th data-graph-type="pie"><spring:message code='str56'/></th>
							<th data-graph-type="pie"><spring:message code='str201'/></th>
						</tr>
						</thead>
						<tbody>
						<c:forEach items="${statisticsList}" var="item">
							<tr>
								<td>${fns:getGamePlatformNameById(item.pid,item.pid)}</td>
								<td data-graph-name="${fns:getGamePlatformNameById(item.pid,item.pid)} : <fmt:formatNumber type="percent" value="${item.p_income}"/>"><fmt:formatNumber type="percent" value="${item.p_income}"/></td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			
			<div class="span6 panel panel-primary">
				<div class="panel-heading"><spring:message code='str474'/>-<spring:message code='str458'/></div>
				<div class="" style="width:100%;height:300px;line-height:300px;overflow:scroll;">
				<table id="dailyTable" class="table table-striped table-bordered table-condensed">
					<tr><th><spring:message code='str162'/></th><th><spring:message code='str205'/></th><th><spring:message code='str518'/></th><th><spring:message code='str201'/></th><th><spring:message code='str459'/></th><th><spring:message code='str460'/></th></tr>
					<c:forEach items="${statisticsList}" var="item">
						<tr>
							<td>${fn:substring(paramMap.createDateStart,"5","10")}~${fn:substring(paramMap.createDateEnd,"5","10")}</td>
							<td>${fns:getGamePlatformNameById(item.pid,item.pid)}</td>
							<td>${item.income}</td>
							<td <c:if test="${item.p_income > 1 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber  value="${item.p_income*100}" pattern="#0.00"/>%</td>
							<td <c:if test="${item.p_income_before > 1 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber  value="${item.p_income_before*100}" pattern="#0.00"/>%</td>
							<td <c:if test="${item.p_income_after > 1 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber  value="${item.p_income_after*100}" pattern="#0.00"/>%</td>
						</tr>
					</c:forEach>
				</table>
				</div>
			</div>
		</div>

		<%-- <div class="row-fluid">
			<div class="span6 panel panel-primary">
				<div class="panel-heading"><spring:message code='str519'/></div>
				<div style="height: 300px;line-height: 300px">
					<div class="highchart-dru-pie" style="height: 300px"></div>
					<table class="highchart table"
						   data-graph-container=".highchart-dru-pie" data-graph-type="pie" data-graph-pie-show-in-legend="1" data-graph-datalabels-enabled="1" style="display: none">
						<caption><spring:message code='str520'/></caption>
						<thead>
						<tr>
							<th data-graph-type="pie"><spring:message code='str56'/></th>
							<th data-graph-type="pie"><spring:message code='str201'/></th>
						</tr>
						</thead>
						<tbody>
						<c:forEach items="${statisticsList}" var="item">
							<tr>
								<td>${fns:getGamePlatformNameById(item.pid,item.pid)}</td>
								<td data-graph-name="${fns:getGamePlatformNameById(item.pid,item.pid)} : <fmt:formatNumber type="percent" value="${item.p_dru}"/>"><fmt:formatNumber type="percent" value="${item.p_dru}"/></td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</div>

			<div class="span6 panel panel-primary">
				<div class="panel-heading"><spring:message code='str519'/>-<spring:message code='str458'/></div>
				<div class="" style="width:100%;height:300px;line-height:300px;overflow:scroll;">
				<table id="dailyTable" class="table table-striped table-bordered table-condensed">
					<tr><th><spring:message code='str162'/></th><th><spring:message code='str205'/></th><th><spring:message code='str521'/></th><th><spring:message code='str522'/></th><th><spring:message code='str459'/></th><th><spring:message code='str460'/></th><th><spring:message code='str209'/></th><th><spring:message code='str523'/></th><th><spring:message code='str459'/></th><th><spring:message code='str460'/></th></tr>
					<c:forEach items="${statisticsList}" var="item">
						<tr>
							<td>${fn:substring(paramMap.createDateStart,"5","10")}~${fn:substring(paramMap.createDateEnd,"5","10")}</td>
							<td>${fns:getGamePlatformNameById(item.pid,item.pid)}</td>
							<td>${item.dru}</td>
							<td <c:if test="${item.p_dru > 1 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber  value="${item.p_dru*100}" pattern="#0.00"/>%</td>
							<td <c:if test="${item.p_dru_before > 1 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber  value="${item.p_dru_before*100}" pattern="#0.00"/>%</td>
							<td <c:if test="${item.p_dru_after > 1 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber  value="${item.p_dru_after*100}" pattern="#0.00"/>%</td>
							<td>${item.kaifu}</td>
							<td <c:if test="${item.p_kaifu > 1 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber  value="${item.p_kaifu*100}" pattern="#0.00"/>%</td>
							<td <c:if test="${item.p_kaifu_before > 1 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber  value="${item.p_kaifu_before*100}" pattern="#0.00"/>%</td>
							<td <c:if test="${item.p_kaifu_after > 1 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber  value="${item.p_kaifu_after*100}" pattern="#0.00"/>%</td>
						</tr>
					</c:forEach>
				</table>
				</div>
			</div> 
			
			
			
		</div>--%>
		
		<%-- <div class="row-fluid">
			<div class="span6 panel panel-primary">
				<div class="panel-heading"><spring:message code='str456'/></div>
				<div style="height: 300px;line-height: 300px">
					<div class="highchart-au-pie" style="height: 300px"></div>
					<table class="highchart table"
						   data-graph-container=".highchart-au-pie" data-graph-type="pie" data-graph-pie-show-in-legend="1" data-graph-datalabels-enabled="1" style="display: none">
						<caption><spring:message code='str457'/></caption>
						<thead>
						<tr>
							<th data-graph-type="pie"><spring:message code='str56'/></th>
							<th data-graph-type="pie"><spring:message code='str201'/></th>
						</tr>
						</thead>
						<tbody>
						<c:forEach items="${activeStatisticsList}" var="item">
							<tr>
								<td>${fns:getGamePlatformNameById(item.pid,item.pid)}</td>
								<td data-graph-name="${fns:getGamePlatformNameById(item.pid,item.pid)} : <fmt:formatNumber type="percent" value="${item.p_au}"/>"><fmt:formatNumber type="percent" value="${item.p_au}"/></td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</div> --%>

			<%-- <div class="span6 panel panel-primary">
				<div class="panel-heading"><spring:message code='str456'/>-<spring:message code='str458'/></div>
				<div class="" style="width:100%;height:300px;line-height:300px;overflow:scroll;">
				<table id="dailyTable" class="table table-striped table-bordered table-condensed">
					<tr><th><spring:message code='str162'/></th><th><spring:message code='str205'/></th><th><spring:message code='str207'/></th><th><spring:message code='str201'/></th><th><spring:message code='str459'/></th><th><spring:message code='str460'/></th></tr>
					<c:forEach items="${activeStatisticsList}" var="item">
						<tr>
							<td>${fn:substring(paramMap.createDateStart,"5","10")}~${fn:substring(paramMap.createDateEnd,"5","10")}</td>
							<td>${fns:getGamePlatformNameById(item.pid,item.pid)}</td>
							<td>${item.au}</td>
							<td <c:if test="${item.p_au > 1 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber  value="${item.p_au*100}" pattern="#0.00"/>%</td>
							<td <c:if test="${item.p_au_before > 1 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber  value="${item.p_au_before*100}" pattern="#0.00"/>%</td>
							<td <c:if test="${item.p_au_after > 1 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber  value="${item.p_au_after*100}" pattern="#0.00"/>%</td>
						</tr>
					</c:forEach>
				</table>
				</div>
			</div>
			
		</div> --%>
		
		
<%-- 		<div class="panel panel-primary">
		<div class="panel-heading"><spring:message code='str524'/> </div>
		<div class="" style="width:100%;height:300px;line-height:300px;overflow:auto;overflow-x:hidden;">
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<tr><th><spring:message code='str162'/></th><th><spring:message code='str205'/></th><th><spring:message code='str507'/></th><th><spring:message code='str525'/></th><th><spring:message code='str511'/></th><th><spring:message code='str459'/></th><th><spring:message code='str460'/></th></tr>
				<c:forEach items="${regConvertionList}" var="item">
					<tr>
						<td>${fn:substring(paramMap.createDateStart,"5","10")}~${fn:substring(paramMap.createDateEnd,"5","10")}</td>
						<td>${fns:getGamePlatformNameById(item.pid,item.pid)}</td>
						<td>${item.visit_times}</td>
						<td>${item.role_num}</td>
						<td <c:if test="${item.create_role_rate > 1 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber  value="${item.create_role_rate*100}" pattern="#0.00"/>%</td>
						<td <c:if test="${item.p_role_num_before > 1 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber  value="${item.p_role_num_before*100}" pattern="#0.00"/>%</td>
						<td><fmt:formatNumber  value="${item.p_role_num_after*100}" pattern="#0.00"/>%</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div> --%>
		
		 <div class="row-fluid">
			<div class="span6 panel panel-primary">
				<div class="panel-heading"><spring:message code='str476'/></div>
				<div style="height: 300px;line-height: 300px">
					<div class="highchart-consume-pie" style="height: 300px"></div>
					<table class="highchart table"
						   data-graph-container=".highchart-consume-pie" data-graph-type="pie" data-graph-pie-show-in-legend="1" data-graph-datalabels-enabled="1" style="display: none">
						<caption><spring:message code='str526'/></caption>
						<thead>
						<tr>
							<th data-graph-type="pie"><spring:message code='str56'/></th>
							<th data-graph-type="pie"><spring:message code='str201'/></th>
						</tr>
						</thead>
						<tbody>
						<c:forEach items="${statisticsList}" var="item">
							<tr>
								<td>${fns:getGamePlatformNameById(item.pid,item.pid)}</td>
								<td data-graph-name="${fns:getGamePlatformNameById(item.pid,item.pid)} : <fmt:formatNumber type="percent" value="${item.p_consume}"/>"><fmt:formatNumber type="percent" value="${item.p_consume}"/></td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</div>

			<div class="span6 panel panel-primary">
				<div class="panel-heading"><spring:message code='str476'/>-<spring:message code='str458'/></div>
				<div class="" style="width:100%;height:300px;line-height:300px;overflow:scroll;">
				<table id="dailyTable" class="table table-striped table-bordered table-condensed">
					<tr><th><spring:message code='str162'/></th><th><spring:message code='str205'/></th><th><spring:message code='str239'/></th><th><spring:message code='str201'/></th><th><spring:message code='str459'/></th><th><spring:message code='str460'/></th></tr>
					<c:forEach items="${statisticsList}" var="item">
						<tr>
							<td>${fn:substring(paramMap.createDateStart,"5","10")}~${fn:substring(paramMap.createDateEnd,"5","10")}</td>
							<td>${fns:getGamePlatformNameById(item.pid,item.pid)}</td>
							<td>${item.consume}</td>
							<td <c:if test="${item.p_consume > 1 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber  value="${item.p_consume*100}" pattern="#0.00"/>%</td>
							<td <c:if test="${item.p_consume_before > 1 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber  value="${item.p_consume_before*100}" pattern="#0.00"/>%</td>
							<td><fmt:formatNumber  value="${item.p_consume_after*100}" pattern="#0.00"/>%</td>
						</tr>
					</c:forEach>
				</table>
				</div>
			</div>
			
			
		</div>
		
		<div class="row-fluid">
			<div class="span6 panel panel-primary">
				<div class="panel-heading"><spring:message code='str527'/></div>
				<div style="height: 300px;line-height: 300px">
					<div  class="highchart-pay_rate" style="height: 300px"></div>
					<table class="highchart table"
						   data-graph-container=".highchart-pay_rate" data-graph-type="column" data-graph-xaxis-reversed="1"
						   data-graph-yaxis-1-title-text = '<spring:message code='str180'/>' data-graph-zoom-type="xy" style="display: none">
						<caption><spring:message code='str180'/></caption>
						<thead>
						<tr>
							<th><spring:message code='str56'/></th>
							<th data-graph-type="column"><spring:message code='str180'/></th>
						</tr>
						</thead>
						<tbody>
							<c:forEach items="${statisticsList}" var="item">
								<tr>
									<td>${fns:getGamePlatformNameById(item.pid,item.pid)}</td>
									<td><fmt:formatNumber  value="${item.pay_rate*100}" pattern="#0.00"/>%</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>

			<div class="span6 panel panel-primary">
				<div class="panel-heading"><spring:message code='str527'/>-<spring:message code='str458'/></div>
				<div class="" style="width:100%;height:300px;line-height:300px;overflow:auto;overflow-x:hidden;">
				<table id="dailyTable" class="table tableoverflow:scrollondensed">
					<tr><th><spring:message code='str162'/></th><th><spring:message code='str205'/></th><th><spring:message code='str180'/></th><th><spring:message code='str459'/></th><th><spring:message code='str460'/></th></tr>
					<c:forEach items="${statisticsList}" var="item">
						<tr>
							<td>${fn:substring(paramMap.createDateStart,"5","10")}~${fn:substring(paramMap.createDateEnd,"5","10")}</td>
							<td>${fns:getGamePlatformNameById(item.pid,item.pid)}</td>
							<td <c:if test="${item.pay_rate > 1 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber  value="${item.pay_rate*100}" pattern="#0.00"/>%</td>
							<td <c:if test="${item.p_pay_rate_before > 1 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber  value="${item.p_pay_rate_before*100}" pattern="#0.00"/>%</td>
							<td <c:if test="${item.p_pay_rate_after > 1 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber  value="${item.p_pay_rate_after*100}" pattern="#0.00"/>%</td>
						</tr>
					</c:forEach>
				</table>
				</div>
			</div>
		</div>
		
		<div class="row-fluid">
			<div class="span6 panel panel-primary">
				<div class="panel-heading"><spring:message code='str56'/>ARPU<spring:message code='str528'/></div>
				<div style="height: 300px;line-height: 300px">
					<div  class="highchart-ARPU" style="height: 300px"></div>
					<table class="highchart table"
						   data-graph-container=".highchart-ARPU" data-graph-type="column" data-graph-xaxis-reversed="1"
						   data-graph-yaxis-1-title-text = 'ARPU' data-graph-zoom-type="xy" style="display: none">
						<caption>ARPU</caption>
						<thead>
						<tr>
							<th><spring:message code='str56'/></th>
							<th data-graph-type="column">ARPU</th>
						</tr>
						</thead>
						<tbody>
							<c:forEach items="${statisticsList}" var="item">
								<tr>
									<td>${fns:getGamePlatformNameById(item.pid,item.pid)}</td>
									<td <c:if test="${item.arpu > 1 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber  value="${item.arpu*100}" pattern="#0.00"/>%</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>

			<div class="span6 panel panel-primary">
				<div class="panel-heading"><spring:message code='str56'/>ARPU<spring:message code='str528'/>-<spring:message code='str458'/></div>
				<div class="" style="width:100%;height:300px;line-height:300px;overflow:auto;overflow-x:hidden;">
				<table id="dailyTable" class="table table-striped table-bordered table-condensed">
					<tr><th><spring:message code='str162'/></th><th><spring:message code='str205'/></th><th>ARPU</th><th><spring:message code='str459'/></th><th><spring:message code='str460'/></th></tr>
					<c:forEach items="${statisticsList}" var="item">
						<tr>
							<td>${fn:substring(paramMap.createDateStart,"5","10")}~${fn:substring(paramMap.createDateEnd,"5","10")}</td>
							<td>${fns:getGamePlatformNameById(item.pid,item.pid)}</td>
							<td>${item.arpu}</td>
							<td <c:if test="${item.p_arpu_before > 1 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber  value="${item.p_arpu_before*100}" pattern="#0.00"/>%</td>
							<td <c:if test="${item.p_arpu_after > 1 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber  value="${item.p_arpu_after*100}" pattern="#0.00"/>%</td>
						</tr>
					</c:forEach>
				</table>
				</div>
			</div> 
		</div>
	</div>



	<%--<table id="contentTable" class="table table-striped table-bordered table-condensed">--%>
		<%--<tr><th><spring:message code='str173'/></th><th><spring:message code='str209'/></th><th><spring:message code='str176'/></th><th><spring:message code='str174'/></th><th><spring:message code='str175'/></th><th><spring:message code='str210'/></th><th>ACCU</th><th>PCCU</th><th>DT<spring:message code='str211'/>(<spring:message code='str212'/>)</th><th><spring:message code='str213'/></th>--%>
			<%--<th><spring:message code='str180'/></th><th>ARPU</th><th><spring:message code='str195'/>ARPU</th><th><spring:message code='str214'/></th><th><spring:message code='str215'/></th><th><spring:message code='str216'/></th><th><spring:message code='str217'/></th><th><spring:message code='str219'/><spring:message code='str180'/></th><th><spring:message code='str219'/>ARPU</th>--%>
			<%--<th><spring:message code='str220'/></th><spring:message code='str221'/><th><spring:message code='str222'/></th><th><spring:message code='str223'/>ARPU</th><th><spring:message code='str220'/></th><th><spring:message code='str224'/></th><th><spring:message code='str225'/></th><th><spring:message code='str226'/>ARPU</th></tr>--%>
		<%--<c:forEach items="${page.list}" var="item">--%>
			<%--<tr>--%>
 
			<%--</tr>--%>
		<%--</c:forEach>--%>
	<%--</table>--%>
	<%--<div class="pagination">${page}</div>--%>

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
