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
	src="${ctxStatic}/modules/rebate/applyLog.js?timestamp=1114"></script>
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
	var fileName = "返利操作日志";
	$(function() {
		$(".tipDiv").tooltip('show');
		initPlatSelect0("#platSel2","#serverSelect2");
		$("#platSel2").on("change",function(e){
			initServerSelect0("#serverSelect2",e.val);
		})
		initTable(BASE + "/rebate/self/applyLogData");

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
							
							<label for="name">服务器:</label>
							<select id="serverSelect2"></select>
							
							<label for="name">状态:</label>
							<select id="status">
								<option value="">全部</option>
								<option value="">成功</option>
								<option value="">失败</option>
							</select>
							<br/><br/>
							<label for="name" >操作时间:</label> <input
								type="text" name="startTimeStart" class="input_search" size="10"
								readonly="readonly" maxlength="20" value=""
								id="startDatePickerDay"
								onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDatePickerDay\')}'})">
							-&nbsp; <input type="text" name="startTimeEnd"
								class="input_search" size="10" readonly="readonly" value=""
								id="endDatePickerDay"
								onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatePickerDay\')}'})">
							
							<label for="name" style="margin-left: 20px">角色名:</label> <input
								id="roleName" /> 
								<br/><br/>
							<label for="name" >平台账号:</label> <input
								id="userId" /> 
							<label for="name">角色ID:</label><input
								id="roleId" /> 
							<label for="name">操作人:</label><input
								id="createName" /> 
							

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
		<table id="applyLogTable">

		</table>


		<div class="modal fade" id="detailModal" tabindex="-1"
			role="dialog" aria-labelledby="editLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="newPageOut">操作详情</h4>
					</div>
					<div class="modal-body">
						<form role="form" id="editForm">
							<div class="form-group">
								<label for="editDayAmount" >邮件标题: </label> <input
									class="form-control" name="detailTitle" id="detailTitle" disabled></input>
							</div>
							<br/>
							<div class="form-group">
								<label for="editRatio">邮件内容: </label> <textarea
									class="form-control" name="detailText" id="detailText" disabled></textarea>
							</div>
								<br/>
								<table id="detailTable">

								</table>
						</form>
					</div>
				</div>
			</div>
		</div>

	</div>
	<script type="text/javascript">
		function initTable(url) {
			$('#applyLogTable').bootstrapTable('destroy').bootstrapTable({
				url : url,
				uniqueId : "id",
				toggle : "table",
				cache : "false",
				sidePagination : "server",
				pagination : "true",
				buttonsAlign : "left",
				/* toolbar : "#toolbar",
				toolbarAlign : "right", */
				showToggle : "true",
				showColumns : "true",
				showRefresh : "true",
				pageList : "[30, 50, 80]",
				pageSize : "30",
				showExport : "true",
				showExport : "true",
				exportDataType : "all",
				responseHandler : "dealApplyLogData",

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
					field : 'gold',
					title : '兑换元宝',
					sortable : "true",
					align : "center"
				}, {
					field : 'status1',
					title : '状态',
					sortable : "true",
					align : "center"
				},{
					field : 'createName',
					title : '操作人',
					sortable : "true",
					align : "center"
				},{
					field : 'detail',
					title : '详情',
					sortable : "true",
					align : "center"
				}
				]

			});
		}
		
		function initDetailTable(url) {
			$('#detailTable').bootstrapTable('destroy').bootstrapTable({
				url : url,
				uniqueId : "id",
				toggle : "table",
				cache : "false",
				showToggle : false,
				showColumns : false,
				showRefresh : false,
				showExport : false,

				columns : [ {
					field : 'goodsName',
					title : '物品名称',
					sortable : "true",
					align : "center" 
    			},{
					field : 'goodsId',
					title : '物品ID',
					sortable : "true",
					align : "center"
				}, {
					field : 'goodsProp',
					title : '物品属性',
					sortable : "true",
					align : "center"
				},{
					field : 'num',
					title : '兑换数量',
					sortable : "true",
					align : "center"
				}, {
					field : 'sumMoney',
					title : '总价(虚拟元宝)',
					sortable : "true",
					align : "center"
				}]

			});
		}
	</script>
</body>
</html>
