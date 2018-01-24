<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title><spring:message code='str1301'/></title>
    <meta name="decorator" content="default"/>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/tools/feedback/"><spring:message code='str1302'/></a></li>
</ul>
<form id="searchForm"  action="${ctx}/tools/feedback/" method="post" class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

    <div>
        <label><spring:message code='str1303'/>:</label>
        <select id="feedbackType" name="feedbackType">
            <option value=""><spring:message code='str627'/></option>
            <c:forEach items="${fns:getDictList('feedback_type')}" var="feedbackType">
                <option value="${feedbackType.value}"><spring:message code="${feedbackType.internationalKey}"/></option>
            </c:forEach>
        </select>
        <label><spring:message code='str47'/></label>
        <input type="text" id="keywords" name="keywords"  class="input-small" value="${paramMap.keywords}"/>
        <label><spring:message code='str1304'/> <spring:message code='str4'/></label>
        <input type="text" name="feedbackTimeStart" class="input-small" readonly="readonly" maxlength="20" value="${paramMap.feedbackTimeStart}" id="startDatePicker" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDatePicker\')}'})">
		-&nbsp;<input type="text" name="feedbackTimeEnd"   class="input-small" readonly="readonly" value="${paramMap.feedbackTimeEnd}"   id="endDatePicker" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatePicker\')}'})">		
        &nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='str83'/>" onclick="return page();"/>
     </div>
</form>

<table id="contentTable" class="table table-striped table-bordered table-condensed table-hover">
    <thead><tr><th><spring:message code='str1168'/></th><th><spring:message code='str718'/></th><th><spring:message code='str1303'/></th><th><spring:message code='str56'/></th><th><spring:message code='str7'/></th><th>VIP<spring:message code='str85'/></th><th><spring:message code='str1305'/></th><th><spring:message code='str3'/></th><th><spring:message code='str149'/></th></tr></thead>
    <tbody>
    <c:forEach items="${page.list}" var="item">
        <tr>
            <td>${item.title}</td>
            <td width="20%">${item.content}</td>
            <td><spring:message code="${fns:getDictKeys(item.type,'feedback_type',item.type)}"/></td>
            <td>${item.platformId}</td>
            <td>${item.roleName}</td>
            <td>${item.vipLevel}</td>
            <td>${item.totalRecharge}</td>
            <td><fmt:formatDate value="${item.feedbackTime}" type="both"/></td>
            <td>
				<shiro:hasPermission name="game.feedback.reply">
                <a href="<c:url value='${fns:getAdminPath()}/tools/feedback/feedbackForm?id=${item.id}&serverId=${item.serverId}&roleId=${item.roleId}'><c:param name='roleName' value='${fns:urlEncode(item.roleName)}'/></c:url>"><spring:message code='str1300'/></a>
				</shiro:hasPermission>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>
