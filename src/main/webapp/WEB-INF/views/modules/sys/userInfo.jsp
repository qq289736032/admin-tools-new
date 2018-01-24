<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str1249'/></title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('<spring:message code='str1105'/>...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("<spring:message code='str1106'/>");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/user/info"><spring:message code='person.info'/></a></li>
		<li><a href="${ctx}/sys/user/modifyPwd"><spring:message code='modify.passwod'/></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="user" action="${ctx}/sys/user/info" method="post" class="form-horizontal">
		<tags:message content="${message}"/>
		<%--<div class="control-group">--%>
			<%--<label class="control-label"><spring:message code='str1286'/>:</label>--%>
			<%--<div class="controls">--%>
				<%--<label class="lbl">${user.company.name}</label>--%>
			<%--</div>--%>
		<%--</div>--%>
		<%--<div class="control-group">--%>
			<%--<label class="control-label"><spring:message code='str1288'/>:</label>--%>
			<%--<div class="controls">--%>
				<%--<label class="lbl">${user.office.name}</label>--%>
			<%--</div>--%>
		<%--</div>--%>
		<div class="control-group">
			<label class="control-label"><spring:message code='str1428'/>:</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="50" class="required" readonly="true"/>
			</div>
		</div>
		<%--<div class="control-group">--%>
			<%--<label class="control-label"><spring:message code='str1615'/>:</label>--%>
			<%--<div class="controls">--%>
				<%--<form:input path="email" htmlEscape="false" maxlength="50" class="email"/>--%>
			<%--</div>--%>
		<%--</div>--%>
		<%--<div class="control-group">--%>
			<%--<label class="control-label"><spring:message code='str1429'/>:</label>--%>
			<%--<div class="controls">--%>
				<%--<form:input path="phone" htmlEscape="false" maxlength="50"/>--%>
			<%--</div>--%>
		<%--</div>--%>
		<%--<div class="control-group">--%>
			<%--<label class="control-label"><spring:message code='str1430'/>:</label>--%>
			<%--<div class="controls">--%>
				<%--<form:input path="mobile" htmlEscape="false" maxlength="50"/>--%>
			<%--</div>--%>
		<%--</div>--%>
		<div class="control-group">
			<label class="control-label"><spring:message code='str729'/>:</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="input-xlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code='str1640'/>:</label>
			<div class="controls">
				<label class="lbl">
				<c:if test="${fns:getDictKeys(user.userType, 'sys_user_type', user.userType)!=''}">
			  	  <spring:message code="${fns:getDictKeys(user.userType, 'sys_user_type', user.userType)}"/>
			  	  </c:if>
				</label>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code='str1594'/>:</label>
			<div class="controls">
				<label class="lbl">${user.roleNames}</label>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code='str1595'/>:</label>
			<div class="controls">
				<label class="lbl">IP: ${user.loginIp}&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code='str709'/><fmt:formatDate value="${user.loginDate}" type="both" dateStyle="full"/></label>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='str1176'/> <spring:message code='str1177'/>"/>
		</div>
	</form:form>
</body>
</html>
