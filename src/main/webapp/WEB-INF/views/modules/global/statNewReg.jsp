<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str607'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/highcharts.jsp" %>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/global/newRegisterStatistics/newRegisterStatisticsReport"><spring:message code='str607'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/global/newRegisterStatistics/newRegisterStatisticsReport" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

		<label><spring:message code='str3'/> <spring:message code='str4'/></label>
		<input type="text" name="createDateStart" class="input-small" readonly="readonly" maxlength="20" value="${paramMap.createDateStart}" id="startDatePicker" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDatePicker\')}'})">
		-&nbsp;<input type="text" name="createDateEnd"   class="input-small" readonly="readonly" value="${paramMap.createDateEnd}"   id="endDatePicker" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatePicker\')}'})">

		<label><spring:message code='str55'/></label>
	    <input id="serverIds" name="serverIds" value="${paramMap.serverIds}" readonly="readonly"/>
		<%--<input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="openSeverDialog();">--%>
		<input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="showContent('${ctx}/tools/gameArea/openGameServer')">
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
	</form>
	<tags:message content="${message}"/>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span6 panel panel-primary">
			<div class="panel-heading"><spring:message code='str619'/> </div>
			<div class="" style="width:100%;height:300px;line-height:300px;overflow:scroll;">
			<table id="newRegister" class="table table-striped table-bordered table-condensed">
				<thead>
				<tr><th><spring:message code='str198'/></th> <th><spring:message code='str3'/></th> <th><spring:message code='str620'/></th>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${newRegister}" var="item">
					<tr>
						<td>${item.day}</td>
						<td>${item.hour}</td>
						<td>${item.num}</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
			</div>
			</div>

			<div class="span6 panel panel-primary">
			<div class="panel-heading"><spring:message code='str621'/></div>
			<div class="" style="width:100%;height:300px;line-height:300px;overflow:auto;overflow-x:hidden;">
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<thead>
				<tr><th><spring:message code='str198'/></th><th><spring:message code='str230'/> </th><th><spring:message code='str14'/> </th><th><spring:message code='str561'/> </th><th><spring:message code='str622'/> </th>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${serverNewRegister}" var="item">
					<tr>
						<td>${item.day}</td>
						<td>${fns:getGamePlatformNameById(item.pid,item.pid)}</td>
						<td>${item.serverId}</td>
						<td>${fns:getGameServer(item.serverId).name}</td>
						<td>${item.num}</td>
					</tr>
				</c:forEach>
				<tr>
					<td style="font-weight: bolder;"><spring:message code='str193'/>:</td>
					<td>${serverNum }</td>
					<td>${serverNum }</td>
					<td>${serverNum }</td>
					<td>${totalNewRegNum}</td>
				</tr>
				</tbody>
			</table>
			</div>
			</div>
		</div>
	</div>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span6 panel panel-primary">
				<div class="panel-heading"><spring:message code='str623'/></div>
				<div style="height: 300px;line-height: 300px">
					<div  class="highchart-daysActive" style="height: 300px"></div>
					<table class="highchart table"
						   data-graph-container=".highchart-daysActive" data-graph-type="spline"
						   data-graph-yaxis-1-title-text = '<spring:message code='str462'/>' data-graph-zoom-type="xy" style="display: none">
						<caption><spring:message code='str624'/></caption>
						<thead>
						<tr>
							<th><spring:message code='str3'/></th>
							<th data-graph-type="line">${beforeStart } ~ ${beforeEnd }</th>
							<th data-graph-type="line">${createDateStart} ~ ${createDateEnd }</th>
						</tr>
						</thead>
						<tbody>
							<c:forEach items="${compare}" var="item">
								<tr>
									<td>${fn:substring(item.log_day,1,3)}</td>
									<td>${item.before}</td>
								    <td>${item.after}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div> 
	
	<script type="text/javascript">
		$(document).ready(function(){
			$('table.highchart').highchartTable();
		});
	</script>

</body>
</html>
