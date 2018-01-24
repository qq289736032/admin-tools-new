<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>发放排名</title>
<meta name="decorator" content="default" />

<script type="text/javascript">
	var type = "plat";
	var fileName = "发放排名";
	$(function() {
		$(".tipDiv").tooltip('show');
		//加载平台下拉框
		initPlatSelect1("#platSel2","#serverSelect2");
		
		initTable(BASE + "/welfare/goldPool/rankData?pid="+$("#platSel2").select2("val")+"&type="+type);
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
	src="${ctxStatic}/modules/welfare/rank.js?timestamp=1111"></script>
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
			<li class="active"><a href="${ctx}/welfare/goldPool/platIssueRank">发放排名</a></li>

		</ul>

		<div id="toolbar">
			<div class="row-fluid">
				<div class="span12">
					<div style="margin-left: 20px;">

						<div class="form-group">
							<label for="name">平台:</label>
							<select id="platSel2"></select>

							<label for="name" style="margin-left: 20px" >服务器:</label>
			   					<select  id="serverSelect2">
			   					</select>

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
									data-toggle="modal" onclick="searchRank()">
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
		<table id="rankPlatTable">
		</table>


	</div>
	<script type="text/javascript">
		function initTable(url) {
			$('#rankPlatTable').bootstrapTable('destroy').bootstrapTable({
				url : url,
				uniqueId : "id",
				toggle : "table",
				cache : "false",
				/* sidePagination : "server", */
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
				responseHandler : "dealRankPlatData",
				sortName:"index",
				sortType:"asc",

				columns : [ 
			    {
					field : 'index',
					title : '排名',
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
					field : 'usePeople',
					title : '使用人',
					sortable : "true",
					align : "center"
				}, {
					field : 'usePeoplePost',
					title : '使用人职务',
					sortable : "true",
					align : "center"
				}, {
					field : 'vipLevel',
					title : 'VIP等级',
					sortable : "true",
					align : "center"
				}, {
					field : 'sumYb',
					title : '累计发放<br/>元宝',
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
					field : 'numOfDays',
					title : '连续未登<br/>天数',
					sortable : "true",
					align : "center"
				},

				{
					field : 'level',
					title : '等级',
					sortable : "true",
					align : "center"
				}, {
					field : 'combat',
					title : '当前战力',
					sortable : "true",
					align : "center"
				}, {
					field : 'surplusYb',
					title : '身上元宝',
					sortable : "true",
					align : "center"
				}, {
					field : 'status1',
					title : '状态',
					sortable : "true",
					align : "center"
				}
				]

			});
		}
	</script>
</body>
</html>
