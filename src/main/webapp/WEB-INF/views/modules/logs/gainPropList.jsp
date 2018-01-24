<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title><spring:message code='str808'/></title>
    <meta name="decorator" content="default"/>
    <%@include file="/WEB-INF/views/include/dialog.jsp" %>

</head>
<body>

<form:form id="searchForm" modelAttribute="gainPropDetail" action="${ctx}/log/gainProp/" method="post" class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <input id="orderBy" name="orderBy" type="hidden" value="${page.orderBy}"/>
    <div>
    <label><spring:message code='str809'/></label><form:input path="userRoleId" htmlEscape="false" maxlength="50" class="input-small"/>
    &nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='str83'/>" onclick="return page();"/>
    &nbsp;<input id="btnExport" class="btn btn-primary" type="button" value="<spring:message code='str5'/>" onclick="doExport('${ctx}/log/gainProp/export')"/>
     </div>
</form:form>

<table id="contentTable" class="table table-striped table-bordered table-condensed table-hover">
    <thead><tr><th><spring:message code='str810'/></th><th><spring:message code='str811'/></th><th><spring:message code='str812'/></th><th><spring:message code='str813'/></th><th><spring:message code='str438'/></th><th><spring:message code='str814'/></th><th><spring:message code='str3'/></th></tr></thead>
    <tbody>
    <c:forEach items="${page.list}" var="gainProp">
        <tr>
            <td>${gainProp.userRoleId}</td>
            <td>${gainProp.userName}</td>
            <td>${gainProp.eventType}</td>
            <td>${gainProp.goodsId}</td>
            <td>${gainProp.goodsName}</td>
            <td>${gainProp.count}</td>
            <td><fmt:formatDate value="${gainProp.logTime}" type="both"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>
