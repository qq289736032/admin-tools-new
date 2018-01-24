<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str1205'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treeview.jsp" %>
    <%@include file="/WEB-INF/views/include/dialog.jsp" %>
	<script type="text/javascript">
	function execute(url){
		var executeUrl = url+"/tools/code/execute";
		var serverUrl = url+"/tools/gameArea/openGameServer";
	    var content="<form id='inputForm' class='form-horizontal'><div class='control-group'><label class='control-label'>全部服务器:</label><div class='controls'><shiro:hasGlobalRoleTag><input type='radio' id='global' name='isGlobal' value='1'  onclick=$('#gameServerTreeDiv').hide()>全部服务器</shiro:hasGlobalRoleTag><input type='radio' id='global' name='isGlobal' value='0' checked='checked' onclick=$('#gameServerTreeDiv').show()>指定服务器</div><div class='controls' id='gameServerTreeDiv'><input id='serverIds' name='serverIds' value='${paramMap.currentServerId }'  readonly/><input type='button' class='btn btn-primary' value='<spring:message code='str15'/>' onclick=showContents('"+serverUrl+"')></div></div><div class='control-group'><label class='control-label'>groovy:</label><div class='controls'><textarea name='code' id='code' style='overflow:scroll; overflow-x:hidden;width:690px; height:550px;resize: none' rows='15' class='input-xxlarge {required:true,maxlength:100}''></textarea></div></div></form>";

	    var d = new top.dialog({
			title: 'groovy',
	        lock:true,
	        width:'100%',
	        height:'100%',
	        content: content,
	        ok: function () {
	        	var flag = false;
	            var serverIds = $(window.parent.document).find("#serverIds").val();
	            var code = $(window.parent.document).find("#code").val();
	            var global = $(window.parent.document).find("#global:checked").val();

	            var confirmDialog = top.dialog({
	                title: '提示',
	                content: '确定提交数据',
	                okValue: '确定',
	                ok: function () {
	                	if(global=='1'){
	            			_remoteCall('${ctx}/tools/code/execute',{code:code,isGlobal:global},function(result){
	            	            if(result.success){
	            	            	console.log(result.data);
	            	                  tips("<spring:message code='str1465'/>");
	            	                $("#tips").html(result.data);
	            	                window.location.href="${ctx}/tools/code/code";

	            	            }else{
	            	            	 tips(result.data);
	            	            }
	            	        })
	            	        d.close();
	                        d.remove();
	            		}else{
	            			_remoteCall('${ctx}/tools/code/execute',{code:code,serverIds:serverIds,isGlobal:global},function(result){
	            	            if(result.success){
	            	            	console.log(result.data);
	            	            	 tips(result.data);
	            	                $("#tips").html(result.data);
	            	                window.location.href="${ctx}/tools/code/code";
	            	            }else{
	            	            	 tips(result.data);
	            	            }
	            	        })
	            	        d.close();
	                        d.remove();
	            		}
	                },
	                cancelValue: '取消',
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
    </script>
</head>
<body>
	<input type='button' class='btn btn-primary' value='添加' onclick="execute('${ctx}')">
    <form id="searchForm" action="" method="post" class="breadcrumb form-search">
     <input type="text" style="display: none" hidden="hidden" name="code" id="code"/>
     <input type="text" style="display: none" hidden="hidden" name="serverIds" id="serverIds"/>
     <input type="text" style="display: none" hidden="hidden" name="isGlobal" id="global"/>
	     <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
	     <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
   </form>
     <table id="contentTable" class="table table-striped table-bordered table-condensed table-hover">
    <thead><tr><th>id</th><th>服务器范围</th><th>code</th><th>创建人</th><th>用户id</th><th>时间</th></tr></thead>
    <tbody>
    <c:forEach items="${page.list}" var="item">
        <tr>
            <td>${item.id}</td>
             <td title="${item.serverIds}">
                    <c:if test="${item.is_global == 1}">全部服务器</c:if>
					<c:if test="${item.is_global == 0}">${item.serverIds}</c:if>				
				</td>
            <td>${item.code}</td>
            <td>${item.create_name}</td>
            <td>${item.create_by}</td>
			<td><fmt:formatDate value="${item.add_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
    <div class="pagination">${page}</div>
</body>
</html>
