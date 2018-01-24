<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str1191'/>IP</title>
	<meta name="decorator" content="default"/>


	<link href="${ctxStatic}/bootstrap-switch/dist/css/bootstrap2/bootstrap-switch.css" type="text/css" rel="stylesheet" />
	<script src="${ctxStatic}/bootstrap-switch/dist/js/bootstrap-switch.js" type="text/javascript"></script>
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
		<li><a href="${ctx}/tools/black/ipSilenceFreeze">ip<spring:message code='str1197'/></a></li>
		<li><a href="${ctx}/tools/black/form"><spring:message code='str1191'/>IP</a></li>
		<li><a href="${ctx}/tools/black/userBlackList"><spring:message code='str1408'/></a></li>
		<li  class="active"><a href="${ctx}/tools/black/userForm"><spring:message code='str1409'/></a></li>
	</ul>
	<tags:message content="${message}"/>

	<form id="inputForm"  action="${ctx}/tools/black/addUserBlack" method="post" class="form-horizontal">

		<div class="control-group">
			<label class="control-label"><spring:message code='str56'/>:</label>
			<div class="controls">
				<select id="pid" name="pid">
					<%--<form:option value="" label="<spring:message code='str627'/>"/>--%>
					<c:forEach var="platform" items="${fns:getGamePlatformList()}">
						<option id="" value="${platform.pid}" <c:if test="${paramMap.pid == platform.pid}">selected="selected" </c:if>>${platform.name}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code='str88'/>:</label>
			<div class="controls">
			<!-- <input id="userId" name="userId" maxlength="50" class="required"/> -->
			<textarea id="userIds" rows="3" name="userIds" class="input-xxlarge required"></textarea>
              <span style="color:#FF003A;"> * <spring:message code='str1167'/></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code='str1524'/>:</label>
			<div class="controls">
				<input type="checkbox" operatorType="self"  data-size="small" name="self" <c:if test="${item.self == 1}">checked</c:if>>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code='str147'/>:</label>
			<div class="controls">
				<input type="checkbox" operatorType="silence"  data-size="small" name="silence" <c:if test="${item.silence == 1}">checked</c:if>>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code='str148'/>:</label>
			<div class="controls">
				<input type="checkbox" operatorType="freeze" data-size="small" name="freeze" <c:if test="${item.freeze == 1}">checked</c:if>>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label"><spring:message code='str78'/>:</label>
			<div class="controls">
				<input type="text" name="expireTime" id="expireTime"  readonly="readonly" maxlength="20" value="${paramMap.expireTime}" id="startDatePicker" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
			</div>
		</div>

		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='str1176'/> <spring:message code='str1177'/>"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="<spring:message code='str1117'/> <spring:message code='str1118'/>" onclick="history.go(-1)"/>
		</div>
	</form>

</body>
</html>
