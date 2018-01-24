<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code='str877'/></title>
<meta name="decorator" content="default" />
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/log/mine/mineWarList"><spring:message code='str877'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/log/mine/mineWarList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" /> 
		<label><spring:message code='str3'/> <spring:message code='str4'/></label>
		<input type="text" name="createDateStart" class="input-small" maxlength="20" readonly="readonly" value="${paramMap.createDateStart}" id="startDatePicker" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDatePicker\')}',minDate:'#F{$dp.$D(\'endDatePicker\',{d:-6})}'})">
		-&nbsp;<input type="text" name="createDateEnd" class="input-small" readonly="readonly" value="${paramMap.createDateEnd}" id="endDatePicker" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatePicker\')}',maxDate:'#F{$dp.$D(\'startDatePicker\',{d:6})}'})">
		<label><spring:message code='str55'/></label>
		<input id="serverIds" name="serverIds" value="${paramMap.serverIds}" readonly/>
        <input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="showContent('${ctx}/tools/gameArea/openGameServer')">
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();" />
	</form>
	<tags:message content="${message}" />
	
	<div class="row-fluid">
		<div class="span6 panel panel-primary">
			<div class="panel-heading"><spring:message code='str877'/></div>
			<div class="" style="width:100%;height:300px;line-height:300px;overflow:auto;overflow-x:hidden;">
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<tr>
					<td width='200'><spring:message code='str637'/></td><td width='200'>${active[0].num}</td>
				</tr>
				<tr>
					<td width='200'><spring:message code='str878'/></td><td width='200'><c:if test="${mineWarParticipation[0].cou ==null}"> 0</c:if>
					<c:if test="${mineWarParticipation[0].cou !=null}">${mineWarParticipation[0].cou}</c:if></td>
				</tr>
				<tr>
					<td width='200'><spring:message code='str879'/></td><td width='200'><c:if test="${mineWarDeathToll[0].num == null}"> 0</c:if>
					<c:if test="${mineWarDeathToll[0].num != null}">${mineWarDeathToll[0].num }</c:if></td>
				</tr>
				<tr>
					<td width='200'><spring:message code='str880'/></td><td width='200'><c:if test="${mineWarDeaths[0].num == null}"> 0</c:if>
					<c:if test="${mineWarDeaths[0].num != null}">${mineWarDeaths[0].num }</c:if></td>
				</tr>
				
			</table>
			</div>
 		</div>
 		
 		<div class="span6 panel panel-primary">
			<div class="panel-heading"><spring:message code='str881'/></div>
			<div class="" style="width:100%;height:300px;line-height:300px;overflow:auto;overflow-x:hidden;">
 			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<tr>
					<th width='200'></th><th width='200'><spring:message code='str206'/></th><th width='200'><spring:message code='str814'/></th>
				</tr>
				<c:forEach items="${goldMine}" var="item">
					<c:if test="${item.gather_id=='sj_004'}">
						<tr><td><spring:message code='str882'/></td><td>${item.cou}</td><td>${item.num}</td></tr>
					</c:if>
					<c:if test="${item.gather_id=='sj_005'}">
						<tr><td><spring:message code='str883'/></td><td>${item.cou}</td><td>${item.num}</td></tr>
					</c:if>
					<c:if test="${item.gather_id=='sj_006'}">
						<tr><td><spring:message code='str884'/></td><td>${item.cou}</td><td>${item.num}</td></tr>
					</c:if>
				</c:forEach>
			</table>
			</div>
		</div>
 	</div>
 	<div class="row-fluid">
		<div class="span6 panel panel-primary">
			<div class="panel-heading">AXP<spring:message code='str885'/></div>
			<div class="" style="width:100%;height:300px;line-height:300px;overflow:auto;overflow-x:hidden;">
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<tr>
					<th width='200'></th><th width='200'><spring:message code='str206'/></th><th width='200'><spring:message code='str814'/></th>
				</tr>
				<c:forEach items="${goldMine}" var="item">
					<c:if test="${item.gather_id=='sj_007'}">
						<tr><td><spring:message code='str882'/></td><td>${item.cou}</td><td>${item.num}</td></tr>
					</c:if>
					<c:if test="${item.gather_id=='sj_008'}">
						<tr><td><spring:message code='str883'/></td><td>${item.cou}</td><td>${item.num}</td></tr>
					</c:if>
					<c:if test="${item.gather_id=='sj_009'}">
						<tr><td><spring:message code='str884'/></td><td>${item.cou}</td><td>${item.num}</td></tr>
					</c:if>
				</c:forEach>
			</table>
			</div>
 		</div>
 		
 		<div class="span6 panel panel-primary">
			<div class="panel-heading"><spring:message code='str886'/></div>
			<div class="" style="width:100%;height:300px;line-height:300px;overflow:auto;overflow-x:hidden;">
 			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<tr>
					<th width='200'></th><th width='200'><spring:message code='str206'/></th><th width='200'><spring:message code='str814'/></th>
				</tr>
				<c:forEach items="${goldMine}" var="item">
					<c:if test="${item.gather_id=='sj_001'}">
						<tr><td><spring:message code='str882'/></td><td>${item.cou}</td><td>${item.num}</td></tr>
					</c:if>
					<c:if test="${item.gather_id=='sj_002'}">
						<tr><td><spring:message code='str883'/></td><td>${item.cou}</td><td>${item.num}</td></tr>
					</c:if>
					<c:if test="${item.gather_id=='sj_003'}">
						<tr><td><spring:message code='str884'/></td><td>${item.cou}</td><td>${item.num}</td></tr>
					</c:if>
				</c:forEach>
			</table>
			</div>
		</div>
 	</div> 	
</body>
</html>
