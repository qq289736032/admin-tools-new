<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str1104'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treeview.jsp" %>
    <%@include file="/WEB-INF/views/include/dialog.jsp" %>

    <script type="text/javascript">
		$(document).ready(function() {
			$("#inputForm").validate({
				submitHandler: function(form){
					if ($("#isGlobal").val() == "0") {
						var serverId =$("#serverIds").val();
						if (serverId == "") {
							tips("<spring:message code='str1105'/>!");
							return;
						}
					}

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
				},
				rules:{
					name:{
						required: true,
						maxlength:200
					},
					alias:{
						required: true,
						maxlength:200
					}
				}
			})
		})
    </script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/tools/activity/activityConfigList"><spring:message code='str1108'/></a></li>
		<li class="active"><a href="${ctx}/tools/activity/activityConfigForm?id=${activityConfig.id}"><c:if test="${not empty activityConfig.id}"><spring:message code='str1365'/><spring:message code='str1111'/></c:if><c:if test="${empty activityConfig.id}"><spring:message code='operation.add'/><spring:message code='str1111'/></c:if></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="activityConfig" action="${ctx}/tools/activity/saveActivityConfig" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<tags:message content="${message}"/>

		<div class="control-group">
			<label class="control-label"><spring:message code='str1112'/>:</label>
			<div class="controls">
				<form:input path="name" name="name" htmlEscape="false" class="input-xlarge {required:true,maxlength:200}"/>
			</div>
        </div>
		
		<div class="control-group">
			<label class="control-label">Alias:</label>
			<div class="controls">
				<form:input path="alias" name="alias" htmlEscape="false" class="input-xlarge {required:true,maxlength:200}"/>
			</div>
		</div>

		<div class="form-actions">
			<c:if test="${not empty activityConfig.id}">
		       <input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='str1115'/><spring:message code='str1116'/> "/> &nbsp; 
		</c:if>
		<c:if test="${empty activityConfig.id}">
			   <input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='str1113'/><spring:message code='str1114'/>"/> &nbsp;
		</c:if>
			<input id="btnCancel" class="btn" type="button" value="<spring:message code='str1117'/> <spring:message code='str1118'/>" onclick="history.go(-1)"/>
		</div>
	</form:form>

</body>
</html>
