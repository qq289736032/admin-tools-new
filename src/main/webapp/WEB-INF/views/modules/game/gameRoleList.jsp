<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str71'/></title>
	<meta name="decorator" content="default"/>
	  <%@include file="/WEB-INF/views/include/dialog.jsp" %>
    <script type="text/javascript">
//       $(function(){
//           $("#tipDiv").tooltip('show');
//       });
 function page(n,s){
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").attr("action","${ctx}/game/role/");
            $("#searchForm").submit();
            return false;
    }

    </script>
    <style type="text/css">
      td.ui-dialog-body {
            height: auto;
     }
    </style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/game/role/"><spring:message code='str71'/></a></li>
	</ul>
    <form id="searchForm" action="${ctx}/game/role/" method="post" class="breadcrumb form-search">
        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
        <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
        
        <label><spring:message code='str2'/></label>
        <input type="text" id="name" name="name"  class="input-small" value="${paramMap.name}"/>
        
        <label><spring:message code='str80'/></label>
        <input type="text" id="userId" name="userId"  class="input-small" value="${paramMap.userId}"/>
        
        <label><spring:message code='str138'/>ID<spring:message code='str4'/></label>
        <input type="text" id="roleId" name="roleId"  class="input-small" value="${paramMap.roleId}"/>
        
        <label><spring:message code='str82'/></label>
        <input type="text" name="startLevel" class="input-small" value="${paramMap.startLevel}"> - <input type="text" class="input-small" name="endLevel" value="${paramMap.endLevel}">
        &nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
        <div class="">
        <br/>
		<shiro:hasPermission name="game.role.jinyan">
        &nbsp;<input id="batchJinYan" class="btn btn-primary" type="button" value="<spring:message code='str139'/>" onclick="submitCheckedRecordIdsWithPrompt_2('${ctx}/game/role/batchJinYan')"/>
        &nbsp;<input id="batchCancelJinYan" class="btn btn-primary" type="button" value="<spring:message code='str140'/>" onclick="submitCheckedRecordIdsWithPrompt('${ctx}/game/role/batchCancelJinYan')"/>
        </shiro:hasPermission>
        <shiro:hasPermission name="game.role.fenghao">
        &nbsp;<input id="batchFenHao" class="btn btn-primary" type="button" value="<spring:message code='str141'/>" onclick="submitCheckedRecordIdsWithPrompt_2('${ctx}/game/role/batchFengHao')"/>
        &nbsp;<input id="batchCancelFenHao" class="btn btn-primary" type="button" value="<spring:message code='str142'/>" onclick="submitCheckedRecordIdsWithPrompt('${ctx}/game/role/batchCancelFengHao')"/>
		</shiro:hasPermission>
		&nbsp;  <input id="btnExport" class="btn btn-primary" type="button" onclick="doExport('${ctx}/game/role/roleExport')" value="<spring:message code='str5'/>">            
        </div>
    </form>
	<tags:message content="${message}"/>
    <form id="tableForm" action="">
	<table id="contentTable" class="table table-striped table-bordered table-condensed  ">
        <input type="text" style="display: none" hidden="hidden" name="msg" id="msg"/>
        <input type="text" style="display: none" hidden="hidden" name="expireTime" id="expireTime"/>
		<tr><th width="18px"><input id="checkAllOrNot" name="checkAllOrNot" type="checkbox"></th><th><spring:message code='str143'/>ID</th><th><spring:message code='str138'/>ID</th><th><spring:message code='str7'/></th><th><spring:message code='str89'/></th><th><spring:message code='str90'/></th><th><spring:message code='str85'/></th><th><spring:message code='str144'/></th><th><spring:message code='str145'/></th><th><spring:message code='str103'/></th><th><spring:message code='str146'/></th><th><spring:message code='str14'/></th><th><spring:message code='str147'/></th><th><spring:message code='str148'/></th><th><spring:message code='str149'/></th></tr>
		<c:forEach items="${page.list}" var="role">
			<tr>
                <td><input type="checkbox" name="recordIds" value="${role.id}"></td>
                <td>${role.userId}</td>
                <td>${role.id}</td>
                <td>${role.name}</td>
                <td><spring:message code='${fns:getDictKeys(role.job, "job_type",role.job)}'/></td>
                <td>${role.gender}</td>
                <td>${role.level}</td>
                <td>${role.exp}</td>
                <td><fmt:formatDate value="${role.lastLoginTime}" type="both"/></td>
                <td><fmt:formatDate value="${role.lastLogoutTime}" type="both"/></td>
                <td><fmt:formatDate value="${role.addTime}" type="both"/></td>
                <td>${role.serverId}</td>
                <td>
                    <c:if test="${role.isjinyan eq 1}"><span class="label label-success">${role.isjinyan}</span></c:if>
                    <c:if test="${role.isjinyan eq 0}"><span class="label label-info">${role.isjinyan}</span></c:if>
                </td>
                <td>
                    <c:if test="${role.isfenghao eq 1}"><span class="label label-success">${role.isfenghao}</span></c:if>
                    <c:if test="${role.isfenghao eq 0}"><span class="label label-info">${role.isfenghao}</span></c:if>
                </td>
				<td>
                    <shiro:hasPermission name="game.role.delete">
                        <a href="${ctx}/game/role/delete?id=${role.id}" onclick="return confirmx('<spring:message code='str151'/>', this.href)"><spring:message code='str152'/></a>
                    </shiro:hasPermission>
					<shiro:hasPermission name="game.role.logingame">
						<a target="_blank" href="${ctx}/game/role/loginGame?userId=${role.userId}&serverId=${role.serverId}"><spring:message code='str153'/></a>
					</shiro:hasPermission>
                    <a href="${ctx}/game/role/form?id=${role.id}"><spring:message code='str154'/></a>
				</td>
			</tr>
		</c:forEach>
	</table>
        </form>
    <div class="pagination">${page}</div>
</body>
</html>
