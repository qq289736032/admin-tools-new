<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str1432'/></title>
	<meta name="decorator" content="default"/>
	<style>
		input#goods {
			width: 100%;
		}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/tools/config/limitList"><spring:message code='str1433'/>JTDD<spring:message code='str1434'/></a></li>
	</ul>
	
	<tags:message content="${message}"/>

	<table id="contentTable" class="datatable table table-striped table-bordered table-condensed">
		<thead>
			<tr><th><spring:message code='str1407'/></th><th><spring:message code='str718'/></th><th><spring:message code='str149'/></th></tr>
		</thead>
		<tbody>
			<tr>
				<td><spring:message code='str1435'/>:</td>
				<td><input name="jinbi" id="jinbi" value="${jinbi}"/></td>
				<td><a href="javascript:;" onclick='ajaxRequest("<spring:message code='str1393'/>?","${ctx}/tools/config/saveLimit",{jinbi:$("#jinbi").val()})'><spring:message code='str1216'/></a></td>
			</tr>
			<tr>
				<td><spring:message code='str1436'/>:</td>
				<td><input name="goods" id="goods" value="${goods}" width="100%"/></td>
				<td><a href="javascript:;" onclick='ajaxRequest("<spring:message code='str1393'/>?","${ctx}/tools/config/saveLimit",{goods:$("#goods").val()})'><spring:message code='str1216'/></a></td>
			</tr>
			<%--<tr>--%>
				<%--<td><spring:message code='str1437'/>list</td>--%>
				<%--<td>--%>
					<%--<textarea id="notice" htmlEscape="false" rows="3" class="input-xxlarge">${notice}</textarea>--%>
				<%--</td>--%>
				<%--<td><a href="javascript:;" onclick='ajaxRequest("<spring:message code='str1393'/>?","${ctx}/tools/config/saveLimit",{notice:$("#notice").val()})'><spring:message code='str1216'/></a></td>--%>
			<%--</tr>--%>
		</tbody>
	</table>

</body>
</html>
