<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code='str1062'/></title>
<meta name="decorator" content="default" />
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/log/territory/territoryWarList"><spring:message code='str1062'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/log/territory/territoryWarList" method="post" class="breadcrumb form-search">
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
 		<div class="span12 panel panel-primary">
 		<div class="panel-heading"><spring:message code='str1063'/></div>
 		<div class="" style="width:100%;height:300px;line-height:300px;overflow:auto;overflow-x:hidden;">
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
					<tr>
						<th width='200'><spring:message code='str1064'/></th><th width='200'><spring:message code='str8'/></th>
					</tr>
					<c:forEach items="${factionFightNum}" var="item">
					<tr>
						<td>${item.guild_name}</td>
               			<td>${item.fight}</td>
					</tr>
					</c:forEach>
					<tr>
						<td width='200'><spring:message code='str637'/></td><td width='200'>${active[0].num}</td>
					</tr>
			</table>
			</div>
		</div>
 	</div>
 	<div class="row-fluid">
		<div class="span6 panel panel-primary">
			<div class="panel-heading"><spring:message code='str1065'/></div>
			<div class="" style="width:100%;height:300px;line-height:300px;overflow:auto;overflow-x:hidden;">
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<tr><th><spring:message code='str1066'/></th><th><spring:message code='str1018'/></th><th><spring:message code='str1067'/></th><th><spring:message code='str1068'/></th></tr>
				<c:forEach items="${mapList}" var="items">
					<tr>
						<td>${items}</td>
						<td>
							<c:set var="flag" value ="0"/>
							<c:forEach items="${territoryWarDie}" var="item">
								<c:if test="${items==item.stage_id}"><c:set var="flag" value ="${item.cou }"/></c:if>
							</c:forEach>
							${flag }
						</td>
						<td>
						<c:set var="flag" value ="0"/>
						<c:forEach items="${territoryWarDie}" var="item">
							<c:if test="${items==item.stage_id}"><c:set var="flag" value ="${item.num }"/></c:if>
						</c:forEach>
						${flag }
						</td>
						<td>
						<c:set var="flag" value ="0"/>
						<c:forEach items="${territoryWarPull}" var="pull">
							<c:if test="${items==pull.map_id}"><c:set var="flag" value ="${pull.cou }"/></c:if>
						</c:forEach>
						${flag }
						</td>
					</tr>
				</c:forEach>
			</table>
			</div>
 		</div>
 		
 		<div class="span6 panel panel-primary">
			<div class="panel-heading"><spring:message code='str1069'/></div>
			<div class="" style="width:100%;height:300px;line-height:300px;overflow:auto;overflow-x:hidden;">
 			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<tr><th><spring:message code='str1070'/></th><th><spring:message code='str1071'/></th></tr>
				<c:forEach items="${mapOccupyFaction}" var="item">
					<tr>
						<td>${item.map_id}</td>
               			<td>${item.guild_name}</td>
					</tr>
				</c:forEach>
			</table>
			</div>
		</div>
 	</div>

 	
</body>
</html>
