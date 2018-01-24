<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str1120'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treeview.jsp" %>
    <%@include file="/WEB-INF/views/include/dialog.jsp" %>

    <script type="text/javascript">

        $(document).ready(function() {
			
			if ($("#activityId").val() != "" && $("#activityId").val() != null) {
				if($("#isGlobal").val() == "0") {
					$('#serverSelect').show();
				}
			}
			
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
					activityName:{
                        required: true
                    }
                }
            });
        });

        function toggle(){
			if ($("#isGlobal").val() == "1") {
                $('#serverSelect').hide();
            } else {
                $('#serverSelect').show();
            }
            $("#serverIds").val("");
        }

        function openServer(){
            if ($("#isGlobal").val() == "0") {
			    //$("#serverIds").val("");
			    $("#serverIds").val();
                openSeverDialog();
            } else {
                alert("<spring:message code='str1121'/>");
            }
        }

    </script>
</head>
<body>
	<ul class="nav nav-tabs">
	 <li ><a href="${ctx}/tools/activity/activityList"><spring:message code='str1122'/></a></li>
	 <li class="active"><a href="${ctx}/tools/activity/form?id=${activity.id}"> <c:if test="${not empty activity.id}"><spring:message code='str1365'/></c:if><c:if test="${empty activity.id}"><spring:message code='operation.add'/></c:if><spring:message code='str1123'/></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="activity" action="${ctx}/tools/activity/save" method="post" class="form-horizontal" enctype="multipart/form-data">
		<form:hidden path="id"/>
		<input type="hidden" id="activityId" name="activityId" value="${activity.id}">

		<tags:message content="${message}"/>

		<div class="control-group">
			<label class="control-label"><spring:message code='str1112'/>:</label>
			<div class="controls">
				<c:if test="${not empty activity.id}"><input id="activityName" name="activityName" value="${activity.activityName}" readonly/></c:if>
				<c:if test="${empty activity.id}">
					<select id="activityName" name="activityName">
						<c:forEach items="${activityConfigList}" var="item">
							<option value="${item.name}">${item.name}</option>
						</c:forEach>
					</select>
				</c:if>	
			</div>
        </div>
		
		<div class="control-group">
			<label class="control-label"><spring:message code='str1124'/>:</label>
			<div class="controls">
				<form:textarea path="activityDesc" htmlEscape="false" rows="3" maxlength="200" class="input-xlarge"/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label"><spring:message code='str1125'/>:</label>
			<div class="controls">
				<c:if test="${empty activity.id}">
					<input type="file" name="file" class="required"/>
				</c:if>	
				<c:if test="${not empty activity.id}">
					<input type="file" name="file" value="${activity.name}"/>
				</c:if>	
			</div>
		</div>
		
        <div class="control-group">
            <label class="control-label"><spring:message code='str1126'/>:</label>
            <div class="controls">
					<select id="isGlobal" name="isGlobal" onchange="toggle()">
						<option value="1"><spring:message code='str1127'/></option>
						<c:if test="${not empty activity.id}">
							<c:if test="${activity.isGlobal == 0}">
								<option value="0" selected="selected"><spring:message code='str1128'/></option>
							</c:if>	
							<c:if test="${activity.isGlobal == 1}">
								<option value="0"><spring:message code='str1128'/></option>
							</c:if>	
						</c:if>	
						<c:if test="${empty activity.id}">
							<option value="0"><spring:message code='str1128'/></option>
						</c:if>	
					</select>
            </div>
        </div>
			<div class="control-group"  class="control-group" style="display: none" id="serverSelect">
				<label class="control-label"><spring:message code='str14'/>:</label>
				<div class="controls">
					<input id="serverIds" name="serverIds" value="${activity.serverIds}" readonly/>
					<%--<input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="openServer()"></button>--%>
					<input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="showContent('${ctx}/tools/gameArea/openGameServer');"></button>
				</div>
			</div>

		<div class="form-actions">
			<c:if test="${not empty activity.id}">
		       <input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='str1115'/><spring:message code='str1116'/> "/> &nbsp; 
		</c:if>
		<c:if test="${empty activity.id}">
			   <input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='str1113'/><spring:message code='str1114'/>"/> &nbsp;
		</c:if>
			<input id="btnCancel" class="btn" type="button" value="<spring:message code='str1117'/> <spring:message code='str1118'/>" onclick="history.go(-1)"/>
		</div>
	</form:form>

</body>
</html>
