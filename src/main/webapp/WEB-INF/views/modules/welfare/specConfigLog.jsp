<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>特殊配置日志</title>
<meta name="decorator" content="default" />

<script type="text/javascript">
	var fileName = "单服特殊配置日志";
	var fileName1 = "单账号特殊配置日志";
	 var exportDate = new Date().getTime();
     var exportDate1 = new Date().getTime();
	$(function() {
		$(".tipDiv").tooltip('show');

		//加载平台下拉框
		initPlatSelect0("#platSel3","#serverSelect3");
		$("#platSel3").on("change",function(e){
			initServerSelect0("#serverSelect3",e.val);
		})
		initPlatSelect0("#platSel4","#serverSelect4");
		$("#platSel4").on("change",function(e){
			initServerSelect0("#serverSelect4",e.val);
		})
		initSingleAccountLogTable(BASE + "/welfare/specConfig/singleAccountLogData?pid="
				+ "");
		initSingleServerLogTable(BASE
				+ "/welfare/specConfig/singleServerLogData?pid="
				+ "");

		
	});
</script>
<link href="${ctxStatic}/bootstrap-table/bootstrap-table.min.css"
	rel="stylesheet">
<script type="text/javascript"
	src="${ctxStatic}/bootstrap-table/bootstrap-table.min.js"></script>
<script type="text/javascript"
	src="${ctxStatic}/bootstrap-table/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript"
	src="${ctxStatic}/modules/welfare/singleConfig.js?timestamp=1111"></script>
<script type="text/javascript"
	src="${ctxStatic}/modules/welfare/initSelect.js?timestamp=1111"></script>
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
			<li class="active"><a href="${ctx}/welfare/specConfig/specConfigLog">特殊配置日志</a></li>

		</ul>
		<!-- ######################################单账号特殊配置 #############################################-->
		<div class="row-fluid">
			<div class="span12">
				<div class="form-actions">单账号特殊配置日志</div>
			</div>
		</div>

		<div id="toolbar">
			<div class="row-fluid">
				<div class="span12">
					<div style="margin-left: 20px;">

						<div class="form-group">
							<label for="name">平台:</label>
							<select id="platSel3"></select>



							<label for="name" style="margin-left: 20px">服务器:</label>
							<select id="serverSelect3"></select>

							<label for="name" style="margin-left: 20px">操作时间:</label> <input
								type="text" name="startTimeStart" class="input_search" size="10"
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
								<button type="button" class="btn btn-primary "
									id="dc1">

									<i class="icon-download-alt"></i> 导出
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<table id="singleAccountLogTable">

		</table>
		<!-- ######################################单服特殊配置日志#############################################-->
		<div class="row-fluid">
			<div class="span12">
				<div class="form-actions">单服特殊配置日志</div>
			</div>
		</div>

		<div id="toolbar1">
			<div class="row-fluid">
				<div class="span12">
					<div style="margin-left: 20px;">

						<div class="form-group">
							<label for="name">平台:</label>
							 <select  id="platSel4">
			   					</select> 



							<label for="name" style="margin-left: 20px">服务器:</label>
							 <select  id="serverSelect4">
			   	</select> 
							<label for="name" style="margin-left: 20px">操作时间:</label> <input
								type="text" name="startTimeStart" class="input_search" size="10"
								readonly="readonly" maxlength="20" value=""
								id="startDatePicker1"
								onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDatePicker\')}'})">
							-&nbsp; <input type="text" name="startTimeEnd"
								class="input_search" size="10" readonly="readonly" value=""
								id="endDatePicker1"
								onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatePicker\')}'})">


							<div class="btn-group" style="margin-left: 20px">
								<button type="button" class="btn btn-primary "
									data-toggle="modal" onclick="searchServer()">
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
		<table id="singleServerLogTable">

		</table>



	</div>
	<script type="text/javascript">
		function initSingleAccountLogTable(url) {
			$('#singleAccountLogTable').bootstrapTable('destroy')
					.bootstrapTable({
						url : url,
						uniqueId : "id",
						toggle : "table",
						cache : "false",
						sidePagination : "server",
						pagination : "true",
						buttonsAlign : "left",
						toolbar : "#toolbar",
						toolbarAlign : "right",
						showToggle : "true",
						showColumns : "true",
						showRefresh : "true",
						pageList : "[30, 50, 80]",
						pageSize : "30",
						showExport : "true",
						showExport : "true",
						exportDataType : "all",
						responseHandler : "dealAccountLogData",

						columns : [ {
							field : 'createDate1',
							title : '操作时间',
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
							field : 'sumCharge',
							title : '实际总充值<br/>(rmb)',
							sortable : "true",
							align : "center"
						}, {
							field : 'topCharge',
							title : '充值上限<br/>(rmb)',
							sortable : "true",
							align : "center"
						}, {
							field : 'isInfluence',
							title : '是否受批量<br/>修改影响',
							sortable : "true",
							align : "center"
						}, {
							field : 'editType',
							title : '操作类型',
							sortable : "true",
							align : "center"
						}, {
							field : 'createName',
							title : '操作人',
							sortable : "true",
							align : "center"
						}]
					});
		}
		function initSingleServerLogTable(url) {
			$('#singleServerLogTable').bootstrapTable('destroy')
					.bootstrapTable({
						url : url,
						uniqueId : "id",
						toggle : "table",
						cache : "false",
						sidePagination : "server",
						pagination : "true",
						buttonsAlign : "left",
						toolbar : "#toolbar1",
						toolbarAlign : "right",
						showToggle : "true",
						showColumns : "true",
						showRefresh : "true",
						pageList : "[2, 4, 8]",
						pageSize : "2",
						showExport : "true",
						showExport : "true",
						exportDataType : "all",
						responseHandler : "dealServerLogData",

						columns : [ {
							field : 'createDate1',
							title : '操作时间',
							sortable : "true",
							align : "center"
						},{
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
							field : 'newServiceGold',
							title : '新服资源<br/>(元宝)',
							sortable : "true",
							align : "center"
						}, {
							field : 'rResourceAmount',
							title : '大R资源金额<br/>(rmb)',
							sortable : "true",
							align : "center"
						}, {
							field : 'rResourceRatio',
							title : '大R资源<br/>比例',
							sortable : "true",
							align : "center"
						}, {
							field : 'singleChargeRatio',
							title : '单服总充值<br/>后续比例',
							sortable : "true",
							align : "center"
						}, {
							field : 'topCharge',
							title : '内部号最高<br/>充值限制(rmb)',
							sortable : "true",
							align : "center"
						}, {
							field : 'topGoldDay',
							title : '单角色每日<br/>最高额度(元宝)',
							sortable : "true",
							align : "center"
						}, {
							field : 'topHoldGold',
							title : '单角色持有<br/>最高额度(元宝)',
							sortable : "true",
							align : "center"
						}, {
							field : 'topInternalNumber',
							title : '单区内部号<br/>数量上限(个)',
							sortable : "true",
							align : "center"
						}, {
							field : 'addTimeLimit',
							title : '内部号添加<br/>时间(天)',
							sortable : "true",
							align : "center"
						},  {
							field : 'isInfluence',
							title : '是否受批量<br/>修改影响',
							sortable : "true",
							align : "center"
						},

						{
							field : 'editType',
							title : '操作类型',
							sortable : "true",
							align : "center"
						}, {
							field : 'createName',
							title : '操作人',
							sortable : "true",
							align : "center"
						}

						]

					});
		}
	</script>
</body>
</html>
