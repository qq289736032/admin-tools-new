<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str1280'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treeview.jsp" %>
    <script type="text/javascript">

        $(document).ready(function() {
            $("#content").focus();
            $("#inputForm").validate({
                submitHandler: function(form){

                    var isGlobal;
                    $('input[name="isGlobal"]:checked').each(function(){
                        isGlobal = $(this).val();
                    });
                    if(isGlobal == 0){
                        var serverIds = $("#serverIds").val();
                        if(serverIds == ''){
                            tips("<spring:message code='str1281'/>")
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
                }
            });

        });

        function submitForm(){
            var isGlobal = $(".isGlobal").attr('checked').val();
            alert(isGlobal);
            return;
            if(!isGlobal){
                var serverIds = $("#serverIds").val();
                if(serverIds == ''){
                    tips("<spring:message code='str1282'/>")
                    return;
                }
            }
            $("#inputForm").submit();
        }

    </script>
    <style type="text/css">
        td#cke_contents_content {
            height: 158px !important;
        }
        span#cke_content {
            width: 60% !important;
            /* height: 200px; */
        }
    </style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/tools/crossArea/crossAreas"><spring:message code='str1283'/></a></li>
        <li class="active"><a href=""><spring:message code='str1280'/></a></li>
	</ul><br/>
	<form id="inputForm" action="${ctx}/tools/crossArea/saveGameCrossRelation" method="post" class="form-horizontal">
		<input style="display: none" id="crossId" name="crossId" value="${crossId}"/>

        <div class="control-group">
            <label class="control-label"><spring:message code='str768'/>:</label>
            <div class="controls" id="gameServerTreeDiv">
                <input id="serverIds" value="${fns:getGameServerListByCrossId(crossId)}" name="serverIds" readonly/><input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="showContent('${ctx}/tools/gameArea/openGameServer')">
            </div>
        </div>

		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='str1176'/> <spring:message code='str1177'/>"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="<spring:message code='str1117'/> <spring:message code='str1118'/>" onclick="history.go(-1)"/>
		</div>
	</form>

</body>
</html>
