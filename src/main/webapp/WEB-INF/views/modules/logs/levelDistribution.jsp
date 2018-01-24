<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str859'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/highcharts.jsp" %>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/log/roleUpgrade/levelDistribution"><spring:message code='str859'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/log/roleUpgrade/levelDistribution" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

		<label><spring:message code='str3'/> <spring:message code='str4'/></label>
	    <input name="startDate" style="width: 216px" readonly="readonly" value="${paramMap.startDate}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
	  
	    <label><spring:message code='str626'/> <spring:message code='str4'/></label>
		<select name="viplevel">
			<option value=""><spring:message code='str627'/></option>
			<c:forEach items="${vipLevelMap}" var="item">
				<option value="${item.key}"
						<c:if test="${item.key==paramMap.viplevel}">selected="selected"</c:if>
						>${item.value}</option>
			</c:forEach>
			<option value="02" <c:if test="${02==paramMap.viplevel}">selected="selected"</c:if>>2D</option>
			<option value="01" <c:if test="${01==paramMap.viplevel}">selected="selected"</c:if>>3D</option>
		</select>
		
		<label><spring:message code='str55'/></label>
	    <input id="serverIds" name="serverIds" value="${paramMap.serverIdList}" readonly="readonly"/>
	    <%--<input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="openSeverDialog();">--%>
		<input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="showContent('${ctx}/tools/gameArea/openGameServer')">
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
	</form>
	<tags:message content="${message}"/>
	
	<br/>
		<div class="row-fluid">
			<div  class="highchart-levelDistribution" style="height: 300px"></div>
			<table class="highchart table"
				   data-graph-container=".highchart-levelDistribution" data-graph-type="column"  data-graph-xaxis-1-title-text="1"
				   data-graph-yaxis-1-title-text = '<spring:message code='str206'/>' style="display: none">
				<caption><spring:message code='str860'/></caption>
				<thead>
				<tr>
					<th><spring:message code='str85'/></th>
					<th data-graph-type="column"><spring:message code='str860'/></th>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${list }" var="item">
					<tr>
						<td>${item.roleLevel }</td>
						<td>${item.num }</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>

			<table id="levelDistribution" class="datatable table table-striped table-bordered table-condensed">
				<thead>
				<th><spring:message code='str85'/></th><th><spring:message code='str206'/></th><th><spring:message code='str226'/></th>
				</thead>
				<tbody>
				<c:forEach items="${list }" var="item">
					<tr>
						<td>${item.roleLevel }</td>
						<td>${item.num }</td>
						<td <c:if test="${item.num/total*100  > 100 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber value="${item.num/total*100 }" pattern="0.00"/>%</td>
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
