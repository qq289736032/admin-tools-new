<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str1183'/></title>
	<%@include file="/WEB-INF/views/include/treeview.jsp" %>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	
		var officeTree;
		var selectedTree;//zTree<spring:message code='str1225'/>
		
		// <spring:message code='str1226'/>
		$(document).ready(function(){
			officeTree = $.fn.zTree.init($("#officeTree"), setting, officeNodes);
			selectedTree = $.fn.zTree.init($("#selectedTree"), setting, selectedNodes);
		});

		var setting = {view: {selectedMulti:false,nameIsHTML:true,showTitle:false},
				data: {simpleData: {enable: true}},
				callback: {onClick: treeOnClick}};
		
		var officeNodes=[
	            <c:forEach items="${officeList}" var="office">
	            {id:"${office.id}",
	             pId:"${not empty office.parent?office.parent.id:0}", 
	             name:"${office.name}"},
	            </c:forEach>];
	
		var pre_selectedNodes =[
   		        <c:forEach items="${role.userList}" var="user">
   		        {id:"${user.id}",
   		         pId:"0",
   		         name:"<font color='red' style='font-weight:bold;'>${user.loginName}</font>"},
   		        </c:forEach>];
		
		var selectedNodes =[
		        <c:forEach items="${role.userList}" var="user">
		        {id:"${user.id}",
		         pId:"0",
		         name:"<font color='red' style='font-weight:bold;'>${user.loginName}</font>"},
		        </c:forEach>];
		
		var pre_ids = "${selectIds}".split(",");
		var ids = "${selectIds}".split(",");
		
		//<spring:message code='str1227'/>
		function treeOnClick(event, treeId, treeNode, clickFlag){
			if("officeTree"==treeId){
				$.get("${ctx}/sys/role/users?officeId=" + treeNode.id, function(userNodes){
					$.fn.zTree.init($("#userTree"), setting, userNodes);
				});
			}
			if("userTree"==treeId){
				//alert(treeNode.id + " | " + ids);
				//alert(typeof ids[0] + " | " +  typeof treeNode.id);
				if($.inArray(String(treeNode.id), ids)<0){
					selectedTree.addNodes(null, treeNode);
					ids.push(String(treeNode.id));
				}
			}
            if("selectedTree"==treeId){
                if($.inArray(String(treeNode.id), pre_ids)<0){
                    selectedTree.removeNode(treeNode);
                    ids.splice($.inArray(String(treeNode.id), ids), 1);
                }else{
                    top.$.jBox.tip("<spring:message code='str1228'/>", 'info');
                }
            }
		}
				
		function clearAssign(){
			var submit = function (v, h, f) {
			    if (v == 'ok'){
					var tips="";
					if(pre_ids.sort().toString() == ids.sort().toString()){
						tips = "<spring:message code='str1192'/>${role.name}<spring:message code='str1193'/>";
					}else{
						tips = "<spring:message code='str1229'/>";
					}
					ids=pre_ids.slice(0);
					selectedNodes=pre_selectedNodes;
					$.fn.zTree.init($("#selectedTree"), setting, selectedNodes);
			    	top.$.jBox.tip(tips, 'info');
			    } else if (v == 'cancel'){
			    	// <spring:message code='str1230'/>
			    	top.$.jBox.tip("<spring:message code='str1231'/>", 'info');
			    }
			    return true;
			};
			tips="<spring:message code='str1232'/>${role.name}<spring:message code='str1233'/>";
			top.$.jBox.confirm(tips, "<spring:message code='str1234'/>", submit);
		};
	</script>
</head>
<body>
	<div id="assignRole" class="row-fluid span12">
		<div class="span4" style="border-right: 1px solid #A8A8A8;">
			<p><spring:message code='str1235'/></p>
			<div id="officeTree" class="ztree"></div>
		</div>
		<div class="span4">
			<p><spring:message code='str1236'/></p>
			<div id="userTree" class="ztree"></div>
		</div>
		<div class="span4" style="padding-left:16px;border-left: 1px solid #A8A8A8;">
			<p><spring:message code='str1237'/></p>
			<div id="selectedTree" class="ztree"></div>
		</div>
	</div>
</body>
</html>
