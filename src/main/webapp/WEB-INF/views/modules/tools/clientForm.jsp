<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str1191'/>IP</title>
	<meta name="decorator" content="default"/>


	<%--<link href="${ctxStatic}/bootstrap-switch/dist/css/bootstrap2/bootstrap-switch.css" type="text/css" rel="stylesheet" />--%>
	<%--<script src="${ctxStatic}/bootstrap-switch/dist/js/bootstrap-switch.js" type="text/javascript"></script>--%>
	<script src="${ctxStatic}/bootstrap-switch/dist/js/highlight.js" type="text/javascript"></script>
	<script src="${ctxStatic}/bootstrap-switch/dist/js/main.js" type="text/javascript"></script>

	<script type="text/javascript">

		$(document).ready(function() {
			jQuery.validator.addMethod("ip", function(value, element) {
				return this.optional(element) || /^(([1-9]|([1-9]\d)|(1\d\d)|(2([0-4]\d|5[0-5])))\.)(([1-9]|([1-9]\d)|(1\d\d)|(2([0-4]\d|5[0-5])))\.){2}([1-9]|([1-9]\d)|(1\d\d)|(2([0-4]\d|5[0-5])))$/.test(value);
			}, "<spring:message code='str1192'/>IP<spring:message code='str1193'/>");

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
				},
				rules: {
					moneyNum:{
						digits:true,
						range:[0,900000000]
					},
					ipAddress: {
						ip: true
					}
				}
			});

		});



	</script>

</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/tools/config/client"><spring:message code='str1201'/>URL</a></li>
		<li class="active"><a href="${ctx}/tools/config/clientForm"><spring:message code='str1191'/></a></li>
	</ul>
	<tags:message content="${message}"/>

	<form id="inputForm"  action="${ctx}/tools/config/addClient" method="post" class="form-horizontal">

		<div class="control-group">
			<label class="control-label"><spring:message code='str14'/>:</label>
			<div class="controls">
				<input type="radio" name="isGlobal" value="1"  onclick="$('#gameServerTreeDiv').hide()"><spring:message code='str1202'/>
				<input type="radio" name="isGlobal" value="0" checked="checked" onclick="$('#gameServerTreeDiv').show()"><spring:message code='str1348'/>

			</div>

		</div>

		<div class="control-group">
			<div class="controls" id="gameServerTreeDiv">
				<input id="serverIds" name="serverIds" readonly/>
				<%--<input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="openSeverDialog()"></button>--%>
				<input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="showContent('${ctx}/tools/gameArea/openGameServer')">
			</div>
		</div>

		<div class="control-group">
			<label class="control-label"><spring:message code='str1201'/>URL:</label>
			<div class="controls">
				<input type="text" operatorType="url" class="input-xxlarge" name="url" >
			</div>
		</div>

		<%--<div class="control-group">--%>
			<%--<label class="control-label"><spring:message code='str78'/>:</label>--%>
			<%--<div class="controls">--%>
				<%--<input type="text" name="expireTime" id="expireTime"  readonly="readonly" maxlength="20" value="${paramMap.expireTime}" id="startDatePicker" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">--%>
			<%--</div>--%>
		<%--</div>--%>

		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='str1176'/> <spring:message code='str1177'/>"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="<spring:message code='str1117'/> <spring:message code='str1118'/>" onclick="history.go(-1)"/>
		</div>
	</form>

</body>
</html>
