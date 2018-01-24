<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str1217'/></title>
	<meta name="decorator" content="default"/>
    <%@include file="/WEB-INF/views/include/dialog.jsp" %>
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
		<li class="active"><a href="${ctx}/tools/gameEmail/"><spring:message code='str1157'/></a></li>
		<shiro:hasPermission name="game.email.edit">
			<li><a href="${ctx}/tools/gameEmail/form"><spring:message code='str1160'/></a></li>
            <li><a href="${ctx}/tools/gameEmail/applyEmail"><spring:message code='str1160'/>(<spring:message code='str1161'/>)</a></li>
		</shiro:hasPermission>
        <shiro:hasPermission name="game.email.batchadd">
            <li><a href="${ctx}/tools/gameEmail/batchAdd"><spring:message code='str1162'/></a></li>
        </shiro:hasPermission>
         <shiro:hasPermission name="game.email.name">
            <li><a href="${ctx}/tools/gameEmail/batchNameAdd"><spring:message code='str1162'/>(<spring:message code='str653'/>)</a></li>
        </shiro:hasPermission>
	</ul>
    <form:form id="searchForm" modelAttribute="gameEmail" action="${ctx}/tools/gameEmail/" method="post" class="breadcrumb form-search">
        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
        <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
        <label><spring:message code='str1323'/></label>
        <form:input path="title" htmlEscape="false" maxlength="100" class="input-small"/>
        <label><spring:message code='str1324'/></label>
        <form:input path="content" htmlEscape="false" maxlength="100" class="input-small"/>
        <label><spring:message code='str1325'/></label>
        <select name="emailStatus">
            <option value=""><spring:message code='str627'/></option>
                <c:forEach items="${fns:getDictList('notice_status')}" var="items">
                  <option value="${items.value }"><spring:message code='${items.internationalKey }'/> </option>
             </c:forEach>
          </select>
        <label><spring:message code='str1326'/></label>
         <input type="text" name="createTimeStart" class="input_search" size="10" readonly="readonly" maxlength="20" value="${paramMap.createTimeStart}" id="startDatePicker" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDatePicker\')}'})">
				-&nbsp;<input type="text" name="createTimeEnd"   class="input_search" size="10" readonly="readonly" value="${paramMap.createTimeEnd}"   id="endDatePicker" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatePicker\')}'})">
        &nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
        <shiro:hasPermission name="game.email.batchcancel">
        &nbsp;<input id="batchCancel" class="btn btn-primary" type="button" value="<spring:message code='str175'/>" onclick="submitCheckedRecordIds('${ctx}/tools/gameEmail/batchCancel')"/>
        </shiro:hasPermission>
        <shiro:hasPermission name="game.email.batchrecover">
        &nbsp;<input id="batchRecover" class="btn btn-primary" type="button" value="<spring:message code='str1327'/>" onclick="submitCheckedRecordIds('${ctx}/tools/gameEmail/batchRecover')"/>
        </shiro:hasPermission>
        </div>
    </form:form>
	<tags:message content="${message}"/>
    <form id="tableForm" action="">
	<table id="contentTable" class="table table-striped table-bordered table-condensed tooltip-demo">
		<tr><th width="18px"><input id="checkAllOrNot" name="checkAllOrNot" type="checkbox"></th>
            <th><spring:message code='str863'/></th><th width="18%"><spring:message code='str866'/></th><th><spring:message code='str1328'/></th><th><spring:message code='str1172'/></th><th><spring:message code='str867'/></th><th style="display: none"><spring:message code='str867'/></th><th><spring:message code='str1329'/></th><th><spring:message code='str1330'/></th><th><spring:message code='str1331'/>ID<spring:message code='str1332'/></th><th><spring:message code='str1333'/></th><th><spring:message code='str1334'/></th><th width="10%"><spring:message code='str3'/></th><th><spring:message code='str1294'/></th><th><spring:message code='str149'/></th></tr>
		<c:forEach items="${page.list}" var="gameEmail" varStatus="status">
			<tr>
                <td><input type="checkbox" name="recordIds" value="${gameEmail.id}"></td>
                <td>${gameEmail.title}</td>
                <td>${gameEmail.content}</td>
                <td>
                    <c:if test="${gameEmail.isGlobal == 1}"><spring:message code='str1347'/></c:if>
                    <c:if test="${gameEmail.isGlobal == 0}"><spring:message code='str1348'/></c:if>
                </td>
                <td>${gameEmail.yb }</td>
                <td><c:if test="${gameEmail.attachments != '' && gameEmail.attachments != '[]'}"><a type="button" href="#" class="btn btn-primary btn-small" id="btn_${status.index+1}"  onclick="viewAttach(${status.index+1})"><spring:message code='str872'/></a></c:if> </td>
                <td style="display: none" id="hide_${status.index + 1}">${fns:transformationGoodNames(gameEmail.attachments)}</td>
                <td style="overflow:hidden;white-space:nowrap;"><a title="${gameEmail.serverIds}">${gameEmail.serverIds}</a></td>
                <td style="overflow:hidden;white-space:nowrap;"><a title="${gameEmail.receiverNames}">${gameEmail.receiverNames}</a> </td>
                <td style="overflow:hidden;white-space:nowrap;"> <a title="${gameEmail.receiverUserIds}">${gameEmail.receiverUserIds}</a> </td>
                <td>${gameEmail.createName}</td>
                <td>${gameEmail.approveName}</td>
                <td><fmt:formatDate value="${gameEmail.createTime}" type="both"/></td>
                <td>
                    <c:choose>
                        <c:when test="${gameEmail.emailStatus == 2}"><span class="label label-warning"><spring:message code="${fns:getDictKeys(gameEmail.emailStatus, 'email_status', gameEmail.emailStatus)}"  /></span></c:when>
                        <c:when test="${gameEmail.emailStatus == 1}"><span class="label label-success"><spring:message code="${fns:getDictKeys(gameEmail.emailStatus, 'email_status', gameEmail.emailStatus)}"  /></span></c:when>
                        <c:when test="${gameEmail.emailStatus == 0}"><span class="label label-info"><spring:message code="${fns:getDictKeys(gameEmail.emailStatus, 'email_status', gameEmail.emailStatus)}"  /></span></c:when>
                        <c:otherwise><spring:message code="${fns:getDictKeys(gameEmail.emailStatus, 'email_status', gameEmail.emailStatus)}"  /></c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <c:if test="${gameEmail.emailStatus == 0}">
					<shiro:hasPermission name="game.email.send">
                        <a href="${ctx}/tools/gameEmail/send?id=${gameEmail.id}" onclick="return confirmx('<spring:message code='str1335'/>', this.href)"><spring:message code='str1336'/></a>
					</shiro:hasPermission>
					<shiro:hasPermission name="game.email.cancel">
                        <a href="${ctx}/tools/gameEmail/cancel?id=${gameEmail.id}" onclick="return confirmx('<spring:message code='str1337'/>', this.href)"><spring:message code='str175'/></a>
					</shiro:hasPermission>
                    </c:if>
                    <c:if test="${gameEmail.emailStatus == 2}">
                        <a href="${ctx}/tools/gameEmail/recover?id=${gameEmail.id}" onclick="return confirmx('<spring:message code='str1338'/>', this.href)"><spring:message code='str1327'/></a>
                    </c:if>
				</td>
			</tr>
		</c:forEach>
	</table>
	<div class="pagination">${page}</div>
    </form>
    
</body>
</html>
