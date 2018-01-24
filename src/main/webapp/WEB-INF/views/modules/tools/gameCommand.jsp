<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>协议管理</title>
	<meta name="decorator" content="default"/>
    <script type="text/javascript">
	function showCommand(url){
		
		var arealist=$("#modules").val();
		var idsArray = arealist.split(",");
		var commandUrl = url+"/tools/command/addGameCommand";
		var serverUrl = url+"/tools/gameArea/openGameServer";
        var content = "<form id='inputForm' class='form-horizontal' ><div><div><label style='width: 100px;'>协议:</label><div><select id='commandss' onchange='gameGetModules()'><option value=''>请选择</option>";
        for (var i = 0 ; i < idsArray.length ; i++ ){
				var id = jQuery.trim(idsArray[i]);
				content += "<option value='"+id+"'>"+id+"</option>'";
			}
        content +="</select><select id='command'><option value=''>请选择</option></select></div></div><div ><label style='width: 100px;'>服务器:</label><div><input id='serverIds' name='serverIds' value='' readonly='readonly'/>&nbsp;<input type='button' class='btn btn-primary' value='选择服务器' onclick=showContents('"+serverUrl+"')></div></div><div><label style='width: 100px;'>状态:</label><div><input type='radio' name='status' id='status' value='1' checked='checked'/>开启<input type='radio' name='status' id='status' value='0' />关闭</div></div></div></form>";
        var d = new top.dialog({
	        title: '协议管理',
	        lock:true,
	        width:'100%',
	        height:'100%',
	        content: content,
	        ok: function () {
	            var flag = false;
	            var serverIds = $(window.parent.document).find("#serverIds").val();
	            var command = $(window.parent.document).find("#command").val();
	            var status = $(window.parent.document).find("#status:checked").val();
	            var confirmDialog = top.dialog({
	                title: str171,
	                content: str172,
	                okValue: str173,
	                ok: function () {
	                    if(command != ''&& serverIds != ''){
	                        var form = $("#tableForm");
	                        form.attr('action', commandUrl);
	                        var com = $("#command").val(command); 
	                        var server = $("#serverIds").val(serverIds); 
	                        var stu = $("#status").val(status); 
	                        form.attr('command',com); 
	                        form.attr('serverIds',server);
	                        form.attr('status',stu);
	                        form.submit();
	                        d.close();
	                        d.remove();
	                    }else{
	                        tips("协议和服务器不能为空");
	                    }
	                },
	                cancelValue: str175,
	                cancel: function () {}
	            });
	            $("td.ui-dialog-body", top.document).css('height','auto');
	            confirmDialog.showModal();
	            return false;
	        },
            cancel: function(){}
	    });
	    $("td.ui-dialog-body", top.document).css('height','auto');
	    d.showModal();
	} 
	
	$(document).ready(function(){
		var sec = document.getElementById('modules');
		var modules='';
		$.ajax({
			type : "POST",
			url: "${ctx}/tools/command/gameCommandModules",
			dataType: 'text',
			before:function(){
				loading("<spring:message code='str1244'/>...")
			},
			success:function(data){
				$.each($.parseJSON(data), function(idx, obj) {
					if(modules!=''){
						modules+=",";
					}
				    modules+=idx;
				});
				$("#modules").val(modules)
				
			},error:function(request,status,e){
				alert(request+"===="+status+"======="+e);
			}
		})
	});
    </script>

</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/command/gameCommand/">协议管理</a></li>
	</ul>
	<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <input type='button' class='btn btn-primary' value='协议操作' onclick="showCommand('${ctx}')">
    ${map}
    <input type="text" style="display: none" id="modules" value="${map}">
	<tags:message content="${message}"/>
    <form id="tableForm" action="">
     <input type="text" style="display: none"  name="command" id="command"/>
     <input type="text" style="display: none"  name="serverIds" id="serverIds"/>
     <input type="text" style="display: none"  name="status" id="status"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed  ">
		<tr><th>模块</th><th>协议</th><th>状态</th></tr>
		<c:forEach items="${list}" var="item">
			<tr>
                <td>${item.module}</td>
                <td>${item.cmd}</td>
                <td><span class="label label-warning">关闭</span></td>
               
		</c:forEach>
	</table>
	<div class="pagination">${page}</div>
    </form> 
</body>
</html>
