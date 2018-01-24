<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str967'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/dialog.jsp" %>
	<script type="text/javascript">
	function page(n,s){
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").attr("action","${ctx}/log/recharge/");
            $("#searchForm").submit();
            return false;
    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/log/recharge/"><spring:message code='str967'/></a></li>
	</ul>
	<form id="searchForm"action="${ctx}/log/recharge/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label><spring:message code='str57'/>ID<spring:message code='str4'/></label>
		<input name="userId"  maxlength="100" class="input-small" value="${paramMap.userId}"/>
		<label><spring:message code='str138'/>ID<spring:message code='str4'/></label>
		<input name="roleId"  maxlength="100" class="input-small" value="${paramMap.roleId}"/>
		<label><spring:message code='str2'/></label>
		<input name="roleName"  maxlength="100" class="input-small" value="${paramMap.roleName}"/>
		<label><spring:message code='str968'/></label>
		<input name="orderId"  maxlength="100" class="input-small" value="${paramMap.orderId}"/>
		<label><spring:message code='str709'/></label>
		<input type="text" name="createDateStart" class="input-small" readonly="readonly" maxlength="20" value="${paramMap.createDateStart}" id="startDatePicker" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDatePicker\')}'})">
		-&nbsp;<input type="text" name="createDateEnd"   class="input-small" readonly="readonly" value="${paramMap.createDateEnd}"   id="endDatePicker" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatePicker\')}'})">		
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
       &nbsp;<input id="btnExport" class="btn btn-primary" type="button" value="<spring:message code='str5'/>" onclick="doExport('${ctx}/log/recharge/export')"/>

	</form>
	<tags:message content="${message}"/>
	<form id="tableForm" action="">
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<tr><th width="18px"><input id="checkAllOrNot" name="checkAllOrNot" type="checkbox"></th><th><spring:message code='str57'/>ID</th><th><spring:message code='str138'/>ID</th><th><spring:message code='str7'/></th><th><spring:message code='str969'/></th><th><spring:message code='str970'/></th><th><spring:message code='str888'/></th>
			<th><spring:message code='str253_3'/></th><th><spring:message code='str971'/></th><th><spring:message code='str972'/></th><th><spring:message code='str973'/></th></tr>
		<c:forEach items="${page.list}" var="item">
			<tr>
				<%--<td><input type="checkbox" name="recordIds" value="${item.id}"></td>--%>
				<td><input type="checkbox" name="recordIds"></td>
				<td>${item.userId}</td>
				<td style="overflow: hidden;white-space: nowrap" title="${item.roleId}">${item.roleId}</td>
				<td style="overflow: hidden;white-space: nowrap" title="${item.roleName}">${item.roleName}</td>
				<td>${item.orderId}</td>
				<td>
					<c:choose>
						<c:when test="${item.rechargeType == 2}"><span class="label label-warning"><spring:message code="${fns:getDictKeys(item.rechargeType, 'recharge_type', item.rechargeType)}"/></span></c:when>
						<c:when test="${item.rechargeType == 1}"><span class="label label-success"><spring:message code="${fns:getDictKeys(item.rechargeType, 'recharge_type', item.rechargeType)}"/></span></c:when>
						<c:when test="${item.rechargeType == 0}"><span class="label label-info"><spring:message code="${fns:getDictKeys(item.rechargeType, 'recharge_type', item.rechargeType)}"/></span></c:when>
						<c:otherwise><spring:message code="${fns:getDictKeys(item.rechargeType, 'recharge_type', item.rechargeType)}"/></c:otherwise>
					</c:choose>
				</td>
				<td><spring:message code="${fns:getDictKeys(item.moneyType, 'money_type', item.moneyType)}"/></td>
				<td>${item.rmb_amount}</td>
				<td>${item.treasure_amount}</td>
				<td>${item.is_finish}</td>
				<td>${fns:parseLong(item.recharge_time)}</td>
			</tr>
		</c:forEach>
	</table>
	</form>
	<div class="pagination">${page}</div>
</body>
</html>
