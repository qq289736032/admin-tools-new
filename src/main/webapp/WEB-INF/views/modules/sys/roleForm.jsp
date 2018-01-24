<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str1202'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treeview.jsp" %>
	<script type="text/javascript">
		$(document).ready(function(){
			$("#name").focus();
			$("#inputForm").validate({
				rules: {
					name: {remote: "${ctx}/sys/role/checkName?oldName=" + encodeURIComponent("${role.name}")}
				},
				messages: {
					name: {remote: "<spring:message code='str1203'/>"}
				},
				submitHandler: function(form){
					var ids = [], nodes = tree.getCheckedNodes(true);
					for(var i=0; i<nodes.length; i++) {
						ids.push(nodes[i].id);
					}
					$("#menuIds").val(ids);
					var ids2 = [], nodes2 = tree2.getCheckedNodes(true);
					for(var i=0; i<nodes2.length; i++) {
						ids2.push(nodes2[i].id);
					}
					$("#officeIds").val(ids2);
					loading('<spring:message code='str1105'/>...');
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

			var setting = {check:{enable:true,nocheckInherit:true},view:{selectedMulti:false},
					data:{simpleData:{enable:true}},callback:{beforeClick:function(id, node){
						tree.checkNode(node, !node.checked, true, true);
						return false;
					}}};
			
			// <spring:message code='str143'/>-<spring:message code='str1204'/>
			var zNodes=[
					<c:forEach items="${menuList}" var="menu">
					    <c:if test="${not empty menu.parent.id}">
	                     {id:'${menu.id}', pId:'${not empty menu.parent.id?menu.parent.id:0}', name:"${menu.name}"},
	 					</c:if>
	                     <c:if test="${ empty menu.parent.id}">
	                     {id:'${menu.id}', pId:'${empty menu.parent.id?menu.parent.id:0}', name:"<spring:message code='str1422'/>"},
	 					</c:if>
		            </c:forEach>];
			// <spring:message code='str1206'/>
			var tree = $.fn.zTree.init($("#menuTree"), setting, zNodes);
			// <spring:message code='str1207'/>
			var ids = "${role.menuIds}".split(",");
			for(var i=0; i<ids.length; i++) {
				var node = tree.getNodeByParam("id", ids[i]);
				try{tree.checkNode(node, true, false);}catch(e){}
			}
			// <spring:message code='str1208'/>
			//tree.expandAll(true);
			
			// <spring:message code='str143'/>-<spring:message code='str1209'/>
			var zNodes2=[
					<c:forEach items="${officeList}" var="office">{id:'${office.id}', pId:'${not empty office.parent?office.parent.id:0}', name:"${office.name}"},
		            </c:forEach>];
			// <spring:message code='str1210'/>
			var tree2 = $.fn.zTree.init($("#officeTree"), setting, zNodes2);
			// <spring:message code='str1211'/>
			tree2.setting.check.chkboxType = { "Y" : "s", "N" : "s" };
			// <spring:message code='str1212'/>
			var ids2 = "${role.officeIds}".split(",");
            var gamePlatformIds = "${role.gamePlatformIds}".split(",");
			for(var i=0; i<ids2.length; i++) {
				var node = tree2.getNodeByParam("id", ids2[i]);
				try{tree2.checkNode(node, true, false);}catch(e){}
			}
			// <spring:message code='str1213'/>
			tree2.expandAll(true);
			// <spring:message code='str1214'/>/<spring:message code='str1215'/>
			refreshOfficeTree();
			$("#dataScope").change(function(){
				refreshOfficeTree();
			});
		});
		function refreshOfficeTree(){
			if($("#dataScope").val()==9){
				$("#officeTree").show();
			}else{
				$("#officeTree").hide();
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/role/"><spring:message code='str71'/></a></li>
		<li class="active"><a href="${ctx}/sys/role/form?id=${role.id}"><spring:message code='str138'/><shiro:hasPermission name="sys:role:edit"><c:if test="${not empty role.id }"> <spring:message code='str1365'/></c:if>
			   <c:if test="${ empty role.id }"> <spring:message code='operation.add'/></c:if></shiro:hasPermission><shiro:lacksPermission name="sys:role:edit"><spring:message code='str872'/></shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="role" action="${ctx}/sys/role/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<tags:message content="${message}"/>
		<div class="control-group" hidden="hidden">
			<label class="control-label"><spring:message code='str1570'/>:</label>
			<div class="controls">
                <tags:treeselect id="office" name="office.id" value="${role.office.id}" labelName="office.name" labelValue="${role.office.name}"
					title="<spring:message code='str1571'/>" url="/sys/office/treeData" cssClass="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code='str653'/>:</label>
			<div class="controls">
				<input id="oldName" name="oldName" type="hidden" value="${role.name}">
				<form:input path="name" htmlEscape="false" maxlength="50" class="required"/>
			</div>
		</div>
        <div class="control-group">
            <label class="control-label"><spring:message code='str729'/>:</label>
            <div class="controls">
                <input id="remarks" name="remarks" value="${role.remarks}">
            </div>
        </div>
		<div class="control-group" hidden="hidden">
			<label class="control-label"><spring:message code='str1573'/>:</label>
			<div class="controls">
				<form:select path="dataScope">
					<%--<form:options items="${fns:getDictList('sys_data_scope')}" itemLabel="label" itemValue="value" htmlEscape="false"/>--%>
                    <form:option value="1"><spring:message code='str1574'/></form:option>
				</form:select>
				<%--<span class="help-inline"><spring:message code='str1217'/></span>--%>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label"><spring:message code='str1431'/>:</label>
			<div class="controls">
				<div id="menuTree" class="ztree" style="margin-top:3px;float:left;"></div>
				<form:hidden path="menuIds"/>
				<div id="officeTree" class="ztree" style="margin-left:100px;margin-top:3px;float:left;"></div>
				<form:hidden path="officeIds"/>
			</div>

		</div>

        <div class="control-group">
        <label class="control-label"><spring:message code='str1572'/>:</label>
        <%--<div class="controls">--%>
            <%--<input type="radio" name="isGlobal" value="1" onclick="doS();"/><spring:message code='str1220'/>--%>
            <%--<input type="radio" name="isGlobal" value="0" onclick="$('#platformIds').show()"/><spring:message code='str1221'/>--%>
        <%--</div>--%>
            <div class="controls" id="platformIds" style="display:block;">
                <input type="checkbox" class="case" value="" id="selectAll" name="selectAll"/><spring:message code='str1222'/>
                <input id="isGlobal" name="isGlobal" type="hidden" value="${role.isGlobal}"/>
                <form:checkboxes path="gamePlatformIds" items="${allGamePlatforms}" itemLabel="name" itemValue="id" htmlEscape="false"  cssClass="case required"/>
            </div>
        </div>
		<div class="form-actions">
			<shiro:hasPermission name="sys:role:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='str1176'/> <spring:message code='str1177'/>"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="<spring:message code='str1117'/> <spring:message code='str1118'/>" onclick="history.go(-1)"/>
		</div>
	</form:form>

<script type="text/javascript">
    $(function(){
        $("#selectAll").click(function () {
            selectAll();
        })

      
        var isGlobal = ${role.isGlobal} + '';
        if(isGlobal == 1){
            $('.case').attr('checked', 'checked');
            $("#isGlobal").val('1');
        }
    });
    function selectAll(){
        if($("#selectAll").attr("checked") == 'checked'){
            $('.case').attr('checked', 'checked');
            $("#isGlobal").val('1');
        }else {
            $('.case').removeAttr('checked');
            $("#isGlobal").val('0');
        }
    }
</script>
</body>
</html>
