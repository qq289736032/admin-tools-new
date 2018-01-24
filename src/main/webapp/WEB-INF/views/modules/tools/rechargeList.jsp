<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str1489'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/dialog.jsp" %>
	
	<style>
		table{table-layout: fixed;}
		/*td{word-break: break-all; word-wrap:break-word;height:15px;overflow: hidden} */
		td{layout:fixed; overflow: hidden ; line-height: 20px;}


	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/tools/recharge/"><spring:message code='str967'/></a></li>
		<shiro:hasPermission name="game.recharge.apply">
			<li><a href="${ctx}/tools/recharge/form"><spring:message code='str1490'/></a></li>
			<li><a href="${ctx}/tools/recharge/roleIdRechargeForm"><spring:message code='str1480'/>ID<spring:message code='str1473'/></a></li>
		</shiro:hasPermission>
		<shiro:hasPermission name="game.recharge.add">
			<li><a href="${ctx}/tools/recharge/add"><spring:message code='str1481'/></a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="recharge" action="${ctx}/tools/recharge/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label><spring:message code='str1491'/></label>
		<select name="rechargeStatus">
            <option value=""><spring:message code='str627'/></option>
                <c:forEach items="${fns:getDictList('recharge_status')}" var="items">
                  <option value="${items.value }"
                  <c:if test="${items.value == paramMap.rechargeStatus }">selected = "selected"</c:if>
                  ><spring:message code='${items.internationalKey }'/> </option>
             </c:forEach>
             
          </select>
		<label><spring:message code='str2'/></label>
		<form:input path="roleNames" htmlEscape="false" maxlength="100" class="input-small"/>
		<label><spring:message code='str55'/></label>
		<form:input path="serverId" htmlEscape="false" maxlength="100" class="input-small"/>
		<label><spring:message code='str1492'/></label>
		<select name="rechargeType">
            <option value=""><spring:message code='str627'/></option>
                <c:forEach items="${fns:getDictList('recharge_status')}" var="items">
                  <option value="${items.value }"
                  <c:if test="${items.value == paramMap.rechargeType }">selected = "selected"</c:if>
                  ><spring:message code='${items.internationalKey }'/> </option>
             </c:forEach>
          </select>
		<label><spring:message code='str1493'/></label>
		<select name="moneyType">
            <option value=""><spring:message code='str627'/></option>
                <c:forEach items="${fns:getDictList('recharge_status')}" var="items">
                  <option value="${items.value }"
                  <c:if test="${items.value == paramMap.moneyType }">selected = "selected"</c:if>
                  ><spring:message code='${items.internationalKey }'/> </option>
             </c:forEach>
          </select>
		<br/><br/>
		<label><spring:message code='str1326'/></label>
		<input type="text" name="createTimeStart" class="input-small" readonly="readonly" maxlength="20" value="${paramMap.createTimeStart}" id="startDatePicker" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDatePicker\')}'})">
		-&nbsp;<input type="text" name="createTimeEnd"   class="input-small" readonly="readonly" value="${paramMap.createTimeEnd}"   id="endDatePicker" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatePicker\')}'})">		
		<div class="">
			<br/>
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
		<shiro:hasPermission name="game.recharge.batchadd">
			&nbsp;<input id="batchAdd" class="btn btn-primary" type="button" value="<spring:message code='str1494'/>" onclick="submitCheckedRecordIds('${ctx}/tools/recharge/batchAdd')"/>
		</shiro:hasPermission>
		<shiro:hasPermission name="game.recharge.batchcancel">
			&nbsp;<input id="batchCancel" class="btn btn-primary" type="button" value="<spring:message code='str175'/>" onclick="submitCheckedRecordIdsAjax('${ctx}/tools/recharge/batchCancel')"/>
		</shiro:hasPermission>
		<shiro:hasPermission name="game.recharge.batchrecover">
			&nbsp;<input id="batchRecover" class="btn btn-primary" type="button" value="<spring:message code='str1327'/>" onclick="submitCheckedRecordIdsAjax('${ctx}/tools/recharge/batchRecover')"/>
		</shiro:hasPermission>
		</div>
	</form:form>
	<tags:message content="${message}"/>
	<form id="tableForm" action="">
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<tr><th width="18px"><input id="checkAllOrNot" name="checkAllOrNot" type="checkbox"></th><th><spring:message code='str14'/></th><th width="18%"><spring:message code='str138'/>ID</th><th width="18%"><spring:message code='str7'/></th><th><spring:message code='str970'/></th><th><spring:message code='str888'/></th><th><spring:message code='str440'/></th><th><spring:message code='str973'/></th><th><spring:message code='str1495'/></th><th><spring:message code='str1496'/></th><th><spring:message code='str729'/></th><th><spring:message code='str1497'/></th></tr>
		<c:forEach items="${page.list}" var="item">
			<tr>
				<td><input type="checkbox" name="recordIds" value="${item.id}"></td>
				<td>${item.serverId}</td>
				<td style="overflow: hidden;white-space: nowrap"><span title="${item.roleIds}">${item.roleIds}</span></td>
				<td style="overflow: hidden;white-space: nowrap"><span title="${item.roleNames}">${item.roleNames}</span></td>
				<td>
					<c:choose>
						<c:when test="${item.rechargeType == 3}"><span class="label label-important"><spring:message code="${fns:getDictKeys(item.rechargeType, 'recharge_type', item.rechargeType)}"   /></span></c:when>
						<c:when test="${item.rechargeType == 2}"><span class="label label-warning"><spring:message code="${fns:getDictKeys(item.rechargeType, 'recharge_type', item.rechargeType)}"   /></span></c:when>
						<c:when test="${item.rechargeType == 1}"><span class="label label-success"><spring:message code="${fns:getDictKeys(item.rechargeType, 'recharge_type', item.rechargeType)}"   /></span></c:when>
						<c:when test="${item.rechargeType == 0}"><span class="label label-info"><spring:message code="${fns:getDictKeys(item.rechargeType, 'recharge_type', item.rechargeType)}"   /></span></c:when>
						<c:otherwise>${fns:getDictKeys(item.rechargeType, 'recharge_type', item.rechargeStatus)}</c:otherwise>
					</c:choose>
				</td>
				<td>${fns:getDictKeys(item.moneyType, 'money_type', item.moneyType)}</td>
				<td>${item.moneyNum}</td>
				<td><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
				<td>${item.createName} </td>
				<td>${item.approveName} </td>
				<td><div title="${item.remark}">${item.remark}</div> </td>
				<td>
					<c:choose>
						<c:when test="${item.rechargeStatus == 2}"><span class="label label-warning"><spring:message  code="${fns:getDictKeys(item.rechargeStatus, 'recharge_status', item.rechargeStatus)}"/></span></c:when>
						<c:when test="${item.rechargeStatus == 1}"><span class="label label-success"><spring:message  code="${fns:getDictKeys(item.rechargeStatus, 'recharge_status', item.rechargeStatus)}"/></span></c:when>
						<c:when test="${item.rechargeStatus == 0}"><span class="label label-info"><spring:message  code="${fns:getDictKeys(item.rechargeStatus, 'recharge_status', item.rechargeStatus)}"/></span></c:when>
						<c:otherwise><spring:message  code="${fns:getDictKeys(item.rechargeStatus, 'email_status', item.rechargeStatus)}"/></c:otherwise>
					</c:choose>
				</td>
			</tr>
		</c:forEach>
	</table>
	</form>
	<div class="pagination">${page}</div>
</body>
</html>
