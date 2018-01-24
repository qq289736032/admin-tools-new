<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>

<head>
	<title><spring:message code='str1027'/></title>
	<meta name="decorator" content="default"/>
    <%@include file="/WEB-INF/views/include/highcharts.jsp" %>
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
            $("#searchForm").attr("action","${ctx}/log/stallTrade/list");
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
		<li class="active"><a href="${ctx}/log/stallTrade/list"><spring:message code='str1027'/></a></li>
	 </ul>
	<form id="searchForm" action="${ctx}/llog/stallTrade/list"" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label><spring:message code='str1028'/>ID<spring:message code='str4'/></label>
		<input type="text" id="roleId"   style="width:180px" name="roleId" value="${paramMap.roleId}"/>
		<label><spring:message code='str1029'/></label>
		<input type="text" id="roleName" style="width:180px" name="roleName" value="${paramMap.roleName}"/>
		<label><spring:message code='str1030'/>ID<spring:message code='str4'/></label>
		<input type="text" id="sellerRoleId" style="width:180px"name="sellerRoleId" value="${paramMap.sellerRoleId}"/>
		<label><spring:message code='str1031'/></label>
		<input type="text" id="sellerRoleName" name="sellerRoleName" value="${paramMap.sellerRoleName}"/>
		<label><spring:message code='str185'/></label>
		<input type="text" name="createDateStart" class="input-small" readonly="readonly" maxlength="20" value="${paramMap.createDateStart}" id="startDatePicker" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'endDatePicker\',{d:-15})}',maxDate:'#F{$dp.$D(\'endDatePicker\')}'})">
		-&nbsp;<input type="text" name="createDateEnd"   class="input-small" readonly="readonly" value="${paramMap.createDateEnd}"   id="endDatePicker" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatePicker\')}'})">
	    &nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
	    &nbsp;<input id="btnExport" class="btn btn-primary" type="button" value="<spring:message code='str5'/>" onclick="doExport('${ctx}/log/stallTrade/export')"/>
	</form>
	<tags:message content="${message}"/>
    <table id="active-yb" class="table table-striped table-bordered table-condensed">
		     <tr>
		        <th><spring:message code='str1028'/>ID</th>
		        <th><spring:message code='str1032'/></th>
		        <th><spring:message code='str1030'/>ID</th>
		        <th><spring:message code='str1033'/></th>
		        <th><spring:message code='str1034'/></th>
		        <th><spring:message code='str1035'/></th>
		        <th><spring:message code='str440'/></th>
		        <th><spring:message code='str1036'/></th>
		        <th><spring:message code='str1037'/></th>
			    <th><spring:message code='str1038'/></th></tr>
		<c:forEach items="${page.list }" var="item">
			<tr>
				<td>${item.role_id}</td>
				<td>${item.role_name }</td>
				<td>${item.seller_role_id}</td>
				<td>${item.seller_role_name}</td>
				<td>${fns:getGoodName(item.item_id)}</td>
				<td>${item.seller_role_level}</td>
				<td>${item.num}</td>
				<td>${item.cost }</td>
				<td>${item.money }</td>
				<td>${fns:parseLong(item.start_time )}</td>
			</tr>
		</c:forEach>
	</table>
	<div class="pagination">${page}</div>
	 <div class="alert">
		 <button type="button" class="close"  data-dismiss="alert">&times;</button>
		 <strong>Warning!</strong><spring:message code='str1041'/>
	 </div>
</body>
</html>
