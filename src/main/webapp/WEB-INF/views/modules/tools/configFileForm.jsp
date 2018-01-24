<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str1139'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treeview.jsp" %>
    <script type="text/javascript">

        $(document).ready(function() {
            $("#content").focus();
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
                }
            });

        });
  </script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li>
		<a href="${ctx}/tools/gameConfig/"><spring:message code='str1212'/></a></li>
		<li class="active"><a href="${ctx}/tools/gameConfig/form?id=${configFile.id}"> <c:if test="${not empty configFile.id}"><spring:message code='str1365'/></c:if><c:if test="${ empty configFile.id}"><spring:message code='operation.add'/></c:if><spring:message code='str1213'/></a></li>
		
	</ul><br/>
	<form:form id="inputForm" modelAttribute="configFile" action="${ctx}/tools/gameConfig/save" method="post" class="form-horizontal" enctype="multipart/form-data">
		<form:hidden path="id"/>
		<tags:message content="${message}"/>

		<div class="control-group">
			<label class="control-label"><spring:message code='str1214'/>:</label>
			<div class="controls">
                <input type="file" name="file" class="required"/>
			</div>
		</div>
        
        <div class="control-group">
            <label class="control-label"><spring:message code='str1134'/>:</label>
            <div class="controls">
                <select name="fileName">
                    <c:forEach items="${fileMap}" var="map" varStatus="status">
                       <option value="${map.key}"
                         <c:if test="${map.key == configFile.newName }"> 
                         selected="selected"</c:if>
                         
                       >${map.value}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label"><spring:message code='str1215'/>:</label>
            <div class="controls">
                <form:textarea path="fileDesc" htmlEscape="false" rows="3" maxlength="100" class="input-xlarge"/>
            </div>
        </div>


		<div class="form-actions">
			<c:if test="${ empty configFile.id}">	
				<input id="btnSubmit" class="btn btn-primary" type="submit"  value="<spring:message code='str1216'/>"/>&nbsp;
		</c:if>
			<c:if test="${not  empty configFile.id}">	
				<input id="btnSubmit" class="btn btn-primary" type="submit"  value="<spring:message code='str1365'/>"/>&nbsp;
		</c:if>
			<input id="btnCancel" class="btn" type="button" value="<spring:message code='str1117'/> <spring:message code='str1118'/>" onclick="history.go(-1)"/>
		</div>
	</form:form>

</body>
</html>
