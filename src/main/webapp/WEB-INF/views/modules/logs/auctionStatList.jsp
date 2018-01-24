<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>

<head>
	<title><spring:message code='acution.house'/></title>
	<meta name="decorator" content="default"/>
    <%@include file="/WEB-INF/views/include/highcharts.jsp" %>
    <%@include file="/WEB-INF/views/include/dialog.jsp" %>
	<script src="${ctxStatic}/common/fixtable.js" type="text/javascript"></script>
	<script type="text/javascript">
	$(document).ready(function(){
		$("#s2id_operaType").hide();
		$("#operaType").multiselect({
			includeSelectAllOption:true,
			enableFiltering:true
		});
	});
	function page(n,s){
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").attr("action","${ctx}/log/auctionHouse/view");
            $("#searchForm").submit();
            return false;
    }
	</script>
	<style type="text/css">
		span.input-group-btn {
			display: none;
		}
	</style>
</head>
<body>
     <ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/log/auctionHouse/view"><spring:message code='acution.house'/></a></li>
	 </ul>
	<form id="searchForm" action="${ctx}/log/auctionHouse/view" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		
		<label><spring:message code='str2'/></label>
		<input type="text" id="roleName" name="roleName" value="${paramMap.roleName}"/>
		
		<label><spring:message code='str915'/></label>
		<input type="text" id="itemName" name="itemName" value="${paramMap.itemName}"/>
		
		<label><spring:message code='str185'/></label>
		<input type="text" name="createDateStart" class="input-small" readonly="readonly" maxlength="20" value="${paramMap.createDateStart}" id="startDatePicker" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'endDatePicker\')}',minDate:'#F{$dp.$D(\'endDatePicker\',{d:-15})}'})">
		-&nbsp;<input type="text" name="createDateEnd"   class="input-small" readonly="readonly" value="${paramMap.createDateEnd}"   id="endDatePicker" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatePicker\')}',maxDate:'#F{$dp.$D(\'startDatePicker\',{d:15})}'})">
	
	    &nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
		<shiro:hasPermission name="log.propConsume.export">
	    &nbsp;<input id="btnExport" class="btn btn-primary" type="button" value="<spring:message code='str5'/>" onclick="doExport('${ctx}/log/propController/export')"/>
		</shiro:hasPermission>
	</form>
	<tags:message content="${message}"/>
    <table id="active-yb" class="table table-striped table-bordered table-condensed">
		<tr>
			<th><spring:message code='str1032'/></th>
			<th><spring:message code='str915'/></th>
			<th><spring:message code='str75'/></th>
			<th><spring:message code='str1688'/></th>
			<th><spring:message code='str1689'/></th>
			<th><spring:message code='str1031'/></th>
			<th><spring:message code='str3'/></th>
		</tr>
		<c:forEach items="${sellPage.list }" var="item">
			<tr>
				<td>${item.role_name }</td>
				<td>${fns:getGoodName(item.item_id)}</td>
				<%-- <td><c:if test="${item.type==2 }"><spring:message code='str1030'/></c:if><c:if test="${item.type==1 }"><spring:message code='str1032'/></c:if></td> --%>
				<td>${item.num }</td>
				<td>${item.cost }</td>
				<td>${item.money }</td>
				<td>${item.seller_role_name }</td>
				<td>${fns:parseLong(item.log_time )}</td>
			</tr>
		</c:forEach>
	</table>
	<div class="pagination">${sellPage}</div>
	
	<table id="active-yb" class="table table-striped table-bordered table-condensed">
		<tr>
			<th><spring:message code='str1032'/></th>
			<th><spring:message code='str915'/></th>
			<th><spring:message code='str1401'/></th>
			<th><spring:message code='str1401'/></th>
			<th><spring:message code='str440'/></th>
			<th><spring:message code='str3'/></th>
		</tr>
		<c:forEach items="${page.list }" var="item">
			<tr>
				<td>${item.roleName }</td>
				<td>${fns:getGoodName(item.item_id)}</td>
				<td>${fns:getOperationType(item.opereateType)}</td>
				<td><c:if test="${item.flowType==1 }"><spring:message code='str921'/></c:if><c:if test="${item.flowType==2 }"><spring:message code='str264'/></c:if></td>
				<td><c:if test="${item.flowType==1 }">${item.value}</c:if><c:if test="${item.flowType==2 }">${item.beforeValue - afterValue }</c:if></td>
				<td>${fns:parseLong(item.log_time )}</td>
			</tr>
		</c:forEach>
	</table>
	<div class="pagination">${page}</div>
	 <div class="alert">
		 <button type="button" class="close"  data-dismiss="alert">&times;</button>
		 <strong>Warning!</strong> <spring:message code='str893'/>,<spring:message code='str727'/>15<spring:message code='str313'/>
	 </div>
</body>
</html>
