<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>福利发放日志</title>
<meta name="decorator" content="default" />

<script type="text/javascript">
	var fileName = "福利发放日志";
	$(function() {
		$(".tipDiv").tooltip('show');
		//加载平台下拉框
		initPlatSelect1("#platSel2","#serverSelect2");
		
		initTable(BASE + "/welfare/goldPool/grantGoldLogData?pid="+$("#platSel2").select2('val'));
		$("#platSel2").on("change",function(e){
			initServerSelect1("#serverSelect2",e.val);
		})
	});
</script>
<link href="${ctxStatic}/bootstrap-table/bootstrap-table.min.css"
	rel="stylesheet">
<script type="text/javascript"
	src="${ctxStatic}/bootstrap-table/bootstrap-table.min.js"></script>
<script type="text/javascript"
	src="${ctxStatic}/bootstrap-table/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript"
	src="${ctxStatic}/modules/welfare/operaGold.js?timestamp=1112"></script>
<script type="text/javascript"
	src="${ctxStatic}/modules/welfare/initSelect.js?timestamp=1112"></script>
<script type="text/javascript"
	src="${ctxStatic}/modules/welfare/welfareBase.js?timestamp=1112"></script>
<script type="text/javascript"
	src="${ctxStatic}/bootstrap-table/bootbox.js"></script>
<script type="text/javascript"
	src="${ctxStatic}/bootstrap-table/bootstrapExport.js"></script>
<script type="text/javascript"
	src="${ctxStatic}/bootstrap-table/tableExport.js"></script>
<style>
.hfont {
	font-weight: bold;
}
</style>
</head>
<body>

	<div style="margin: 20px">
		<ul class="nav nav-tabs">
			<li class="active"><a href="${ctx}/welfare/goldPool/indGoldPoolLog">福利发放日志</a></li>

		</ul>
	<div class="panel panel-default" style="margin-top: 20px;">
		<div class="row-fluid">
				<div class="span12">
					<div style="margin-left: 20px;">

						<div class="form-group">
						<br/> 
							<label for="name">平台:</label>
							<select id="platSel2"></select>

							<label for="name" style="margin-left: 20px">服务器:</label>
							<select id="serverSelect2"></select>

							<label for="name" style="margin-left: 20px">角色名:</label> <input
								id="roleName2" /> 
								<br/> <br/> 
								<label for="name" >平台账号:</label>
							<input id="userId2" /> <label for="name"
								style="margin-left: 20px">操作时间:</label> <input type="text"
								name="startTimeStart" class="input_search" size="10"
								readonly="readonly" maxlength="20" value="" id="startDatePicker"
								onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDatePicker\')}'})">
							-&nbsp; <input type="text" name="startTimeEnd"
								class="input_search" size="10" readonly="readonly" value=""
								id="endDatePicker"
								onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatePicker\')}'})">


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
	
		<table id="grantGoldTable">
		</table>


	</div>
	<script type="text/javascript">
		function initTable(url) {
			$('#grantGoldTable').bootstrapTable('destroy').bootstrapTable({
				url : url,
				uniqueId : "id",
				toggle : "table",
				cache : "false",
				sidePagination : "server",
				pagination : "true",
				buttonsAlign : "left",
			/* 	toolbar : "#toolbar",
				toolbarAlign : "right", */
				showToggle : "true",
				showColumns : "true",
				showRefresh : "true",
				pageList : "[30, 50, 80]",
				pageSize : "30",
				showExport : "true",
				showExport : "true",
				exportDataType : "all",
				responseHandler : "dealgrantGoldLogData",

				columns : [ {
					field : 'createDate1',
					title : '操作时间',
					sortable : "true",
					align : "center"
				}, {
					field : 'passageway',
					title : '通道',
					sortable : "true",
					align : "center"
				}, {
					field : 'platName',
					title : '平台',
					sortable : "true",
					align : "center"
				}, {
					field : 'serverName',
					title : '服务器',
					sortable : "true",
					align : "center"
				},

				{
					field : 'roleName',
					title : '角色名',
					sortable : "true",
					align : "center"
				}, {
					field : 'userId',
					title : '平台账号',
					sortable : "true",
					align : "center"
				}, {
					field : 'roleId',
					title : '角色ID',
					sortable : "true",
					align : "center"
				}, {
					field : 'gold',
					title : '发放元宝',
					sortable : "true",
					align : "center"
				}, {
					field : 'createName',
					title : '操作人',
					sortable : "true",
					align : "center"
				} ]

			});
		}
	</script>
</body>
</html>
