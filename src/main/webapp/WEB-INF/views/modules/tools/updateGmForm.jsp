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
		<li class="active"><a><spring:message code='str1522'/></a></li>
        <li><a href="${ctx}/game/role/gmPublish">GM<spring:message code='str823'/></a></li>
	</ul><br/>
	<form id="inputForm" action="${ctx}/game/role/updateGm" method="post" class="form-horizontal">
        <input hidden="hidden" style="display: none"  id="id" name="id" value="${map.id}">
        <%--<input hidden="hidden" style="display: none"  id="roleType" name="roleType" value="${map.roleType}">--%>
        <input hidden="hidden" style="display: none"  id="serverId" name="serverId" value="${map.serverId}">
        <input hidden="hidden" style="display: none"  id="roleId" name="roleId" value="${map.roleId}">
		<tags:message content="${message}"/>

        <div class="control-group">
            <label class="control-label"><spring:message code='str7'/>:</label>
            <div class="controls">
                <input type="text" name="roleName" id="roleName" value="${map.roleName}" readonly class="input-xlarge required"/>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label"><spring:message code='str700'/>:</label>
            <div class="controls">
                <select id="roleType" name="roleType">
                    <c:forEach items="${fns:getDictList('role_type')}" var="roleType">
                        <option value="${roleType.value}" <c:if test="${map.roleType == roleType.value}">selected="selected"</c:if> name="roleType"><spring:message code="${roleType.internationalKey}"/></option>
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
