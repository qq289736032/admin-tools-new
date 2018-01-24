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
				$("#ip").html("");
				return this.optional(element) || /^(([0-9]|([0-9]\d)|(1\d\d)|(2([0-4]\d|5[0-5])))\.)(([0-9]|([0-9]\d)|(1\d\d)|(2([0-4]\d|5[0-5])))\.){2}([0-9]|([0-9]\d)|(1\d\d)|(2([0-4]\d|5[0-5])))$/.test(value);
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

		function searchIp(){
			var ip = $("#ipAddress").val();
			if(ip != ""){
				$.get("${ctx}/tools/black/searchip?ip="+ip, function(result){
					if(result != ""){
				    	var ipresult =  eval('('+result+')');
				    	if(ipresult.code == 0 ){
				    		var data = eval(ipresult.data);
				    		var value = data.country + data.region + data.city + data.isp;
				    		
				    		$("#ip").html("</label><font color='red'>"+value+"</font>");
				    	}
				    	
				    }
					
				  });
				 

			}
			
		}
			
		
	</script>

</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/tools/black/ipSilenceFreeze">ip<spring:message code='str1197'/></a></li>
		<li class="active"><a href="${ctx}/tools/black/form"><spring:message code='str1191'/>IP</a></li>
		<li><a href="${ctx}/tools/black/userBlackList"><spring:message code='str1408'/></a></li>
		<li><a href="${ctx}/tools/black/userForm"><spring:message code='str1409'/></a></li>
	</ul>
	<tags:message content="${message}"/>

	<form id="inputForm"  action="${ctx}/tools/black/add" method="post" class="form-horizontal">

		<div class="control-group">
			<label class="control-label">ip<spring:message code='str1001'/>:</label>
			<div  class="controls">
				<input id="ipAddress" onBlur =searchIp()   name="ipAddress" maxlength="50" class="required"/>
				<label id ="ip"></label>
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
