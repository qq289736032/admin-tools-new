<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code='str782'/></title>
<meta name="decorator" content="default" />
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/log/excavate/excavateCorpseList"><spring:message code='str782'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/log/excavate/excavateCorpseList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" /> 
		<label><spring:message code='str3'/> <spring:message code='str4'/></label>
		<input type="text" name="createDateStart" class="input-small" maxlength="20" readonly="readonly" value="${paramMap.createDateStart}" id="startDatePicker" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDatePicker\')}'})">
		-&nbsp;<input type="text" name="createDateEnd" class="input-small" readonly="readonly" value="${paramMap.createDateEnd}" id="endDatePicker" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatePicker\')}'})">
		<label><spring:message code='str55'/></label>
		<input type="text" id="serverIds" name="serverIds" value="${paramMap.serverIds}" readonly/>
        <input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="showContent('${ctx}/tools/gameArea/openGameServer')">
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();" />
	</form>
	<tags:message content="${message}" />
	<div class="row-fluid">
 			<div class="" style="width:100%;height:300px;line-height:300px;overflow:auto;overflow-x:hidden;">
 			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<tr><th>VIP<spring:message code='str783'/></th><th><spring:message code='str206'/></th></tr>				
				<c:set var="flag" value ="1"/>
				<c:forEach items="${vipExcavate}" var="ex">
					<c:forEach begin="${flag}" end="${ex.num-1}">
						<tr>
							<td><spring:message code='str784'/>${flag}<spring:message code='str703'/></td><td>0</td>
						</tr>
						<c:set var="flag" value="${flag+1}"/>
					</c:forEach>
					<tr><td><spring:message code='str784'/>${ex.num }<spring:message code='str703'/></td><td>${ex.cou}</td></tr>
					<c:set var="flag" value="${flag+1}"/>
				</c:forEach>
			</table>
			</div>
		
 	</div>
 	<div class="row-fluid">
		<div class="span6 panel panel-primary" style="border:0px solid black;">
			<div class="" style="width:100%;height:300px;line-height:300px;overflow:auto;overflow-x:hidden;">
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<tr>
					<th width="200"><spring:message code='str785'/></th><td width="200">${vipNumber[0].cou}</td>
				</tr>
				<tr>
					<th><spring:message code='str786'/>VIP4<spring:message code='str787'/></th><td>${vipNumber[0].vip4}</td>
				</tr>
				<tr>
					<th><spring:message code='str788'/>VIP6<spring:message code='str787'/></th><td>${vipNumber[0].vip6}</td>
				</tr>
				<tr>
					<th><spring:message code='str788'/>VIP8<spring:message code='str787'/></th><td>${vipNumber[0].vip8}</td>
				</tr>
			
			</table>
			</div>
 		</div>
 		
 		<div class="span6 panel panel-primary" style="border:0px solid black;">
 			<div class="" style="width:100%;height:300px;line-height:300px;overflow:auto;overflow-x:hidden;">
 			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<tr><th></th><th><spring:message code='str789'/></th><th><spring:message code='str790'/></th><th><spring:message code='str791'/></th></tr>	
				<c:forEach items="${vipTask}" var="item">
					<c:if test="${item.guankaid==1 }">
						<tr><td>VIP4<spring:message code='str783'/></td><td>${item.cou }</td><td>${item.num }</td><td>${item.yb }</td></tr>
					</c:if>
					<c:if test="${item.guankaid==2 }">
						<tr><td>VIP6<spring:message code='str783'/></td><td>${item.cou }</td><td>${item.num }</td><td>${item.yb }</td></tr>
					</c:if>
					<c:if test="${item.guankaid==3 }">
						<tr><td>VIP8<spring:message code='str783'/></td><td>${item.cou }</td><td>${item.num }</td><td>${item.yb }</td></tr>
					</c:if>
					
				</c:forEach>
				
			</table>
			</div>
		</div>
 	</div>
 	
</body>
</html>
