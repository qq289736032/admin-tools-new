<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str625'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/highcharts.jsp" %>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/global/taskFinish/list"><spring:message code='str625'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/global/taskFinish/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

		<label><spring:message code='str3'/> <spring:message code='str4'/></label>
	    <input name="createDate" id="createDate" style="width: 216px" readonly="readonly" value="${paramMap.createDate}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
	  
	   <label><spring:message code='str626'/> <spring:message code='str4'/></label>
			<select name="viplevel">
					<option value=""><spring:message code='str627'/></option>
					<option value=" "><spring:message code='str628'/></option>
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
	    <input id="btnExport" class="btn btn-primary" type="button" value="<spring:message code='str5'/>" onclick="exportXls('${ctx}/global/taskFinish/exportXls')"/>
	</form>
	<tags:message content="${message}"/>
	
	<br/>
		<div class="row-fluid">
			<div class="span12 panel panel-primary">
				<div class="panel-heading"><spring:message code='str629'/></div>
				<div style="height: 300px;line-height: 300px">
					<div  class="highchart-monthsIncome" style="height: 300px"></div>
					<table class="highchart table"
						   data-graph-container=".highchart-monthsIncome" data-graph-type="column"  data-graph-xaxis-1-title-text="1"
						   data-graph-yaxis-1-title-text = '<spring:message code='str630'/>' data-graph-zoom-type="xy" style="display: none">
						<caption><spring:message code='str629'/></caption>
						<thead>
						<tr>
							<th><spring:message code='str630'/></th>
							<th data-graph-type="column"><spring:message code='str631'/></th>
						</tr>
						</thead>
						<tbody>
							<c:forEach items="${list }" var="item">
								<tr>
								    <td>${fns:getTaskName(item.taskId)}</td>
		       					    <td  <c:if test="${item.scale > 1.00 }">style="color: red;font-weight:bold" </c:if>>${item.scale}%</td> 
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>

      <table id="monthly-sales" class="datatable table table-striped table-bordered table-condensed">
		<thead>
		<tr><th><spring:message code='str632'/></th><th><spring:message code='str633'/></th><th><spring:message code='str634'/></th><th>完成<spring:message code='str634'/></th><th><spring:message code='str226'/></th>
		</thead>
	 	<tbody>
			<c:forEach items="${list }" var="item" varStatus="status">
			<tr>
			   <td>${status.index +1 }</td>
			   <td>${fns:getTaskName(item.taskId)}</td>
			   <td>${item.accept }</td>
			   <td>${item.complete }</td>
		        <td  <c:if test="${item.scale > 1.00 }">style="color: red;font-weight:bold" </c:if>>${item.scale}%</td> 
			</tr>
		</c:forEach>
	    <c:forEach items="${total }" var="item">
		  <tr>
		    <td style="font-weight: bold;"><spring:message code='str193'/></td> 
		    <td></td> 
		    <td>${item.acceptTotal }</td> 
		    <td>${item.completeTotal }</td> 
		    <td  <c:if test="${item.totalScale > 1.00 }">style="color: red;font-weight:bold" </c:if>>${item.totalScale}%</td> 
		  </tr>
		</c:forEach>
		
		</tbody>
	</table>
		

<script type="text/javascript">
	$(document).ready(function(){
		$('table.highchart').highchartTable();
	});
	
</script>

</body>
</html>
