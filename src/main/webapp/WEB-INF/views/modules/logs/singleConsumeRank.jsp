<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title><spring:message code='str766'/></title>
    <meta name="decorator" content="default"/>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctx}/log/rank/consumeRank"><spring:message code='str766'/></a></li>
    <li class="active"><a href="${ctx}/log/rank/singleConsumeRank"><spring:message code='str767'/></a></li>
</ul>
<form id="searchForm" action="${ctx}/log/rank/singleConsumeRank" method="post" class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

    <label><spring:message code='str3'/> <spring:message code='str4'/></label>
    <input name="createDateStart" class="input-small required" readonly="readonly" value="${paramMap.createDateStart}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>

    <%--<label><spring:message code='str55'/></label>--%>
    <%--<input id="serverIds" name="serverIds" value="${paramMap.serverIdList}" readonly="readonly"/>--%>
    <%--<input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="openSeverDialog();">--%>
    &nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
</form>
<tags:message content="${message}"/>
<form id="tableForm" action="">
    <table id="consumeRank" class="datatable table table-striped table-bordered table-condensed">
        <thead>
        <th><spring:message code='str768'/></th>
        <th><spring:message code='str138'/>ID</th>
        <th><spring:message code='str7'/></th>
        <th><spring:message code='str769'/></th>
        <th><spring:message code='str770'/></th>
        <th><spring:message code='str771'/></th>
        <th>7<spring:message code='str772'/></th>
        <th>30<spring:message code='str772'/></th>
        <th>vip<spring:message code='str85'/></th>
        <th><spring:message code='str773'/></th>
        <th><spring:message code='str774'/></th>
        <th><spring:message code='str775'/></th>
        <th><spring:message code='str776'/></th>
        <th><spring:message code='str777'/></th>
        </thead>
        <tbody>
        <c:forEach items="${page.list}" var="item">
            <tr>
                <td>${item.area_id}</td>
                <td>${item.role_id}</td>
                <td>${item.role_name}</td>
                <td></td>
                <td>${item.tca}</td>
                <td>${item.today_consume}</td>
                <td>${item.seven_day_consume}</td>
                <td>${item.thirty_day_consume}</td>
                <td>${fns:getVipLevel(item.vip_level)}</td>
                <td><fmt:formatDate value="${item.create_role_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td><fmt:formatDate value="${item.last_login_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td><fmt:formatDate value="${item.first_consume_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td><fmt:formatDate value="${item.last_consume_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td>${item.surplus_treasure}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="pagination">${page}</div>
</form>


<script type="text/javascript">

</script>

</body>
</html>
