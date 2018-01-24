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
	src="${ctxStatic}/modules/rebate/configLog.js?timestamp=1112"></script>
<script type="text/javascript"
	src="${ctxStatic}/modules/rebate/initSelect.js?timestamp=1111"></script>
<script type="text/javascript"
	src="${ctxStatic}/modules/rebate/rebateBase.js?timestamp=1112"></script>
<script type="text/javascript"
	src="${ctxStatic}/bootstrap-table/bootbox.js"></script>
<script type="text/javascript"
	src="${ctxStatic}/bootstrap-table/bootstrapExport.js"></script>
<script type="text/javascript"
	src="${ctxStatic}/bootstrap-table/tableExport.js"></script>

<script type="text/javascript">
	var fileName = "返利天数配置日志";
	var fileName1 = "返利比例配置日志";
	var fileName2 = "返利物品配置日志";
	 var exportDate = new Date().getTime();
     var exportDate1 = new Date().getTime();
	 var exportDate2 = new Date().getTime();
	$(function() {
		$(".tipDiv").tooltip('show');
		initPlat("#goodsPlatSel", true);
		//天数配置日志
		dayTable(BASE + "/rebate/config/keepDayLogData");
		//比例配置日志
		ratioLogTable(BASE + "/rebate/config/rebateRatioLogData");
		//物品配置日志
		goodsLogTable(BASE + "/rebate/config/rebateGoodsLogData");
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
			<li class="active"><a href="${ctx}/rebate/config/configLog">返利配置日志</a></li>

		</ul>


		<div class="row-fluid">
			<div class="span12">
				<div class="form-actions">返利天数配置日志</div>
			</div>
		</div>
		<div id="toolbar1">
			<div class="row-fluid">
				<div class="span12">
					<div style="margin-left: 20px;">
						<div class="form-group">
							<label for="name" style="margin-left: 20px">操作时间:</label> <input
								type="text" name="startTimeStart" class="input_search" size="10"
								readonly="readonly" maxlength="20" value=""
								id="startDatePickerDay"
								onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDatePickerDay\')}'})">
							-&nbsp; <input type="text" name="startTimeEnd"
								class="input_search" size="10" readonly="readonly" value=""
								id="endDatePickerDay"
								onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatePickerDay\')}'})">

							<label for="name" style="margin-left: 20px">操作人员:</label> <input
								id="dayCreateName" />

							<div class="btn-group" style="margin-left: 20px">
								<button type="button" class="btn btn-primary "
									data-toggle="modal" onclick="searchDayLog()">
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
		<table id="dayTable">

		</table>

		<div class="row-fluid">
			<div class="span12">
				<div class="form-actions">返利比例配置日志</div>
			</div>
		</div>
		<div id="toolbar2">
			<div class="row-fluid">
				<div class="span12">
					<div style="margin-left: 20px;">
						<div class="form-group">
							<label for="name" style="margin-left: 20px">操作时间:</label> <input
								type="text" name="startTimeStart" class="input_search" size="10"
								readonly="readonly" maxlength="20" value=""
								id="startDatePickerRatio"
								onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDatePickerRatio\')}'})">
							-&nbsp; <input type="text" name="startTimeEnd"
								class="input_search" size="10" readonly="readonly" value=""
								id="endDatePickerRatio"
								onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatePickerRatio\')}'})">

							<label for="name" style="margin-left: 20px">操作类型:</label> <select
								id="ratioEditSel">
								<option value="">全部</option>
								<option value="添加">添加</option>
								<option value="修改">修改</option>
							</select> <label for="name" style="margin-left: 20px">操作人员:</label> <input
								id="ratioCreateName" />

							<div class="btn-group" style="margin-left: 20px">
								<button type="button" class="btn btn-primary "
									data-toggle="modal" onclick="searchRatioLog()">
									<i class="icon-search"></i> 查 询
								</button>
								<button type="button" class="btn btn-primary " id="dc1">

									<i class="icon-download-alt"></i> 导出
								</button>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<table id="ratioLogTable">

		</table>


		<div class="row-fluid">
			<div class="span12">
				<div class="form-actions">返利物品配置日志</div>
			</div>
		</div>
		<div class="panel panel-default" style="margin-top: 20px;">
			<div class="row-fluid">
				<div class="span12">
					<div style="margin-left: 20px;">
						<div class="form-group">
							<label for="name" >操作时间:</label> <input
								type="text" name="startTimeStart" class="input_search" size="10"
								readonly="readonly" maxlength="20" value=""
								id="startDatePickerGoods"
								onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDatePickerGoods\')}'})">
							-&nbsp; <input type="text" name="startTimeEnd"
								class="input_search" size="10" readonly="readonly" value=""
								id="endDatePickerGoods"
								onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatePickerGoods\')}'})">

							<label for="name" style="margin-left: 20px">平台:</label> <select
								id="goodsPlatSel"></select> <label for="name"
								style="margin-left: 20px">操作类型:</label> <select
								id="goodsEditSel">
								<option value="">全部</option>
								<option value="添加">添加</option>
								<option value="修改">修改</option>
							</select>
							<br/><br/>
							 <label for="name" >操作人员:</label> <input
								id="goodsCreateName" /> <label for="name"
								style="margin-left: 20px">物品属性:</label> <select id="goodsProp">
								<option value="">全部</option>
								<option value="0">绑定</option>
								<option value="1">非绑定</option>
							</select> <label for="name" style="margin-left: 20px">物品名称:</label> <input
								id="goodsName" /> <label for="name" style="margin-left: 20px">物品ID:</label>
							<input id="goodsId" />

							<div class="btn-group" style="margin-left: 20px">
								<button type="button" class="btn btn-primary "
									data-toggle="modal" onclick="searchGoodsLog()">
									<i class="icon-search"></i> 查 询
								</button>
								<button type="button" class="btn btn-primary " id="dc2">

									<i class="icon-download-alt"></i> 导出
								</button>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<table id="goodsLogTable">

		</table>
	</div>
	<script type="text/javascript">
		function goodsLogTable(url) {
			$('#goodsLogTable').bootstrapTable('destroy').bootstrapTable({
				url : url,
				uniqueId : "id",
				toggle : "table",
				cache : "false",
				sidePagination : "server",
				pagination : "true",
				buttonsAlign : "left",
/* 				toolbar : "#toolbar3",
				toolbarAlign : "right", */
				showToggle : "true",
				showColumns : "true",
				showRefresh : "true",
				pageList : "[10, 15, 30]",
				pageSize : "10",
				showExport : "true",
				showExport : "true",
				exportDataType : "all",
				responseHandler : "dealGoodsLogData",

				columns : [ {
					field : 'createTime1',
					title : '操作时间',
					sortable : "true",
					align : "center"
				}, {
					field : 'platName',
					title : '平台',
					sortable : "true",
					align : "center"
				}, {
					field : 'goodsName',
					title : '物品名称',
					sortable : "true",
					align : "center"
				}, {
					field : 'goodsId',
					title : '物品ID',
					sortable : "true",
					align : "center"
				}, {
					field : 'goodsDesc',
					title : '物品描述',
					sortable : "true",
					align : "center"
				}, {
					field : 'goodsPrice',
					title : '物品单价(虚拟元宝)',
					sortable : "true",
					align : "center"
				}, {
					field : 'goodsProp1',
					title : '物品属性',
					sortable : "true",
					align : "center"
				}, {
					field : 'exchangeLimit1',
					title : '兑换限制',
					sortable : "true",
					align : "center"
				}, {
					field : 'topNum',
					title : '上限数量',
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
				} ]

			});
		}

		function ratioLogTable(url) {
			$('#ratioLogTable').bootstrapTable('destroy').bootstrapTable({
				url : url,
				uniqueId : "id",
				toggle : "table",
				cache : "false",
				sidePagination : "server",
				pagination : "true",
				buttonsAlign : "left",
				toolbar : "#toolbar2",
				toolbarAlign : "right",
				showToggle : "true",
				showColumns : "true",
				showRefresh : "true",
				pageList : "[10, 15, 30]",
				pageSize : "10",
				showExport : "true",
				showExport : "true",
				exportDataType : "all",
				responseHandler : "dealRatioLogData",

				columns : [ {
					field : 'createTime1',
					title : '操作时间',
					sortable : "true",
					align : "center"
				}, {
					field : 'dayAmount',
					title : '单日累充金额(元)',
					sortable : "true",
					align : "center"
				}, {
					field : 'rebateRatio',
					title : '返还虚拟元宝比例(%)',
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

		function dayTable(url) {
			$('#dayTable').bootstrapTable('destroy').bootstrapTable({
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
				pageList : "[10, 15, 30]",
				pageSize : "10",
				showExport : "true",
				showExport : "true",
				exportDataType : "all",
				responseHandler : "dealDayLogData",

				columns : [ {
					field : 'createTime1',
					title : '操作时间',
					sortable : "true",
					align : "center"
				}, {
					field : 'day',
					title : '天数',
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
				} ]

			});
		}
	</script>
</body>
</html>
