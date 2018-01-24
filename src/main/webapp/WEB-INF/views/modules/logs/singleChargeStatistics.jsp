<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str708'/></title>
	<meta name="decorator" content="default"/>
    <script type="text/javascript">
//       $(function(){
//           $("#tipDiv").tooltip('show');
//       });

		function page(n,s){
			$("#pageNo").val(n);
		    $("#pageSize").val(s);
		    $("#searchForm").attr("action","${ctx}/log/recharge/singleChargeStatistics");
		    $("#searchForm").submit();
		    return false;
		}

    </script>
</head>
<body>
	<ul class="nav nav-tabs">
        <li><a href="${ctx}/log/recharge/chargeStatistics"><spring:message code='str707'/></a></li>
        <li class="active"><a href="${ctx}/log/recharge/singleChargeStatistics"><spring:message code='str708'/></a></li>
	</ul>
    <form id="searchForm" action="${ctx}/log/recharge/singleChargeStatistics" method="post" class="breadcrumb form-search">
        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
        <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
        <label><spring:message code='str2'/></label>
        <input type="text" id="roleName" name="roleName"  class="input-small" value="${paramMap.roleName}"/>
        <label><spring:message code='str57'/>ID<spring:message code='str4'/></label>
        <input type="text" id="userId" name="userId"  class="input-small" value="${paramMap.userId}"/>
        <label><spring:message code='str138'/>ID<spring:message code='str4'/></label>
        <input type="text" id="roleId" name="roleId"  class="input-small" value="${paramMap.roleId}"/>
        <label><spring:message code='str709'/></label>
        <input type="text" name="createDateStart" class="input_search" size="10" readonly="readonly" maxlength="20" value="${paramMap.createDateStart}" id="startDatePicker" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDatePicker\')}'})">
		-&nbsp;<input type="text" name="createDateEnd"   class="input_search" size="10" readonly="readonly" value="${paramMap.createDateEnd}"   id="endDatePicker" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatePicker\')}'})">

        &nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>

		&nbsp;<input id="btnExport" class="btn btn-primary" type="button" value="<spring:message code='str5'/>" onclick="doExport('${ctx}/log/recharge/singleChargeExport')"/>
		
    </form>
	<tags:message content="${message}"/>
    <form id="tableForm" action="">
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
        <input type="text" style="display: none" hidden="hidden" name="msg" id="msg">
		<tr><th width="18px"><input id="checkAllOrNot" name="checkAllOrNot" type="checkbox"></th>
            <th><spring:message code='str143'/>ID</th><th><spring:message code='str138'/>ID</th><th><spring:message code='str7'/></th><th><spring:message code='str85'/></th><th><spring:message code='str54_4'/></th><th>总<spring:message code='str275'/></th><th><spring:message code='str710_0'/></th>
            <th><spring:message code='str711'/></th><th><spring:message code='str60'/></th><th><spring:message code='str102'/></th><th><spring:message code='str712'/></th><th><spring:message code='str713'/></th>
        </tr>
		<c:forEach items="${page.list}" var="role">
			<tr>
                <td><input type="checkbox" name="recordIds" value="${role.id}"></td>
                <td>${role.user_id}</td>
                <td>${role.role_id}</td>
                <td>${role.role_name}</td>
                <td>${role.level}</td>
                <td>${role.total_rmb_amount}</td>
                <td>${role.recharge_count}</td>
                <td>${role.max_rmb_amount}</td>
                <td>${role.surplus_treasure}</td>
                <td>${fns:parseLong(role.lastChargeTime)}</td>
                <td>${fns:parseLong(role.last_login_time)}</td>
                <td>${fns:getPastDays(role.lastChargeTime)}</td>
                <td>${fns:getPastDays(role.last_login_time)}</td>
			</tr>
		</c:forEach>
	</table>
        </form>
    <div class="pagination">${page}</div>
</body>
</html>
