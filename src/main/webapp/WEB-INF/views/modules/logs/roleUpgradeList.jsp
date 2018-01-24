<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str1010'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/highcharts.jsp" %>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/log/roleUpgrade/list"><spring:message code='str1010'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/log/roleUpgrade/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

		<label><spring:message code='str3'/> <spring:message code='str4'/></label>
	    <input name="createDate" style="width: 216px" readonly="readonly" value="${paramMap.createDate}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
	  
	   <label><spring:message code='str626'/> <spring:message code='str4'/></label>
			<select name="viplevel">
					<option value=""><spring:message code='str627'/></option>
			 	<c:forEach items="${vipLevelMap}" var="item">
					<option value="${item.key}"
						<c:if test="${item.key==paramMap.viplevel}">selected="selected"</c:if>
					>${item.value}</option>
			 	</c:forEach>
			</select>
		
		<label><spring:message code='str55'/></label>
	    <input id="serverIds" name="serverIds" value="${paramMap.serverIdList}" readonly="readonly"/>
	    <%--<input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="openSeverDialog();">--%>
		<input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="showContent('${ctx}/tools/gameArea/openGameServer')">
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
	    <input id="btnExport" class="btn btn-primary" type="button" value="<spring:message code='str5'/>" onclick="exportXls('${ctx}/log/roleUpgrade/export')"/>
	</form>
	<tags:message content="${message}"/>
	
	<br/>
		<div class="row-fluid">
			<div  class="highchart-monthsIncome" style="height: 300px"></div>
			<table class="highchart table"
				   data-graph-container=".highchart-monthsIncome" data-graph-type="column"  data-graph-xaxis-1-title-text="1"
				   data-graph-yaxis-1-title-text = '<spring:message code='str1011'/>' data-graph-zoom-type="xy" style="display: none">
				<caption><spring:message code='str1010'/></caption>
				<thead>
				<tr>
					<th><spring:message code='str1011'/></th>
					<th data-graph-type="column"><spring:message code='str85'/></th>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${list }" var="item">
					<tr>
						<td>${item.level }</td>
						<td>${item.time }</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>

			<table id="monthly-sales" class="datatable table table-striped table-bordered table-condensed">
				<thead>
				<tr><th><spring:message code='str85'/></th><th><spring:message code='str1012'/>(<spring:message code='str237'/>)</th><th><spring:message code='str1013'/>(<spring:message code='str237'/>)</th></tr>
				</thead>
				<tbody>
				<c:forEach items="${list }" var="item">
					<tr>
						<td>${item.level }</td>
						<td>${item.time }</td>
						<td>${item.avgTime }</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>


		

<script type="text/javascript">
	$(document).ready(function(){
		$('table.highchart').highchartTable();
	});
	
</script>

</body>
</html>
