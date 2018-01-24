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
	src="${ctxStatic}/modules/rebate/analysis.js?timestamp=1112"></script>
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
	var fileName = "返利分析";
	$(function() {
		$(".tipDiv").tooltip('show');
		initPlat("#platSel2",true);
		initTable(BASE + "/rebate/analysis/analysisData");

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
			<li class="active"><a href="${ctx}/rebate/self/applyLog">返利操作日志</a></li>

		</ul>

		<div class="row-fluid">
			<div class="span12">
				<div class="form-actions">返利操作日志    </div>
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
							
							<label for="name" style="margin-left: 20px">物品名称:</label> <input
								id="goodsName" /> 
							<label for="name" style="margin-left: 20px">物品ID:</label> <input
								id="goodsId" /> 
							
							<label for="name" style="margin-left: 20px">操作时间:</label> <input
								type="text" name="startTimeStart" class="input_search" size="10"
								readonly="readonly" maxlength="20" value=""
								id="startDatePickerDay"
								onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDatePickerDay\')}'})">
							-&nbsp; <input type="text" name="startTimeEnd"
								class="input_search" size="10" readonly="readonly" value=""
								id="endDatePickerDay"
								onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatePickerDay\')}'})">
							
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
		<table id="analysisTable">

		</table>



	</div>
	<script type="text/javascript">
		function initTable(url) {
			$('#analysisTable').bootstrapTable('destroy').bootstrapTable({
				url : url,
				uniqueId : "id",
				toggle : "table",
				cache : "false",
				pagination : "true",
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
				responseHandler : "dealData",

				columns : [{
					field : 'pid',
					title : '平台',
					sortable : "true",
					align : "center"
				},{
					field : 'goodsName',
					title : '物品名称',
					sortable : "true",
					align : "center"
				}, {
					field : 'goodsId',
					title : '物品ID',
					sortable : "true",
					align : "center"
				},
				{
					field : 'sumNum',
					title : '兑换总数量',
					sortable : "true",
					align : "center"
				}, {
					field : 'sumNumRatio1',
					title : '兑换总数量占比',
					sortable : "true",
					align : "center"
				}, {
					field : 'times',
					title : '兑换次数',
					sortable : "true",
					align : "center"
				},{
					field : 'timesRatio1',
					title : '兑换次数占比',
					sortable : "true",
					align : "center"
				},{
					field : 'countRoleId',
					title : '兑换人数',
					sortable : "true",
					align : "center"
				}
				,{
					field : 'countRoleIdRatio1',
					title : '兑换人数占比',
					sortable : "true",
					align : "center"
				}
				]

			});
		}
		
	</script>
</body>
</html>
