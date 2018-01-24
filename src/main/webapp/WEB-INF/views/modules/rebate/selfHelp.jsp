<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code='str1217' /></title>
<meta name="decorator" content="default" />

<link href="${ctxStatic}/bootstrap-table/bootstrap-table.min.css"
	rel="stylesheet">
<script type="text/javascript"
	src="${ctxStatic}/bootstrap-table/bootstrap-table.min.js"></script>
<script type="text/javascript"
	src="${ctxStatic}/bootstrap-table/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript"
	src="${ctxStatic}/modules/rebate/selfHelp.js?timestamp=1114"></script>
<script type="text/javascript"
	src="${ctxStatic}/modules/rebate/initSelect.js?timestamp=1111"></script>
<script type="text/javascript"
	src="${ctxStatic}/modules/rebate/rebateBase.js?timestamp=1112"></script>
<script type="text/javascript"
	src="${ctxStatic}/modules/rebate/thenBy.min.js?timestamp=1111"></script>
<script type="text/javascript"
	src="${ctxStatic}/bootstrap-table/bootbox.js"></script>
<script type="text/javascript"
	src="${ctxStatic}/bootstrap-table/bootstrapExport.js"></script>
<script type="text/javascript"
	src="${ctxStatic}/bootstrap-table/tableExport.js"></script>
	
<script type="text/javascript">
	var fileName = "自助返利";
	$(function() {
		$(".tipDiv").tooltip('show');
		initPlatSelect1("#platSel2","#serverSelect2");
		$("#platSel2").on("change",function(e){
			initServerSelect1("#serverSelect2",e.val);
		})
		loadKeepDayData();
		initTable(BASE + "/rebate/self/selfHelpData?pid="+$("#platSel2").select2('val'));
		
	});
</script>
<style>
.hfont {
	font-weight: bold;
}
</style>
</head>
<body>
	<div style="margin: 20px">
		<ul class="nav nav-tabs">
			<li class="active"><a href="${ctx}/rebate/self/selfHelp">自助返利</a></li>

		</ul>

		<div class="row-fluid">
			<div class="span12">
				<div class="form-actions">返利玩家列表     <font color="red">本系统只保留 <font id="day">0</font> 日的返还虚拟元宝，超出日期未返还的将失效。</font></div>
			</div>
		</div>
		<div class="panel panel-default" style="margin-top: 20px;">
			<div class="row-fluid">
				<div class="span12">
					<div style="margin-left: 20px;">
						<div class="form-group">
							<br/>
							<label for="name">平台:</label>
							<select id="platSel2"></select>
							
							<label for="name">服务器:</label>
							<select id="serverSelect2"></select>
							<label for="name" style="margin-left: 20px">角色名:</label> <input
								id="roleName" /> 
							<label for="name" style="margin-left: 20px">平台账号:</label> <input
								id="userId" /> 
							<label for="name">角色ID:</label><input
								id="roleId" /> 
							

							<div class="btn-group" style="margin-left: 20px">
								<button type="button" class="btn btn-primary "
									data-toggle="modal" onclick="search()">
									<i class="icon-search"></i> 查 询
								</button>
								<button type="button" class="btn btn-primary " id="dc">

									<i class="icon-download-alt"></i> 导出
								</button>
								
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<table id="selfHelpTable">

		</table>

	</div>
	<script type="text/javascript">
		function initTable(url) {
			$('#selfHelpTable').bootstrapTable('destroy').bootstrapTable({
				url : url,
				uniqueId : "id",
				toggle : "table",
				cache : "false",
				/* sidePagination : "server", */
				pagination : true,
				buttonsAlign : "left",
/* 				toolbar : "#toolbar",
				toolbarAlign : "right", */
				showToggle : "true",
				showColumns : "true",
				showRefresh : "true",
				pageList : "[30, 50, 80]",
				pageSize : "30",
				showExport : "true",
				showExport : "true",
				exportDataType : "all",
				responseHandler : "dealSelfHelpData",

				columns : [ {
  	  				checkbox: true  
    			},{
					field : 'pid',
					title : '平台',
					sortable : "true",
					align : "center"
				}, {
					field : 'serverName',
					title : '服务器',
					sortable : "true",
					align : "center"
				},{
					field : 'roleName',
					title : '角色名',
					sortable : "true",
					align : "center"
				}, {
					field : 'userId',
					title : '平台账号',
					sortable : "true",
					align : "center"
				},
				{
					field : 'roleId',
					title : '角色ID',
					sortable : "true",
					align : "center"
				}, {
					field : 'allowGold',
					title : '可返虚拟元宝数量',
					sortable : "true",
					align : "center"
				}, {
					field : 'usedGold',
					title : '已返虚拟元宝数量',
					sortable : "true",
					align : "center"
				},{
					field : 'edit',
					title : '操作',
					sortable : "true",
					align : "center"
				}
				]

			});
		}
	</script>
</body>
</html>
