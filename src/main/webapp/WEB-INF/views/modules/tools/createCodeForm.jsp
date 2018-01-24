<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str1249'/></title>
	<meta name="decorator" content="default"/>
    <%@include file="/WEB-INF/views/include/dialog.jsp" %>

    <script type="text/javascript">

        $(document).ready(function() {
        	$("#repeatDiv").hide();
    		$("#type").on("change",function(e){
    			if(e.val!=0){
    				$("#repeatDiv").show();
    			}else{
    				$("#repeatNum").val(0);
    				$("#repeatDiv").hide();
    			}
    		})
            $("#title").focus();
            $("#inputForm").validate({
                submitHandler: function(form){
                	var pid =$("#pid").val();
					if (pid == "") {
						tips("请选择使用平台!");
						return;
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
                    codeLength:{
                        digits:true,
                        range:[9,32]
                    },repeatNum:{
                    	digits:true
                    }
                }
            });
        });

    </script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/tools/gift/createCodeList"><spring:message code='str1250'/></a></li>
		<li class="active"><a href="${ctx}/tools/gift/createCodeForm"><spring:message code='str1251'/></a></li>
	</ul><br/>
	<form:form id="inputForm" action="${ctx}/tools/gift/createCodeSave" method="post" class="form-horizontal">
		<tags:message content="${message}"/>

        <div class="control-group">
            <label class="control-label"><spring:message code='str1252'/>:</label>
			<div class="controls">
				<select id="giftId" name="giftId">
					<%--<option value=""><spring:message code='str627'/></option>--%>
					<c:forEach items="${giftList}" var="gift">
						<option value="${gift.id}">${gift.name}</option>
					</c:forEach>
				</select>
			</div>
        </div>
		<div class="control-group">
			<label class="control-label"><spring:message code='str1253'/>:</label>
			<div class="controls">
                <select id="type" name="type">
					<option value="0"><spring:message code='str1247'/></option>
                    <option value="1"><spring:message code='str1246'/></option>
                </select>
            </div>
		</div>
		
		<div class="control-group" id="repeatDiv">
			<label class="control-label">可重复领取数量:</label>
			<div class="controls">
                 <input type="text" id="repeatNum" name="repeatNum" value="0" class="input-xlarge required"/>
            </div>
		</div>
		
		<div class="control-group">
            <label class="control-label"><spring:message code='str1254'/>:</label>
            <div class="controls">
                <input type="text" name="count"  class="input-xlarge required"/>
            </div>
        </div>
		<div class="control-group">
			<label class="control-label"><spring:message code='str1244'/>:</label>
            <div class="controls">
                <input type="text" name="prefix"  class="input-xlarge required"/>
            </div>
        </div>
		<div class="control-group">
			<label class="control-label"><spring:message code='str1255'/>:</label>
            <div class="controls">
                <input type="text" name="codeLength" id="codeLength" class="input-xlarge required"/>
                <span class="help-inline">(<spring:message code='str1256'/>9<spring:message code='str1257'/>)</span>
            </div>
        </div>
		<div class="control-group">
			<label class="control-label"><spring:message code='str1258'/></label>
            <div class="controls">
                <input type="text" name="effectiveTime" id="startDatePicker" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDatePicker\')}'})" class="required"/>
            </div>
        </div>
		<div class="control-group">
			<label class="control-label"><spring:message code='str1259'/></label>
            <div class="controls">
                <input type="text" name="effectiveEndTime" id="endDatePicker" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatePicker\')}'})"/><span class="help-inline">(<spring:message code='str1260'/>)</span>
            </div>
        </div>
		<div class="control-group">
            <label class="control-label"><spring:message code='str1261'/></label>
			<div class="controls">
				<select id="pid" name="pids" onchange="setCurrentPlatform()">
				<option value=''></option>
				<option value='all'>全服平台</option>
			<c:forEach var="item" items="${fns:getGamePlatformList()}">
				<option value="${item.pid}" <c:if test="${item.pid==paramMap.pids}">selected="selected"</c:if>>${item.name}</option>
			</c:forEach>
		</select>
			</div>
        </div>
		<div class="control-group">
			<label class="control-label"><spring:message code='str1262'/></label>
            <div class="controls">
				<input type="textarea" name="remarks"/>
            </div>
        </div>

		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='str1263'/> <spring:message code='str1264'/>"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="<spring:message code='str1117'/> <spring:message code='str1118'/>" onclick="history.go(-1)"/>
		</div>
	</form:form>

</body>
</html>
