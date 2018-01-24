<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str1217'/></title>
	<meta name="decorator" content="default"/>
    <script type="text/javascript">
       $(function(){
           $(".tipDiv").tooltip('show');
       });

    </script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/tools/gameNotice/"><spring:message code='str1339'/></a></li>
		<shiro:hasPermission name="game.notice.edit"><li><a href="${ctx}/tools/gameNotice/form"><spring:message code='str998'/></a></li></shiro:hasPermission>
	</ul>
    <form:form id="searchForm" modelAttribute="gameNotice" action="${ctx}/tools/gameNotice/" method="post" class="breadcrumb form-search">
        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
        <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
        <label><spring:message code='str1324'/></label>
        <form:input path="content" htmlEscape="false" maxlength="100" class="input-small"/>
        <label><spring:message code='str1325'/></label>
         <select name="noticeStatus">
            <option value=""><spring:message code='str627'/></option>
                <c:forEach items="${fns:getDictList('notice_status')}" var="items">
                  <option value="${items.value }"><spring:message code='${items.internationalKey }'/> </option>
             </c:forEach>
          </select>
        <label><spring:message code='str1345'/>:</label>
        <form:input path="stepTime" htmlEscape="false" maxlength="50" class="input-small"/>
        <label><spring:message code='str1349'/></label>
        <input type="text" name="startTimeStart" class="input_search" size="10" readonly="readonly" maxlength="20" value="${paramMap.startTimeStart}" id="startDatePicker" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDatePicker\')}'})">
		-&nbsp;<input type="text" name="startTimeEnd"   class="input_search" size="10" readonly="readonly" value="${paramMap.startTimeEnd}"   id="endDatePicker" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatePicker\')}'})">
        
        &nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
        </div>
    </form:form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed" style="table-layout:fixed;" >
		<tr><th><spring:message code='str1341'/></th><th width="20%"><spring:message code='str1342'/></th><th><spring:message code='str14'/></th><th><spring:message code='str1350'/></th><th><spring:message code='str1351'/></th><th><spring:message code='str1352'/></th><th><spring:message code='str1353'/></th><th><spring:message code='str1294'/></th><th><spring:message code='str1354'/></th><th><spring:message code='str101'/></th><th><spring:message code='str149'/></th></tr>
		<c:forEach items="${page.list}" var="gameNotice">
			<tr>
				<td><spring:message code="${fns:getDictKeys(gameNotice.noticeType, 'notice_type', gameNotice.noticeType)}"/></td></td>
                <td style="WORD-WRAP: break-word">${gameNotice.content}</td>
                <td style="overflow:hidden;">
                    <c:if test="${gameNotice.isGlobal == 1}"><spring:message code='str1347'/></c:if>
                    <c:if test="${gameNotice.isGlobal == 0}"><a href="#" name="tipDiv" data-toggle="tooltip" title="${gameNotice.serverIds}">${gameNotice.serverIds}</a></c:if>
                </td>
                <td>${gameNotice.repeatCount}</td>
                <td>${gameNotice.stepTime}</td>
                <td><fmt:formatDate value="${gameNotice.startTime}" type="both"/></td>
                <td><fmt:formatDate value="${gameNotice.finishTime}" type="both"/></td>
                <td>
                    <c:choose>
                        <c:when test="${gameNotice.noticeStatus == 4}"><span class="label label-warning"><spring:message code="${fns:getDictKeys(gameNotice.noticeType, 'notice_type', gameNotice.noticeType)}"/></span></c:when>
                        <c:when test="${gameNotice.noticeStatus == 1}"><span class="label label-success"><spring:message code="${fns:getDictKeys(gameNotice.noticeType, 'notice_type', gameNotice.noticeType)}"/></span></c:when>
                        <c:when test="${gameNotice.noticeStatus == 0}"><span class="label label-info"><spring:message code="${fns:getDictKeys(gameNotice.noticeType, 'notice_type', gameNotice.noticeType)}"/></span></c:when>
                        <c:otherwise><spring:message code="${fns:getDictKeys(gameNotice.noticeType, 'notice_type', gameNotice.noticeType)}"/></c:otherwise>
                    </c:choose>
                </td>
                <td>${gameNotice.createName}</td>
                <td><fmt:formatDate value="${gameNotice.createDate}" type="both"/></td>
				<td>
                    <shiro:hasPermission name="game.notice.publish">
					<c:if test="${gameNotice.noticeStatus == 0}"><a href="${ctx}/tools/gameNotice/publish?id=${gameNotice.id}" onclick="return confirmx('<spring:message code='str1355'/>', this.href)"><spring:message code='str1356'/></a></c:if>
					</shiro:hasPermission>
					<%--<a href="${ctx}/tools/gameNotice/form?id=${gameNotice.id}"><spring:message code='str1297'/></a>--%>
                    <c:set var="nowDate" value="<%=System.currentTimeMillis()%>"></c:set>
                    <shiro:hasPermission name="game.notice.delete">
                    <c:if test="${(gameNotice.finishTime.time - nowDate >= 0) && (gameNotice.noticeStatus == 1 || gameNotice.noticeStatus == 2 || gameNotice.noticeStatus == 0)}">
                        <a href="${ctx}/tools/gameNotice/delete?id=${gameNotice.id}" onclick="return confirmx('<spring:message code="str1357"/>', this.href)"><spring:message code='str175'/></a>
                    </c:if>
					</shiro:hasPermission>
				</td>
			</tr>
		</c:forEach>
	</table>
    <div class="pagination">${page}</div>
</body>
</html>
