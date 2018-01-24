<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str1500'/></title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">

		$(document).ready(function() {
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
					}
				}
			});

		});

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/tools/release"><spring:message code='str1500'/></a></li>
		<li class="active"><a href="${ctx}/tools/release/from"><spring:message code='str1501'/></a></li>
	</ul>
	<tags:message content="${message}"/>

	<form id="inputForm"  action="${ctx}/tools/release/add" method="post" class="form-horizontal">

		<div class="control-group">
			<label class="control-label"><spring:message code='str1502'/>:</label>
			<div class="controls">
				<input id="versionId" name="versionId" maxlength="50" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code='str1503'/>:</label>
			<div class="controls">
				<input id="versionTime" name="versionTime" maxlength="50" class="required" readonly onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code='str14'/>:</label>
			<div class="controls">
				<input id="serverIds" name="serverIds" readonly/><input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="showContent('${ctx}/tools/gameArea/openGameServer')"></button>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code='str1504'/>:</label>
			<div class="controls">
				<textarea id="releaseNote" name="releaseNote" htmlEscape="false" rows="5" class="input-xxlarge"></textarea>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code='str1392'/>:</label>
			<div class="controls">
				<textarea id="releaseContent" name="releaseContent" htmlEscape="false" rows="5" class="input-xxlarge"></textarea>
			</div>
		</div>

		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='str1176'/> <spring:message code='str1177'/>"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="<spring:message code='str1117'/> <spring:message code='str1118'/>" onclick="history.go(-1)"/>
		</div>
	</form>

</body>
</html>
