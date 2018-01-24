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
		<li class="active"><a href="${ctx}/tools/activity/activityConfigList"><spring:message code='str1108'/></a></li>
		<li><a href="${ctx}/tools/activity/activityConfigForm?id=${activityConfig.id}"><c:if test="${not empty activityConfig.id}"><spring:message code='str1365'/></c:if><c:if test="${empty activityConfig.id}"><spring:message code='operation.add'/></c:if><spring:message code='str1111'/></a></li>
	</ul>
    <form:form id="searchForm" action="${ctx}/tools/activity/activityConfigList" method="post" class="breadcrumb form-search">
        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
        <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label><spring:message code='str1119'/></label>
		<input name="name" htmlEscape="false" maxlength="100" class="input-small"/>
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
    </form:form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed tooltip-demo">
		<tr>
		<th><spring:message code='str1112'/></th><th>Alias</th><th><spring:message code='str149'/></th></tr>
		<c:forEach items="${page.list}" var="item">
			<tr>
                <td>${item.name}</td>
                <td>${item.alias}</td>
                <td><a href="${ctx}/tools/activity/activityConfigForm?id=${item.id}">
					<ul class="the-icons clearfix">
						<li><i class="icon-edit"></i><spring:message code='str1365'/></li>
					</ul>
                </a></td>
			</tr>
		</c:forEach>
	</table>
	<div class="pagination">${page}</div>
    </form>
    
</body>
</html>
