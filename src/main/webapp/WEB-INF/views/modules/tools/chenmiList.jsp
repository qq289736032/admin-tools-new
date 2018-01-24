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
		<li class="active"><a href="${ctx}/tools/config/chenmi"><spring:message code='str1194'/></a></li>
		<li><a href="${ctx}/tools/config/chenmiForm"><spring:message code='str1191'/></a></li>
	</ul>
	<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
	<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<tr><th><spring:message code='str56'/></th><th><spring:message code='str1198'/></th><th><spring:message code='str149'/></th></tr>
		<c:forEach items="${map}" var="item">
			<tr>
				<td>${item.key}</td>
				<td>
					<input type="checkbox" pid="${item.key}"  data-size="small" name="my-checkbox" <c:if test="${item.value}">checked</c:if>>
				</td>
				<td>
					<a href="${ctx}/tools/config/deleteChenMi?pid=${item.key}" onclick="return confirmx('<spring:message code='str1199'/>', this.href)"><spring:message code='str22'/></a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<script type="text/javascript">

		$(document).ready(function() {

			$('input[name="my-checkbox"]').on('switchChange.bootstrapSwitch', function(event, state) {
				var pid = this.attributes.pid.nodeValue;
				var flag = state == true? 1 : 0;
				_remoteCall("${ctx}/tools/config/updateChenMi",{pid:pid,flag:flag},function(result){
					if(result.success){
						top.$.jBox.tip("<spring:message code='str1200'/>!", 'success');
					}
				})
			});
		})

	</script>
</body>

</html>
