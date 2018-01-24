<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str70'/></title>
	<meta name="decorator" content="default"/>

	<script type="text/javascript">
		function bag(){
			location.href = "${ctx}/game/role/bag?roleId=${roleId}&serverId=${serverId}";
		}
        function delItem(itemId, count) {
        	var roleId = ${roleId};
        	var serverId = ${serverId};
            $.ajax({
                url: '${ctx}/game/role/deleteItem',
                data: {id: roleId, itemId: itemId, count: count, serverId: serverId},
                type: 'post',
                dataType: 'json',
                success: function (data) {
                	if(data) {
	                	bag();
                	} else {
	                    alert("<spring:message code='str1674'/>");
                	}
                }, error: function (request, status, e) {
                	alert("<spring:message code='str1674'/>");
                }

            });
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/game/role/"><spring:message code='str71'/></a></li>
		<li><a href="${ctx}/game/role/form?id=${roleId}"><spring:message code='str72'/></a></li>
		<li class="active"><a><spring:message code='str73'/></a></li>
	</ul><br/>



	<div class="panel panel-primary">
		<div class="panel-heading"><spring:message code='str73'/></div>
		<table id="itemsTable" class="table table-striped table-bordered table-condensed">
			<tr>
				<th><spring:message code='str74'/></th>
				<th><spring:message code='str75'/></th>
				<th><spring:message code='str76'/></th>
				<th><spring:message code='str77'/></th>
				<th><spring:message code='operation'/></th>
			</tr>
			<c:forEach items="${items}" var="item">
				<tr>
					<td>${fns:getGoodName(item.template_id)}</td>
					<td>${item.count}</td>
					<td>${item.bind}</td>
					<td>${item.rare_level}</td>
					<td><input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.delete"/>" onclick="delItem(${item.id}, ${item.count})"/></td>
				</tr>
			</c:forEach>
		</table>
	</div>


</body>
</html>
