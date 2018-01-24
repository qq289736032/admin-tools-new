<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title><spring:message code="game.server.list"/></title>
    <meta name="decorator" content="default"/>
    <%@include file="/WEB-INF/views/include/dialog.jsp" %>
    <style>
        .selected{background-color:#ae0!important}
    </style>
</head>
<body>
    <ul class="nav nav-tabs">
        <li class="active"><a href="${ctx}/tools/gameSever/"><spring:message code="game.server.list"/></a></li>
        <li><a href="${ctx}/tools/gameServer/form"><spring:message code="operation.add"/>&nbsp;<spring:message code="game.server"/></a></li>
    </ul>
    <form:form id="searchForm" modelAttribute="gameServer" action="${ctx}/tools/gameServer/" method="post" class="breadcrumb form-search">
        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
        <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
        <label><spring:message code="game.platform"/><spring:message code='str4'/></label>
        <form:select path="gamePlatform.id">
            <form:options items="${fns:getGamePlatformList()}" itemLabel="name" itemValue="id" htmlEscape="false"/>
        </form:select>
        <label><spring:message code="game.server.id"/><spring:message code='str4'/></label><form:input path="serverId" htmlEscape="false" maxlength="50" class="input-small"/>
        &nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
    </div>
    </form:form>
    <table id="contentTable" class="table table-bordered table-condensed">
        <thead><tr><th><spring:message code="belong.paltform"/></th><th><spring:message code="game.server.id"/></th><th><spring:message code="game.server.name"/></th>
            <th><spring:message code="is.test.server"/></th><th><spring:message code="is.hefu"/></th>
            <th><spring:message code="game.server.db"/></th><th><spring:message code="log.server.db"/></th>
            <th><spring:message code="game.server.gate"/></th><th><spring:message code="game.server.interface"/></th>
            <%--<th><spring:message code="game.server.open.time"/></th>--%>
            <th><spring:message code="operation"/></th></tr></thead>
        <tbody>
        <c:forEach items="${page.list}" var="gameServer" varStatus="status">
            <tr>
                <td>${gameServer.gamePlatform.name}</td>
                <td>${gameServer.serverId}</td>
                <td>${gameServer.serverName}</td>
                <td><c:choose><c:when test="${gameServer.isTest == 0}">No</c:when><c:otherwise>Yes</c:otherwise></c:choose></td>
                <td><c:choose><c:when test="${gameServer.isHefu == 0}">No</c:when><c:otherwise>Yes</c:otherwise></c:choose></td>
                <td>${gameServer.gameDbUrl}</td>
                <td>${gameServer.logDbUrl}</td>
                <td>${gameServer.gateUrl}</td>
                <td>${gameServer.gameServerRemoteUrl}</td>
                <%--<td>${gameServer.serverOpenTime}</td>--%>
                <td>
                    <a href="${ctx}/tools/gameServer/form?id=${gameServer.id}">
                        <ul class="the-icons clearfix">
                            <li><i class="icon-edit"></i><spring:message code='str1365'/></li>
                        </ul>
                    </a>
                    <a href="${ctx}/tools/gameServer/delete?id=${gameServer.id}" onclick="return confirmx('<spring:message code='str1389'/>', this.href)"><spring:message code='str22'/></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="pagination">${page}</div>

<script type="text/javascript">

</script>
</body>
</html>
