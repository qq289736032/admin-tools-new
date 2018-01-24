<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str1139'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treeview.jsp" %>
    <script type="text/javascript">

        $(document).ready(function() {
            $("#title").focus();
            $("#inputForm").validate({
                submitHandler: function(form){

                    loading('<spring:message code='str1106'/>...');
                    form.submit();
                },
                errorContainer: "#messageBox",
                errorPlacement: function(error, element) {
                    $("#messageBox").text("<spring:message code='str1107'/>");
                    if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
                        error.appendTo(element.parent().parent());
                    } else {
                        error.insertAfter(element);
                    }
                }
            });

        });

        function openServer(){

            selectSingleServer();
        }

    </script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/game/role/gmList/"><spring:message code='str821'/></a></li>
		<shiro:hasPermission name="game.gm.edit">
			<li class="active"><a href="${ctx}/game/role/gmForm?id=${paramMap.roleName}"><c:if  test="${empty  paramMap.roleName}"><spring:message code='operation.add'/></c:if><c:if  test="${not empty paramMap.roleName}"><spring:message code='str1365'/></c:if><spring:message code='str1399'/></a></li>
 	  </shiro:hasPermission>
        <li><a href="${ctx}/game/role/gmPublish"><spring:message code='str823'/></a></li>
	</ul><br/>
	<form id="inputForm" action="${ctx}/game/role/saveGm" method="post" class="form-horizontal">
        <input hidden="hidden" style="display: none"  id="id" name="id" value="${paramMap.id}">
		<tags:message content="${message}"/>

        <div class="control-group">
            <label class="control-label"><spring:message code='str14'/>:</label>
            <div class="controls">
                <input id="serverIds" name="serverIds" value="${paramMap.serverIds}" readonly/>
                <%--<input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="openServer()"></button>--%>
                <input type="button" class="btn btn-primary" value="<spring:message code='str15'/>"
                       onclick="showContent('${ctx}/tools/gameArea/singlePlatformGameServer')">
                <input style="display: none" id="platformName" name="platformName" value="${paramMap.platformName}"/>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label"><spring:message code='str7'/>:</label>
            <div class="controls">
                <input type="text" name="roleName" id="roleName" value="${paramMap.roleName}" class="input-xlarge required"/>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label"><spring:message code='str700'/>:</label>
            <div class="controls">
                <select id="roleType" name="roleType">
                    <c:forEach items="${fns:getDictList('role_type')}" var="roleType">
                        <option value="${roleType.value}" name="roleType"><spring:message code="${roleType.internationalKey}"/></option>
                    </c:forEach>
                </select>
            </div>
        </div>

		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='str1176'/> <spring:message code='str1177'/>"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="<spring:message code='str1117'/> <spring:message code='str1118'/>" onclick="history.go(-1)"/>
		</div>
	</form>

</body>
</html>
