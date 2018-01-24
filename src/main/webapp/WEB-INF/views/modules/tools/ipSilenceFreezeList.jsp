<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>ip<spring:message code='str1197'/></title>
	<meta name="decorator" content="default"/>

	<link href="${ctxStatic}/bootstrap-switch/dist/css/bootstrap2/bootstrap-switch.css" type="text/css" rel="stylesheet" />
	<script src="${ctxStatic}/bootstrap-switch/dist/js/bootstrap-switch.js" type="text/javascript"></script>
	<script src="${ctxStatic}/bootstrap-switch/dist/js/highlight.js" type="text/javascript"></script>
	<script src="${ctxStatic}/bootstrap-switch/dist/js/main.js" type="text/javascript"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/tools/black/ipSilenceFreeze">ip<spring:message code='str1197'/></a></li>
		<li><a href="${ctx}/tools/black/form"><spring:message code='str1191'/>IP</a></li>
		<li><a href="${ctx}/tools/black/userBlackList"><spring:message code='str1408'/></a></li>
		<li><a href="${ctx}/tools/black/userForm"><spring:message code='str1409'/></a></li>
	</ul>

	<form id="searchForm" action="${ctx}/tools/black/ipSilenceFreeze" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>IP<spring:message code='str1001'/> <spring:message code='str4'/></label>
		<input type="text" id="ip" name="ip"  class="input-small" value="${paramMap.ip}"/>
		<label><spring:message code='str3'/> <spring:message code='str4'/></label>
		<input type="text" name="createDateStart" class="input-small" readonly="readonly" maxlength="20" value="${paramMap.createDateStart}" id="startDatePicker" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDatePicker\')}'})">
		-&nbsp;<input type="text" name="createDateEnd"   class="input-small" readonly="readonly" value="${paramMap.createDateEnd}"   id="endDatePicker" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatePicker\')}'})">			
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<tr><th>ip</th><th><spring:message code='str1410'/></th><th><spring:message code='str1411'/></th><th><spring:message code='str78'/></th><th><spring:message code='str1412'/></th><th><spring:message code='str149'/></th></tr>
		<c:forEach items="${page.list}" var="item">
			<tr>
				<td>${item.ip}</td>
				<td>
					<input type="checkbox" ip="${item.ip}" operatorType="silence"  data-size="small" name="my-checkbox" <c:if test="${item.silence == 1}">checked</c:if>>
				</td>
				<td>
					<input type="checkbox" ip="${item.ip}" operatorType="freeze" data-size="small" name="my-checkbox" <c:if test="${item.freeze == 1}">checked</c:if>>
				</td>
				<td>
					${item.expireTime}
				</td>
				<td>${item.createTime}</td>
				<td>
					<a href="${ctx}/tools/black/delete?ip=${item.ip}" onclick="return confirmx('<spring:message code='str1199'/>', this.href)"><spring:message code='str22'/></a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<div class="pagination">${page}</div>

	<script type="text/javascript">

		$(document).ready(function() {

			$('input[name="my-checkbox"]').on('switchChange.bootstrapSwitch', function(event, state) {
				var ip = this.attributes.ip.nodeValue;
				var flag = state == true? 1 : 0;
				var type = this.attributes.operatorType.nodeValue;
				_remoteCall("${ctx}/tools/black/updateStatus",{ip:ip,type:type,flag:flag},function(result){
					if(result.success){
						top.$.jBox.tip("<spring:message code='str1200'/>!", 'success');
					}
				})
			});
		})

	</script>
</body>

</html>
