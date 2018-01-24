<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str1108'/></title>
	<meta name="decorator" content="default"/>
    <script type="text/javascript">
//       $(function(){
//           $('.tooltip-demo').tooltip({
//               selector: "a[data-toggle=tooltip]"
//           })
//       });

    </script>
    <style type="text/css">
        table{table-layout: fixed;}
    </style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/tools/virtual/"><spring:message code='str1413'/></a></li>
		<shiro:hasPermission name="apply.virtual.point">
			<li><a href="${ctx}/tools/virtual/form"><spring:message code='str1414'/></a></li>
		</shiro:hasPermission>
		<li class="active"><a href="${ctx}/tools/virtual/itemList"><spring:message code='str1413'/></a></li>
		<shiro:hasPermission name="add.virtual.goods">
			<li><a href="${ctx}/tools/virtual/itemForm"><spring:message code='str1415'/></a></li>
		</shiro:hasPermission>
	</ul>
    <%--<form:form id="searchForm" action="${ctx}/tools/virtual/itemList" method="post" class="breadcrumb form-search">--%>
        <%--<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>--%>
        <%--<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>--%>
		<%--<label><spring:message code='str1119'/></label>--%>
		<%--<input name="name" htmlEscape="false" maxlength="100" class="input-small"/>--%>
		<%--<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>--%>
    <%--</form:form>--%>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed tooltip-demo">
		<tr>
		<th><spring:message code='str1416'/></th><th><spring:message code='str1417'/></th><th><spring:message code='str149'/></th></tr>
		<c:forEach items="${page.list}" var="item">
			<tr>
                <td>${item.item_id}</td>
                <td>${item.name}</td>
                <td>
					<shiro:hasPermission name="delete.virtual.goods">
						<a href="${ctx}/tools/virtual/deleteItem?id=${item.id}"><spring:message code='str22'/></a>
					</shiro:hasPermission>
				</td>
			</tr>
		</c:forEach>
	</table>
	<div class="pagination">${page}</div>
    </form>
    
</body>
</html>
