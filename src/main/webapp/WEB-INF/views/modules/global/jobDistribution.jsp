<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str335'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/highcharts.jsp" %>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/global/jobDistribution/list"><spring:message code='str335'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/global/jobDistribution/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

		<label><spring:message code='str3'/> <spring:message code='str4'/></label>
	    <input name="createDate" style="width: 216px" readonly="readonly" value="${paramMap.createDate}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
		
		<label><spring:message code='str55'/></label>
	    <input id="serverIds" name="serverIds" value="${paramMap.serverIdList}" readonly="readonly"/>
	    <%--<input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="openSeverDialog();">--%>
		<input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="showContent('${ctx}/tools/gameArea/openGameServer')">
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
	</form>
	<tags:message content="${message}"/>
	
	<br/>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span6 panel panel-primary">
				<div class="panel-heading"><spring:message code='str336'/></div>
				<table id="jobDistribution" class="datatable table table-striped table-bordered table-condensed">
					<thead>
					<th><spring:message code='str89'/></th><th><spring:message code='str206'/></th>
					</thead>
					<tbody>
					<c:forEach items="${jobList}" var="item">
						<tr>
							<td>${fns:getJobName(item.job)}</td>
							<td>${item.num }</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		
		<div class="row-fluid">
			<div class="span6 panel panel-primary">
				<div class="panel-heading"><spring:message code='str337'/></div>
				<table id="jobLevelDistribution" class="datatable table table-striped table-bordered table-condensed">
					<thead>
					<th><spring:message code='str85'/>/<spring:message code='str89'/></th> 
					<c:forEach items="${fns:getDictList('job_type')}" var="item">
					<th>${fns:getJobName(item.value)}</th>
					</c:forEach>
					</thead>
					<tbody>
					<c:forEach items="${jobLevelList }" var="item">
						<tr>
							<td>${item.level }</td>
							<c:forEach items="${item.numList}" var="num">
							<td>${num}</td>
							</c:forEach>
						</tr>
					</c:forEach>
						<tr>
							<td><spring:message code='str193'/></td>
							<c:forEach items="${totalLevelNumList}" var="num">
							<td>${num}</td>
							</c:forEach>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="span6 panel panel-primary">
				<div class="panel-heading"><spring:message code='str338'/></div>
				<table id="jobVipDistribution" class="datatable table table-striped table-bordered table-condensed">
					<thead>
					<th>vip<spring:message code='str85'/>/<spring:message code='str89'/></th> 
					<c:forEach items="${fns:getDictList('job_type')}" var="item">
					<th>${fns:getJobName(item.value)}</th>
					</c:forEach>
					</thead>
					<tbody>
					<c:forEach items="${jobVipList }" var="item">
						<tr>
							<%--<td>${item.vip_level}</td> --%>
							<td>${fns:getVipLevel(item.vip_level)}</td>
							<c:forEach items="${item.numList}" var="num">
							<td>${num}</td>
							</c:forEach>
						</tr>
					</c:forEach>
						<tr>
							<td><spring:message code='str193'/></td>
							<c:forEach items="${totalVipNumList}" var="num">
							<td>${num}</td>
							</c:forEach>
						</tr>
					</tbody>
				</table>
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
