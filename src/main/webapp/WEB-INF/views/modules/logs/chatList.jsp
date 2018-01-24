<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str690'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/dialog.jsp" %>
	<script type="text/javascript">
	function page(n,s){
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").attr("action","${ctx}/game/chat");
            $("#searchForm").submit();
            return false;
    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/game/chat"><spring:message code='str690'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/game/chat" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label><spring:message code='str2'/></label>
		<input type="text" id="roleName" name="roleName"  class="input-small" value="${paramMap.roleName}"/>

		<label><spring:message code='str714'/></label>
		<select id="channel" name="channel">
			<option value=""><spring:message code='str627'/></option>
			<c:forEach items="${fns:getDictList('chat_channel')}" var="channel">
				<option value="${channel.value}"<c:if test="${channel.value eq paramMap.channel}"></c:if> ><spring:message code='${channel.internationalKey}'/></option>
			</c:forEach>
		</select>
		<label><spring:message code='str715'/> <spring:message code='str4'/></label>
		<input type="text" id="chatWithName" name="chatWithName"  class="input-small" value="${paramMap.chatWithName}"/>

		<label><spring:message code='str3'/> <spring:message code='str4'/></label>
 		<input type="text" name="chatTimeStart" class="input_search" size="10" readonly="readonly" maxlength="20" value="${paramMap.chatTimeStart}" id="startDatePicker" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDatePicker\')}'})">
		-&nbsp;<input type="text" name="chatTimeEnd"   class="input_search" size="10" readonly="readonly" value="${paramMap.chatTimeEnd}"   id="endDatePicker" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatePicker\')}'})">		
		
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
		
		&nbsp;<input id="btnExport" class="btn btn-primary" type="button" value="<spring:message code='str5'/>" onclick="doExport('${ctx}/game/chat/export')"/>
	</form>
	<shiro:hasPermission name="game.role.jinyan">
        &nbsp;<input id="batchJinYan" class="btn btn-primary" type="button" value="<spring:message code='str139'/>" onclick="submitCheckedRecordIdsWithPrompt_2('${ctx}/game/role/batchJinYan')"/>
      </shiro:hasPermission>
       <shiro:hasPermission name="game.role.fenghao">
        &nbsp;<input id="batchFenHao" class="btn btn-primary" type="button" value="<spring:message code='str141'/>" onclick="submitCheckedRecordIdsWithPrompt_2('${ctx}/game/role/batchFengHao')"/>
		</shiro:hasPermission>
	<tags:message content="${message}"/>
	<form id="tableForm" action="">
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<tr><th width="18px"><input id="checkAllOrNot" name="checkAllOrNot" type="checkbox"></th><th><spring:message code='str7'/></th><th><spring:message code='str716'/></th><th><spring:message code='str717'/></th><th width="60%"><spring:message code='str718'/></th><th><spring:message code='str3'/></th></tr>
		<c:forEach items="${page.list}" var="item">
			<tr>
			 <td><input type="checkbox" name="recordIds" value="${item.roleId}"></td>
				<td>${item.roleName}</td>
				<td> <spring:message code='${fns:getDictKeys(item.channel, "chat_channel",item.channel)}'/></td>
				<td>${item.chatWithName}</td>
				<td>${item.content}</td>
                <td>${fns:parseLong(item.chatTime)}</td>
			</tr>
		</c:forEach>
	</table>
	</form>
	<div class="pagination">${page}</div>
</body>
</html>
