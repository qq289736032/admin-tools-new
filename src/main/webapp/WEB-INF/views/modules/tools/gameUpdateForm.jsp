<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str1390'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treeview.jsp" %>
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
		<li class="active"><a href="${ctx}/tools/config/gameUpdate"><spring:message code='str1390'/></a></li>
	</ul><br/>
    <hr class="bs-docs-separator">
	<form id="inputForm"  action="${ctx}/tools/config/gameUpdate" method="post" class="form-horizontal">
		<tags:message content="${message}"/>
        <div class="control-group">
            <label class="control-label"><spring:message code='str1391'/>:</label>
            <div class="controls">
                <textarea id="notice_log" name="notice_log" rows="2" maxlength="100" class="input-xxlarge required">${notice_log}</textarea>
            </div>

        </div>
        <div class="control-group">
            <label class="control-label"><spring:message code='str1392'/>:</label>
            <div class="controls">
                <textarea id="notice_content" name="notice_content" rows="15" maxlength="1000" class="input-xxlarge required">${notice_content}</textarea>
            </div>

        </div>

		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="button" onclick="submitGameUpdate('${ctx}/tools/config/saveGameUpdateLog')" value="<spring:message code='str1176'/> <spring:message code='str1177'/>"/>&nbsp;
		</div>
	</form>
</body>
<script type="text/javascript">
    function submitGameUpdate(){
        ajaxRequest("<spring:message code='str1393'/>?","${ctx}/tools/config/saveGameUpdateLog",{notice_log:$("#notice_log").val(),notice_content:$("#notice_content").val()})
    }
</script>
</html>
