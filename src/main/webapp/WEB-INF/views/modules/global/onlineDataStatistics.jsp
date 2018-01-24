<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str446'/></title>
	<meta name="decorator" content="default"/>
    <%@include file="/WEB-INF/views/include/highcharts.jsp" %>
	<script src="${ctxStatic}/common/fixtable.js" type="text/javascript"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/global/onlinedata/onlineDataStatistics"><spring:message code='str446'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/global/onlinedata/onlineDataStatistics" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		
		<label><spring:message code='str185'/></label>
	     <input type="text" name="createDateStart" class="input_search" size="10" readonly="readonly" maxlength="20" value="${paramMap.createDateStart}" id="startDatePicker" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDatePicker\')}'})">
		-&nbsp;<input type="text" name="createDateEnd"   class="input_search" size="10" readonly="readonly" value="${paramMap.createDateEnd}"   id="endDatePicker" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatePicker\')}'})">
		<label><spring:message code='str55'/></label>
	    <input id="serverIds" name="serverIds" value="${paramMap.serverIdList}" readonly="readonly"/>
		<%--<input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="openSeverDialog();">--%>
		<input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="showContent('${ctx}/tools/gameArea/openGameServer')">
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
	</form>
	<tags:message content="${message}"/>
	 <div class="row-fluid">
	 <div class="span12 panel panel-primary">
				<div class="panel-heading"><spring:message code='str447'/></div>
				<div style="height: 300px;line-height: 300px">
					<div class="highchart-accupccu" style="height: 300px"></div>
					<table class="highchart table"
						   data-graph-container=".highchart-accupccu" data-graph-type="spline" data-graph-xaxis-reversed="1"
						   data-graph-yaxis-1-title-text = '<spring:message code='str448'/>'  style="display: none">
						<caption><spring:message code='str224'/></caption>
						<thead>
						<tr>
							<th><spring:message code='str198'/></th>
							<th><spring:message code='str203'/></th>
							<th><spring:message code='str204'/></th>
						</tr>
						</thead>
						<tbody>
						<c:forEach items="${list}" var="item">
							<tr>
								<td>${item.log_day}</td>
								<td>${item.avg}</td>
								<td>${item.hight}</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
</div>
	
		<div class="row-fluid">
			<div class="span12 panel panel-primary">
				<div class="panel-heading"><spring:message code='str449'/></div>
				<div class="" style="width:100%;height:300px;line-height:300px;overflow:scroll;">
				<table id="online-date" class="table table-striped table-bordered table-condensed ">
					<tr><th><spring:message code='str198'/></th><th><spring:message code='str204'/></th><th><spring:message code='str203'/></th><th><spring:message code='str450'/>(%)</th></tr>
					<c:forEach items="${list}" var="item">
						<tr>
							<td>${item.log_day}</td>
							<td>${item.hight}</td>
							<td>${item.avg}</td>
							<td>
							  <c:if test="${item.hight > 0}">
									<span style="<c:if test="${item.avg*100/item.hight >100  }">color: red;font-weight:bold</c:if>"><fmt:parseNumber value="${item.avg*100/item.hight}" pattern="#0.00"/>%</span>
							 </c:if>
							</td>
						</tr>
					</c:forEach>
				</table>
				</div>
			</div>
		</div>
	
<script type="text/javascript">
	$(document).ready(function(){
		$('table.highchart').highchartTable();
	});
</script>
</body>_
</html>
