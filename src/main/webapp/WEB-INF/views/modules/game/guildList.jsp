<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str155'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/highcharts.jsp" %>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/game/guildLog/getGuildList"><spring:message code='str155'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/game/guildLog/getGuildList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label><spring:message code='str156'/> <spring:message code='str4'/></label>
		<input id="name" name="name" type="text" value="${name}"/>
		<label><spring:message code='str3'/> <spring:message code='str4'/></label>
		<input type="text" name="createDateStart" class="input_search" size="10" readonly="readonly" maxlength="20" value="${paramMap.createDateStart}" id="startDatePicker" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDatePicker\')}'})">
		-&nbsp;<input type="text" name="createDateEnd"   class="input_search" size="10" readonly="readonly" value="${paramMap.createDateEnd}"   id="endDatePicker" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatePicker\')}'})">
		&nbsp;
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
		<shiro:hasPermission name="game.guild.export">
		<input id="btnExport" class="btn btn-primary" type="button" value="<spring:message code='str5'/>" onclick="exportXls('${ctx}/game/guildLog/exportXls')"/>
		</shiro:hasPermission>
	</form>
	<tags:message content="${message}"/>
    <form id="tableForm" action="">
	 <input type="text" style="display: none" hidden="hidden" name="guildId" id="guildId"/>
	 <input type="text" style="display: none" hidden="hidden"  name="serverId" id="serverId"/>
      <input type="text" style="display: none" hidden="hidden" name="newName" id="newName"/>
        <input type="text" style="display: none" hidden="hidden" name="yuanName" id="yuanName"/>
       <input type="text" style="display: none" hidden="hidden" name="roleId" id="roleId"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
		<tr><th><spring:message code='str157'/></th>	<th><spring:message code='str158'/></th>	<th><spring:message code='str101'/></th>	<th><spring:message code='str159'/></th>	<th><spring:message code='str160'/></th>
			 <th><spring:message code='str161'/></th><!-- <th><spring:message code='str162'/></th>-->	 <th><spring:message code='str163'/></th></tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="item">
			<tr>
				<td>${item.name}</td>
				<td>${item.leader_name}</td>
				<td>${item.add_time}</td>
				<td>${item.banner_level}</td>
				<td>${item.fighting}</td>
				 <td>${item.current_member_num}</td>
				<!-- <td> </td> -->
				
				<td>
				<shiro:hasPermission name="game.guild.delete">
				<a href="${ctx}/game/guildLog/deleteGuild?id=${item.id}" onclick="return confirmx('<spring:message code='str164'/>', this.href)"><spring:message code='str22'/></a>
				</shiro:hasPermission>&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="${ctx}/game/guildLog/membersList?guildId=${item.id}"><spring:message code='str165'/></a>
        	    &nbsp; <a  href="#"onclick="changeName('${ctx}/game/guildLog/changeName','${item.name}' ,'${item.roleId }','${item.id }')"/><spring:message code='str166'/></a>
  				&nbsp; <a href="#" onclick="tranName('${ctx}/game/guildLog/transferName','${item.leader_name}'  ,'${item.id }','${item.roleId}')"/><spring:message code='str167'/></a>

				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</form>
	<div class="pagination">${page}</div>
	
	<script type="text/javascript">
		$(document).ready(function(){
			$('table.highchart').highchartTable();
		});
		
		function changeName(url ,name,roleId,guildId){
		    var content ="<form id='inputForm' class='form-horizontal'>"
		    content = content + "<div class='control-group'><label class='control-label' style='width: 100px;'><spring:message code='str168'/>:</label><div class='controls'><input type='text' id='reason' name='reason' value='"+name+"'readonly='readonly' /></div></div></div>";
		    content = content + "<div class='control-group'><label class='control-label' style='width: 100px;'><spring:message code='str169'/>:</label><div class='controls'><input type='text' id='newName' name='newName' vaule='' );'/></div></div></div></form>"
		    var d = top.dialog({
		        title: '<spring:message code='str170'/>',
		        fixed:true,
		        //lock:true,
		        content: content,
		        ok: function () {
		            var flag = false;
		            //var guildId = $(window.parent.document).find("#guildId").val();
		            var newName = $(window.parent.document).find("#newName").val();
		          //  var serverId = $(window.parent.document).find("#serverId").val();
		           // alert(newName+"ddd"+serverId+"dd"+guildId);
		            var confirmDialog = top.dialog({
		                title: '<spring:message code='str171'/>',
		                content: '<spring:message code='str172'/>',
		                okValue: '<spring:message code='str173'/>',
		                ok: function () {
		                    this.title('<spring:message code='str174'/>');
		                    if(newName != ''){
		                    	 var form = $("#tableForm");
			                        form.attr('action', url);
			                        var newNames = $("#newName").val(newName);
			                        var serverIds = $("#serverId").val("${paramMap.currentServerId}");
			                         var yuanName=$("#yuanName").val(name);
			                         var roleIds=$("#roleId").val(roleId);
			                        form.attr('newName',newNames);
			                        form.attr('serverId',serverIds);
			                        form.attr('roleId',roleIds);
			                        form.attr('yuanName',yuanName);
			                        form.attr("guildId",$("#guildId").val(guildId));
			                        form.submit();


		                        d.close();
		                        d.remove();
		                    }else{
		                        tips("<spring:message code='str170'/>");
		                    }
		                },
		                cancelValue: '<spring:message code='str175'/>',
		                cancel: function () {}
		            });
		            $("td.ui-dialog-body", top.document).css('height','auto');
		            confirmDialog.showModal();
		            return false;
		        }
		    });
		    $("td.ui-dialog-body", top.document).css('height','auto');
		    d.showModal();
		}
		
		function tranName(url ,name,guildId ,roleId){
		    var content ="<form id='inputForm' class='form-horizontal'>"
		    content = content + "<div class='control-group'><label class='control-label' style='width: 100px;'><spring:message code='str176'/>:</label><div class='controls'><input type='text' id='reason' name='reason' value='"+name+"'readonly='readonly' /></div></div></div>";
		    content = content + "<div class='control-group'><label class='control-label' style='width: 100px;'><spring:message code='str177'/>:</label><div class='controls'><input type='text' id='newName' name='newName' vaule='' );'/></div></div></div></form>"
		    var d = top.dialog({
		        title: '<spring:message code='str178'/>',
		        fixed:true,
		        //lock:true,
		        content: content,
		        ok: function () {
		            var flag = false;
		            var newName = $(window.parent.document).find("#newName").val();
		            var confirmDialog = top.dialog({
		                title: '<spring:message code='str171'/>',
		                content: '<spring:message code='str172'/>',
		                okValue: '<spring:message code='str173'/>',
		                ok: function () {
		                    this.title('<spring:message code='str174'/>');
		                    if(newName != ''){
		                        var form = $("#tableForm");
		                        form.attr('action', url);
		                        var newNames = $("#newName").val(newName);
		                        var serverIds = $("#serverId").val("${paramMap.currentServerId}");
		                         var roleIds=$("#roleId").val(roleId);
		                        form.attr('newName',newNames);
		                        form.attr('serverId',serverIds);
		                        form.attr('roleId',roleIds);
		                        form.submit();


		                        d.close();
		                        d.remove();
		                    }else{
		                        tips("<spring:message code='str178'/>");
		                    }
		                },
		                cancelValue: '<spring:message code='str175'/>',
		                cancel: function () {}
		            });
		            $("td.ui-dialog-body", top.document).css('height','auto');
		            confirmDialog.showModal();
		            return false;
		        }
		    });
		    $("td.ui-dialog-body", top.document).css('height','auto');
		    d.showModal();
		}
	</script>

</body>
</html>
