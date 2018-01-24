<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code='str832'/></title>
<meta name="decorator" content="default" />
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/log/guard/guardTempleList"><spring:message code='str832'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/log/guard/guardTempleList" method="post" class="breadcrumb form-search">
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
 		<div class="" style="width:100%;height:300px;line-height:300px;overflow:auto;overflow-x:hidden;">
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
					<tr>
						<th width='200'><spring:message code='str637'/></th><td width='200'>${active[0].num}</td>
					</tr>
					<tr>
						<th width='200'><spring:message code='str833'/></th><td width='200'><c:if test="${templeMopUp[0].cou ==null}"> 0</c:if>
					<c:if test="${templeMopUp[0].cou !=null}">${templeMopUp[0].cou}</c:if></td>
					</tr>
					<tr>
						<th width='200'><spring:message code='str834'/></th><td width='200'><c:if test="${templeSeckill[0].cou ==null}"> 0</c:if>
					<c:if test="${templeSeckill[0].cou !=null}">${templeSeckill[0].cou}</c:if></td>
					</tr>
					<tr>
						<th width='200'><spring:message code='str835'/></th><td width='200'><c:if test="${templeCreateinstance[0].cou ==null}">0</c:if>
					<c:if test="${templeCreateinstance[0].cou !=null}">${templeCreateinstance[0].cou}</c:if></td>
					</tr>
					<tr>
						<th width='200'><spring:message code='str836'/></th><td width='200'><c:if test="${templeReset[0].num ==null}">0</c:if>
					<c:if test="${templeReset[0].num !=null}">${templeReset[0].num}</c:if></td>
					</tr>
			</table>
		</div>
 	</div>
 	<div class="row-fluid">
 		<div class="" style="width:100%;height:300px;line-height:300px;overflow:auto;overflow-x:hidden;">
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
					<tr>
						<th width='200'><spring:message code='str837'/></th><th width='200'><spring:message code='str838'/></th>
					</tr>
					<c:forEach items="${peopleNum}" var="item">
					<c:if test="${item.gate=='gate01' }"><tr><td>1~25</td><td>${item.cou}</td></tr></c:if>
					<c:if test="${item.gate=='gate02' }"><tr><td>26~50</td><td>${item.cou}</td></tr></c:if>
					<c:if test="${item.gate=='gate03' }"><tr><td>51~75</td><td>${item.cou}</td></tr></c:if>
					<c:if test="${item.gate=='gate04' }"><tr><td>76~100</td><td>${item.cou}</td></tr></c:if>
					<c:if test="${item.gate=='gate05' }"><tr><td>101~125</td><td>${item.cou}</td></tr></c:if>
					<c:if test="${item.gate=='gate06' }"><tr><td>126~150</td><td>${item.cou}</td></tr></c:if>
					<c:if test="${item.gate=='gate07' }"><tr><td>151~175</td><td>${item.cou}</td></tr></c:if>
					<c:if test="${item.gate=='gate08' }"><tr><td>176~200</td><td>${item.cou}</td></tr></c:if>
					<c:if test="${item.gate=='gate09' }"><tr><td>201~225</td><td>${item.cou}</td></tr></c:if>
					<c:if test="${item.gate=='gate10' }"><tr><td>226~250</td><td>${item.cou}</td></tr></c:if>
				</c:forEach>
			</table>
		</div>
 	</div>

 	
</body>
</html>
