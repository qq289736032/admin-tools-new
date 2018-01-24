<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code='str698'/></title>
<meta name="decorator" content="default" />
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/log/lotto/bigLottoList"><spring:message code='str698'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/log/lotto/bigLottoList" method="post" class="breadcrumb form-search">
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
		<div class="span12 panel panel-primary">
			<div class="panel-heading"><spring:message code='str699'/></div>
			<div class="" style="width:100%;height:300px;line-height:300px;overflow:auto;overflow-x:hidden;">
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<tr>
					<th width="200px"><spring:message code='str700'/></th><th width="200px"><spring:message code='str206'/></th>
				</tr>
				<c:forEach items="${bigLottoAward}" var="item">
					<tr>
						<td>${item.item_name}</td>
						<td>${item.cou}</td>
					</tr>
				</c:forEach>
				
			</table>
			</div>
 		</div>
 	</div>
	<div class="row-fluid">
		<div class="span6 panel panel-primary">
			<div class="panel-heading"><spring:message code='str701'/></div>
			<div class="" style="width:100%;height:300px;line-height:300px;overflow:auto;overflow-x:hidden;">
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<tr><th><spring:message code='str700'/></th><th><spring:message code='str206'/></th></tr>				
				<c:forEach items="${bigLottoExtract}" var="item">
					<tr>
						<td><c:if test="${item.type==1}"><spring:message code='str702'/>1<spring:message code='str703'/></c:if>
							<c:if test="${item.type==2}"><spring:message code='str702'/>10<spring:message code='str703'/></c:if>
							<c:if test="${item.type==3}"><spring:message code='str702'/>50<spring:message code='str703'/></c:if>
						</td>
						<td>${item.cou}</td>
					</tr>
				</c:forEach>
			</table>
			</div>
 		</div>
 		<div class="span6 panel panel-primary">
			<div class="panel-heading"><spring:message code='str704'/></div>
			<div class="" style="width:100%;height:300px;line-height:300px;overflow:auto;overflow-x:hidden;">
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<tr><th><spring:message code='str198'/></th><th><spring:message code='str705'/></th></tr>				
				
				<c:forEach items="${bigLottoConsume}" var="item">
					<tr>
						<td>${item.log_day}</td>
						<td>${item.cou}</td>
					</tr>
				</c:forEach>
			</table>
			</div>
 		</div>
 		
 	</div>
 	
</body>
</html>
