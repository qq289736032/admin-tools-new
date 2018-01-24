<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code='str1418'/></title>
<meta name="decorator" content="default" />
<%@include file="/WEB-INF/views/include/treeview.jsp"%>
<script type="text/javascript">
		var tree="";
		var roleId="";
	    function showUser(id){
	    	roleId=id;
			 var tb = document.getElementById("contentTable1");
		    //<spring:message code='str1419'/>2 <spring:message code='str1420'/>1
		    for (var n = tb.rows.length - 1; n > 0; n--) {
		        tb.deleteRow(n);
		    }
				$.ajax({
					url:'${ctx}/tools/jurisdiction/selectUser',
					type:'post',
					data:{id:id},
					dataType:'json',
					before:function(){
			            loading("<spring:message code='str1189'/>...")
			        },
					success:function(data){
						var tbody=$('#tbody');
						$.each(data,function(index,item){
							OutputData(tbody,item);
						}); 
						
					},error:function(request,status,e){
						alert(request+"===="+status+"======="+e);
					}

				});
				
				 function OutputData(tbody,item){
					tbody.append("<tr id='tr'>"+"<td>"+item.login_name+"</td>"+"<td>"+item.name+"</td>"+"<td>"+item.phone+"</td>"+"<td>"+item.mobile+"</td>"+"</tr>");
				} 
				 
				 $("#showMenu").css('display','block')
			    	$.ajax({
						url:'${ctx}/tools/jurisdiction/selectMenu',
						data:{id:id},
						type:'post',
						dataType:'json',
						before:function(){
				            loading("<spring:message code='str1189'/>...")
				        }, 
						success:function(data, status){
							var setting = {check:{enable:true,nocheckInherit:true},view:{selectedMulti:false},
									data:{simpleData:{enable:true}},callback:{beforeClick:function(id, node){
										tree.checkNode(node, !node.checked, true, true);
										return false;
									}}};
							
							// <spring:message code='str143'/>-<spring:message code='str1421'/>
							var zNodes=[
									<c:forEach items="${menuList}" var="menu">{id:'${menu.id}', pId:'${not empty menu.parent.id?menu.parent.id:0}', name:"${not empty menu.parent.id?menu.name:'<spring:message code='str1422'/>'}"},
						            </c:forEach>];
							// <spring:message code='str1423'/>
							tree = $.fn.zTree.init($("#menuTree"), setting, zNodes);
							
							// <spring:message code='str1424'/>
							tree.expandAll(true); 
							$.each(data,function(index,item){
								var node = tree.getNodeByParam("id", item.id); 
								try{tree.checkNode(node, true, false);}catch(e){}
								
							});	
						},error:function(request,status,e){
							alert(request+"===="+status+"======="+e);
						}

					}); 
				 
			 
		}
	    
	   	function subTree(){
			var ids = [], nodes = tree.getCheckedNodes(true);
		 	for(var i=0; i<nodes.length; i++) {
				ids.push(nodes[i].id);
			}
			 $.ajax({
				url:'${ctx}/tools/jurisdiction/save?id='+roleId+'&&menuId='+ids,
				type:'post',
				success:function(data){
					 alert("<spring:message code='str1425'/>");
					
				},error:function(request,status,e){
					alert(request+"===="+status+"======="+e);
				}

			}); 
	   	}
	   		    
	 </script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a
			href="${ctx}/tools/jurisdiction/jurisdictionAllocationList"><spring:message code='str1418'/></a></li>
	</ul>
	<tags:message content="${message}" />
	<br/>
	<br/>
	<div id="assignRole" class="row-fluid span12">
		<div class="span6" style="border-right: 1px solid #A8A8A8">
			<div class=""
				style="width: 100%; height: 300px; line-height: 300px; overflow-y:scroll;border:1px solid #ccc;">
				<table id="contentTable"
					class="table table-striped table-bordered table-condensed">
					<tr class="fixedHead">
						<th><spring:message code='str653'/></th>
						<th><spring:message code='str1426'/></th>
					</tr>
					<c:forEach items="${list}" var="role">
						<tr>
							<td onclick="showUser('${role.id}')" ><a href="javascript:void(0);"
								class=selectRole>${role.name}</a></td>
							<td><c:if test="${role.isGlobal == 1}"><spring:message code='str98'/></c:if> <c:if
									test="${role.isGlobal == 0}"><spring:message code='str1379'/></c:if></td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<br />
			<div class="st"
				style="width: 100%; height: 300px; line-height: 300px; overflow-y:scroll;border:1px solid #ccc;">
				<table id="contentTable1"
					class="table table-striped table-bordered table-condensed">
					<thead>
						<tr class="ts">
							<th><spring:message code='str1427'/></th>
							<th><spring:message code='str1428'/></th>
							<th><spring:message code='str1429'/></th>
							<th><spring:message code='str1430'/></th>
						</tr>
					</thead>
					<tbody id="tbody">
					</tbody>
				</table>
			</div>
		</div>
		<div class="span6" style="display: none;" id="showMenu">
			<form:form id="inputForm" modelAttribute="role"
				action="${ctx}/tools/jurisdiction/save" method="post"
				class="form-horizontal">
				<div class="control-group"
					style="width: 100%; height: 520px; line-height: 600px; overflow-y:scroll;border:1px solid #ccc;">
					<label class="control-label"><spring:message code='str1431'/>:</label>
					<div class="controls">
						<div id="menuTree" class="ztree"
							style="margin-top: 3px; float: left;"></div>
					</div>
				</div>
				<div class="form-actions">
					<input id="btnSubmit" class="btn btn-primary" type="button"
						value="<spring:message code='str1176'/> <spring:message code='str1177'/>" onclick="subTree()" />
				</div>
			</form:form>
		</div>
	</div>
</body>
</html>
