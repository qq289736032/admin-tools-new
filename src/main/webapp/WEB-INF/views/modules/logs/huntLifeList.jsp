<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code='str848'/></title>
<meta name="decorator" content="default" />
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/log/hunt/huntLifeList"><spring:message code='str848'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/log/hunt/huntLifeList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" /> 
		<label><spring:message code='str3'/> <spring:message code='str4'/></label>
		<input type="text" name="createDateStart" class="input-small" maxlength="20" readonly="readonly" value="${paramMap.createDateStart}" id="startDatePicker" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDatePicker\')}',minDate:'#F{$dp.$D(\'endDatePicker\',{d:-6})}'})">
		-&nbsp;<input type="text" name="createDateEnd" class="input-small" readonly="readonly" value="${paramMap.createDateEnd}" id="endDatePicker" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatePicker\')}',maxDate:'#F{$dp.$D(\'startDatePicker\',{d:6})}'})">
		<label><spring:message code='str55'/></label>
		<input type="text" id="serverIds" name="serverIds" value="${paramMap.serverIds}" readonly/>
        <input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="showContent('${ctx}/tools/gameArea/openGameServer')">
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();" />
	</form>
	<tags:message content="${message}" />
	<div class="row-fluid">
		<div class="" style="width:100%;height:300px;line-height:300px;overflow:auto;overflow-x:hidden;">
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<tr>
					<th></th><th><spring:message code='str849'/></th><th><spring:message code='str850'/></th>
				</tr>
				<c:forEach items="${huntConversion}" var="item">
					<tr>
						<td>${item.goods_id}</td>
						<td>${item.num}</td>
						<td>${item.cou}</td>
					</tr>
				</c:forEach>
				
			</table>
		</div>
 	</div>
 	<div class="row-fluid">
		<div class="" style="width:100%;height:300px;line-height:300px;overflow:auto;overflow-x:hidden;">
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<tr>
					<th><spring:message code='str700'/></th><th><spring:message code='str206'/></th><th><spring:message code='str814'/></th><th><spring:message code='str851'/></th>
				</tr>
				<c:forEach items="${huntLife}" var="item">
					<c:if test="${item.operate_type == 5065}">
         				<tr>
         					<td><spring:message code='str852'/></td>
							<td>${item.num}</td>
							<td>${item.cou}</td>
							<td>${item.consume}</td>
         				</tr>
      				</c:if>
      				<c:if test="${item.operate_type==5067}">
         				<tr>
         					<td><spring:message code='str853'/></td>
							<td>${item.num}</td>
							<td>${item.cou}</td>
							<td>${item.consume}</td>
         				</tr>
      				</c:if>
				</c:forEach>
				
			</table>
		</div>
 	</div>
	
 	
</body>
</html>
