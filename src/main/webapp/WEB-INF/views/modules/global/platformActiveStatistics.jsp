﻿﻿<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str485'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/highcharts.jsp" %>
	<script type="text/javascript" src="${ctxStatic}/highcharts/data.js"></script>
	<style type="text/css">
		span.input-group-btn {
			display: none;
		}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/global/platformStatistics/platformActiveStatistics"><spring:message code='str485'/></a></li>
		<li><a href="${ctx}/global/platformStatistics/platformActiveStatisticsList">平台活跃统计（去新活跃）</a></li>
	
	</ul>
	<form id="searchForm" action="${ctx}/global/platformStatistics/platformActiveStatistics" method="post" class="breadcrumb form-search">
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

	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span4 panel panel-primary">
				<div class="panel-heading"><spring:message code='str485'/></div>
				<div style="height: 500px;line-height: 300px">
					<div class="highchart-au-pie" style="height: 300px"></div>
					<table class="highchart table"
						   data-graph-container=".highchart-au-pie" data-graph-type="pie" data-graph-pie-show-in-legend="1" data-graph-datalabels-enabled="1" style="display: none">
						<caption><spring:message code='str486'/></caption>
						<thead>
						<tr>
							<th data-graph-type="pie"><spring:message code='str56'/></th>
							<th data-graph-type="pie"><spring:message code='str226'/></th>
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
			</div>

			<div class="span8 panel panel-primary">
				<div class="panel-heading"><spring:message code='str485'/>-<spring:message code='str487'/></div>
				<div class="" style="width:100%;height:500px;line-height:300px;overflow:scroll;">
				<table id="platformActiveSTAT" class="table table-striped table-bordered table-condensed">
					<tr><th><spring:message code='str187'/></th><th><spring:message code='str230'/></th><th><spring:message code='str232'/></th><th><spring:message code='str226'/></th><th><spring:message code='str488'/></th><th><spring:message code='str489'/></th>
					<th><spring:message code='str490'/></th><th><spring:message code='str226'/></th><th><spring:message code='str488'/></th><th><spring:message code='str489'/></th>
					</tr>
					<c:forEach items="${activeStatisticsList}" var="item">
						<tr>
							<td>${fn:substring(paramMap.createDateStart,"5","10")}~${fn:substring(paramMap.createDateEnd,"5","10")}</td>
							<td>${fns:getGamePlatformNameById(item.pid,item.pid)}</td>
							<td>${item.au}</td>
							<td <c:if test="${item.p_au>1 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber  value="${item.p_au*100}" pattern="#0.00"/>%</td>
							<td <c:if test="${item.p_au_before>1 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber  value="${item.p_au_before*100}" pattern="#0.00"/>%</td>
							<td <c:if test="${item.p_au_after>1 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber  value="${item.p_au_after*100}" pattern="#0.00"/>%</td>
							<td>${item.old_au}</td>
							<td <c:if test="${item.p_old_au>1 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber  value="${item.p_old_au*100}" pattern="#0.00"/>%</td>
							<td <c:if test="${item.p_old_au_before>1 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber  value="${item.p_old_au_before*100}" pattern="#0.00"/>%</td>
							<td <c:if test="${item.p_old_au_after>1 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber  value="${item.p_old_au_after*100}" pattern="#0.00"/>%</td>
						</tr>
					</c:forEach>
				</table>
				</div>
			</div>
		</div>
	</div>



	<%--<table id="contentTable" class="table table-striped table-bordered table-condensed">--%>
		<%--<tr><th><spring:message code='str198'/></th><th><spring:message code='str234'/></th><th><spring:message code='str201'/></th><th><spring:message code='str199'/></th><th><spring:message code='str200'/></th><th><spring:message code='str235'/></th><th>ACCU</th><th>PCCU</th><th>DT<spring:message code='str236'/>(<spring:message code='str237'/>)</th><th><spring:message code='str238'/></th>--%>
			<%--<th><spring:message code='str205'/></th><th>ARPU</th><th><spring:message code='str220'/>ARPU</th><th><spring:message code='str239'/></th><th><spring:message code='str240'/></th><th><spring:message code='str241'/></th><th><spring:message code='str242'/></th><th><spring:message code='str244'/><spring:message code='str205'/></th><th><spring:message code='str244'/>ARPU</th>--%>
			<%--<th><spring:message code='str245'/></th><spring:message code='str246'/><th><spring:message code='str247'/></th><th><spring:message code='str248'/>ARPU</th><th><spring:message code='str245'/></th><th><spring:message code='str249'/></th><th><spring:message code='str250'/></th><th><spring:message code='str251'/>ARPU</th></tr>--%>
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
		$('#container').highcharts({
			data: {
				table: document.getElementById('datatable')
			},
			chart: {
				type: 'column'
			},
			title: {
				text: '<spring:message code='str493'/>(<spring:message code='str490'/>)'
			},
			yAxis: {
				allowDecimals: false,
				title: {
					text: '<spring:message code='str501'/>'
				}
			},
			tooltip: {
				formatter: function() {
					return '<b>'+ this.series.name +'</b><br/>'+
						this.y +' '+ this.x;
				}
			},
			plotOptions: {
				column: {
					stacking: 'percent'
				}
			}
		});
	});
</script>

</body>
</html>
