<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code='str641'/></title>
<meta name="decorator" content="default" />
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/log/amplifier/amplifierDistributionList"><spring:message code='str641'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/log/amplifier/amplifierDistributionList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" /> 
		<label><spring:message code='str3'/> <spring:message code='str4'/></label>
		<input type="text" name="createDateStart" class="input-small" maxlength="20" readonly="readonly" value="${paramMap.createDateStart}" id="startDatePicker" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDatePicker\')}'})">
		-&nbsp;<input type="text" name="createDateEnd" class="input-small" readonly="readonly" value="${paramMap.createDateEnd}" id="endDatePicker" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatePicker\')}'})">
		<label><spring:message code='str55'/></label>
		<input id="serverIds" name="serverIds" value="${paramMap.serverIds}" readonly/>
        <input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="showContent('${ctx}/tools/gameArea/openGameServer')">
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();" />
	</form>
	<tags:message content="${message}" />
	
	<div class="row-fluid">
		<div class="span6 panel panel-primary">
			<div class="panel-heading"><spring:message code='str642'/></div>
			<div class="" style="width:100%;height:300px;line-height:300px;overflow:auto;overflow-x:hidden;">
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<tr><th></th><th>0-100</th><th>101-200</th><th>201-300</th><th>301-400</th><th>401-500</th><th>501-600</th></tr>
				<c:forEach items="${funnelPrimary}" var="item">
					<c:if test="${item.item_id=='shuxing001' }"><tr><td><spring:message code='str643'/></td><td>${item.value1}</td><td>${item.value2}</td><td>${item.value3}</td><td>${item.value4}</td><td>${item.value5}</td><td>${item.value6}</td></tr></c:if>
					<c:if test="${item.item_id=='shuxing002' }"><tr><td><spring:message code='str644'/></td><td>${item.value1}</td><td>${item.value2}</td><td>${item.value3}</td><td>${item.value4}</td><td>${item.value5}</td><td>${item.value6}</td></tr></c:if>
					<c:if test="${item.item_id=='shuxing003' }"><tr><td><spring:message code='str645'/></td><td>${item.value1}</td><td>${item.value2}</td><td>${item.value3}</td><td>${item.value4}</td><td>${item.value5}</td><td>${item.value6}</td></tr></c:if>
					<c:if test="${item.item_id=='shuxing004' }"><tr><td><spring:message code='str646'/></td><td>${item.value1}</td><td>${item.value2}</td><td>${item.value3}</td><td>${item.value4}</td><td>${item.value5}</td><td>${item.value6}</td></tr></c:if>
					<c:if test="${item.item_id=='shuxing005' }"><tr><td><spring:message code='str647'/></td><td>${item.value1}</td><td>${item.value2}</td><td>${item.value3}</td><td>${item.value4}</td><td>${item.value5}</td><td>${item.value6}</td></tr></c:if>
				</c:forEach>
			</table>
			</div>
 		</div>
 		
 		<div class="span6 panel panel-primary">
			<div class="panel-heading"><spring:message code='str648'/></div>
			<div class="" style="width:100%;height:300px;line-height:300px;overflow:auto;overflow-x:hidden;">
 			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<tr><th></th><th>0-100</th><th>101-200</th><th>201-300</th><th>301-400</th><th>401-500</th><th>501-600</th></tr>
				<c:forEach items="${funnelPrimary}" var="item">
					<c:if test="${item.item_id=='shuxing101' }"><tr><td><spring:message code='str643'/></td><td>${item.value1}</td><td>${item.value2}</td><td>${item.value3}</td><td>${item.value4}</td><td>${item.value5}</td><td>${item.value6}</td></tr></c:if>
					<c:if test="${item.item_id=='shuxing102' }"><tr><td><spring:message code='str644'/></td><td>${item.value1}</td><td>${item.value2}</td><td>${item.value3}</td><td>${item.value4}</td><td>${item.value5}</td><td>${item.value6}</td></tr></c:if>
					<c:if test="${item.item_id=='shuxing103' }"><tr><td><spring:message code='str645'/></td><td>${item.value1}</td><td>${item.value2}</td><td>${item.value3}</td><td>${item.value4}</td><td>${item.value5}</td><td>${item.value6}</td></tr></c:if>
					<c:if test="${item.item_id=='shuxing104' }"><tr><td><spring:message code='str646'/></td><td>${item.value1}</td><td>${item.value2}</td><td>${item.value3}</td><td>${item.value4}</td><td>${item.value5}</td><td>${item.value6}</td></tr></c:if>
					<c:if test="${item.item_id=='shuxing105' }"><tr><td><spring:message code='str647'/></td><td>${item.value1}</td><td>${item.value2}</td><td>${item.value3}</td><td>${item.value4}</td><td>${item.value5}</td><td>${item.value6}</td></tr></c:if>
				</c:forEach>
			</table>
			</div>
		</div>
 	</div>
 	<div class="row-fluid">
		<div class="span6 panel panel-primary">
			<div class="panel-heading"><spring:message code='str649'/></div>
			<div class="" style="width:100%;height:300px;line-height:300px;overflow:auto;overflow-x:hidden;">
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<tr><th></th><th>0-100</th><th>101-200</th><th>201-300</th><th>301-400</th><th>401-500</th><th>501-600</th></tr>
				<c:forEach items="${funnelPrimary}" var="item">
					<c:if test="${item.item_id=='shuxing201' }"><tr><td><spring:message code='str643'/></td><td>${item.value1}</td><td>${item.value2}</td><td>${item.value3}</td><td>${item.value4}</td><td>${item.value5}</td><td>${item.value6}</td></tr></c:if>
					<c:if test="${item.item_id=='shuxing202' }"><tr><td><spring:message code='str644'/></td><td>${item.value1}</td><td>${item.value2}</td><td>${item.value3}</td><td>${item.value4}</td><td>${item.value5}</td><td>${item.value6}</td></tr></c:if>
					<c:if test="${item.item_id=='shuxing203' }"><tr><td><spring:message code='str645'/></td><td>${item.value1}</td><td>${item.value2}</td><td>${item.value3}</td><td>${item.value4}</td><td>${item.value5}</td><td>${item.value6}</td></tr></c:if>
					<c:if test="${item.item_id=='shuxing204' }"><tr><td><spring:message code='str646'/></td><td>${item.value1}</td><td>${item.value2}</td><td>${item.value3}</td><td>${item.value4}</td><td>${item.value5}</td><td>${item.value6}</td></tr></c:if>
					<c:if test="${item.item_id=='shuxing205' }"><tr><td><spring:message code='str647'/></td><td>${item.value1}</td><td>${item.value2}</td><td>${item.value3}</td><td>${item.value4}</td><td>${item.value5}</td><td>${item.value6}</td></tr></c:if>
				</c:forEach>
			</table>
			</div>
 		</div>
 		
 		<div class="span6 panel panel-primary">
			<div class="panel-heading"><spring:message code='str650'/></div>
			<div class="" style="width:100%;height:300px;line-height:300px;overflow:auto;overflow-x:hidden;">
 			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<tr><th></th><th>0-100</th><th>101-200</th><th>201-300</th><th>301-400</th><th>401-500</th><th>501-600</th></tr>
				<c:forEach items="${funnelPrimary}" var="item">
					<c:if test="${item.item_id=='shuxing301' }"><tr><td><spring:message code='str643'/></td><td>${item.value1}</td><td>${item.value2}</td><td>${item.value3}</td><td>${item.value4}</td><td>${item.value5}</td><td>${item.value6}</td></tr></c:if>
					<c:if test="${item.item_id=='shuxing302' }"><tr><td><spring:message code='str644'/></td><td>${item.value1}</td><td>${item.value2}</td><td>${item.value3}</td><td>${item.value4}</td><td>${item.value5}</td><td>${item.value6}</td></tr></c:if>
					<c:if test="${item.item_id=='shuxing303' }"><tr><td><spring:message code='str645'/></td><td>${item.value1}</td><td>${item.value2}</td><td>${item.value3}</td><td>${item.value4}</td><td>${item.value5}</td><td>${item.value6}</td></tr></c:if>
					<c:if test="${item.item_id=='shuxing304' }"><tr><td><spring:message code='str646'/></td><td>${item.value1}</td><td>${item.value2}</td><td>${item.value3}</td><td>${item.value4}</td><td>${item.value5}</td><td>${item.value6}</td></tr></c:if>
					<c:if test="${item.item_id=='shuxing305' }"><tr><td><spring:message code='str647'/></td><td>${item.value1}</td><td>${item.value2}</td><td>${item.value3}</td><td>${item.value4}</td><td>${item.value5}</td><td>${item.value6}</td></tr></c:if>
				</c:forEach>
			</table>
			</div>
		</div>
 	</div>	
</body>
</html>
