<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str1473'/></title>
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
		<li><a href="${ctx}/tools/recharge/"><spring:message code='str967'/></a></li>
		<shiro:hasPermission name="game.recharge.apply">
		    <li><a href="${ctx}/tools/recharge/form?id=${recharge.id}"><c:if test="${ not empty recharge.id}"><spring:message code='str1478'/></c:if><c:if test="${  empty recharge.id}"><spring:message code='str1479'/></c:if><spring:message code='str547'/></a></li>
			<li  class="active"><a href="${ctx}/tools/recharge/roleIdRechargeForm?id=${recharge.id}"><c:if test="${ not empty recharge.id}"><spring:message code='str1480'/>ID<spring:message code='str1158'/></c:if><c:if test="${  empty recharge.id}"><spring:message code='str1480'/>ID<spring:message code='str1158'/></c:if><spring:message code='str547'/></a></li>
		</shiro:hasPermission>
        <shiro:hasPermission name="game.recharge.add">
            <li class="active"><a href="${ctx}/tools/recharge/add"><spring:message code='str1481'/></a></li>
        </shiro:hasPermission>
	</ul><br/>
    <div class="alert alert-info">
        <strong><spring:message code='str1482'/>:</strong>
        <spring:message code='str1483'/><br/>
        <spring:message code='str171'/>1<spring:message code='str1484'/><br/>
        <spring:message code='str171'/>2<spring:message code='str1488'/>
    </div>
    <hr class="bs-docs-separator">
	<form:form id="inputForm" modelAttribute="recharge" action="${ctx}/tools/recharge/saveAndSend" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<tags:message content="${message}"/>
        <div class="control-group">
            <label class="control-label"><spring:message code='str970'/>:</label>
            <div class="controls">
                <form:select path="rechargeType">
                    <form:options items="${fns:getDictList('recharge_type')}" itemLabel="label" itemValue="value"/>
                </form:select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><spring:message code='str7'/>:</label>
            <div class="controls">
                <form:textarea path="roleNames"  htmlEscape="false" rows="3" maxlength="200" class="input-xxlarge required"/>
                <span class="help-inline label label-info"><spring:message code='str1486'/></span>
            </div>

        </div>
        <div class="control-group">
            <label class="control-label"><spring:message code='str888'/>:</label>
            <div class="controls">
                <form:select path="moneyType">
                    <form:options items="${fns:getDictList('money_type')}" itemLabel="label" itemValue="value"/>
                </form:select>
            </div>
        </div>
		<div class="control-group">
			<label class="control-label"><spring:message code='str1487'/>:</label>
            <div class="controls">
                <input id="moneyNum" name="moneyNum" maxlength="50" value="0"  class="required"/>
            </div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code='str729'/>:</label>
			<div class="controls">
                <form:textarea path="remark" htmlEscape="false" rows="3" maxlength="100" class="input-xxlarge"/>
			</div>
		</div>


		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='str1489'/>"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="<spring:message code='str1117'/> <spring:message code='str1118'/>" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
