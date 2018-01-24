<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code='str1217' /></title>
<meta name="decorator" content="default" />

<script type="text/javascript">
	var fileName = "福利配置日志";
	$(function() {
		$(".tipDiv").tooltip('show');
		//加载平台下拉框
		initAllPlat("#platSel3",true);

		initTable(BASE+"/welfare/common/settingLogData");
	});
</script>
<link href="${ctxStatic}/bootstrap-table/bootstrap-table.min.css"
	rel="stylesheet">
<script type="text/javascript"
	src="${ctxStatic}/bootstrap-table/bootstrap-table.min.js"></script>
<script type="text/javascript"
	src="${ctxStatic}/bootstrap-table/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript"
	src="${ctxStatic}/modules/welfare/commonTemp.js?timestamp=1111"></script>
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
			<li class="active"><a href="${ctx}/welfare/common/temp">福利配置日志</a></li>
			
		</ul>
	<div class="panel panel-default" style="margin-top: 20px;">
	<div class="row-fluid">
			<div class="span12">
		<div style="margin-left:20px;">
			
			<div class="form-group">
			<br/>
			 <label for="name" >平台:</label>
			   	<select  id="platSel3">
			   	</select>
			   	
			   	  <label for="name" style="margin-left: 20px" >奖金池类型:</label>
			   	<select id="goldPoolSelect2">
			   		<option value="">全部</option>
			   		<option value="独代奖金池">独代奖金池</option>
			   		<option value="平台奖金池">平台奖金池</option>
				</select>
			   	
			   <label for="name" style="margin-left: 20px" >平台性质:</label>
			   	<select id="platNatureSel2">
			   		<option value="">全部</option>
			   		<option value="网盟">网盟</option>
			   		<option value="公会">公会</option>
			   	</select>
			   	
			   	 <label for="name" style="margin-left: 20px" >操作时间:</label>
			   		<input type="text" name="startTimeStart" class="input_search" size="10" 
			   		readonly="readonly" maxlength="20" value="" 
			   		id="startDatePicker" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDatePicker\')}'})">
					-&nbsp;
					<input type="text" name="startTimeEnd"   class="input_search" size="10" 
					readonly="readonly" value=""   
					id="endDatePicker" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatePicker\')}'})">
			  
			   
			    <div class="btn-group" style="margin-left: 20px" >
			        <button type="button" class="btn btn-primary " data-toggle="modal" onclick="searchLog()">
		            <i class="icon-search"></i>
		            查 询
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
		<table id="commonSettingLogTable" >
		</table>


	</div>
	<script type="text/javascript">
	function initTable(url){
		$('#commonSettingLogTable').bootstrapTable('destroy').bootstrapTable({
			url:url,
			uniqueId:"id",
			toggle:"table",
			cache:"false",
			sidePagination:"server",
			pagination:"true",
			buttonsAlign:"left" ,
		/* 	toolbar:"#toolbar",
			toolbarAlign:"right" , */
			showToggle:"true" ,
			showColumns:"true" ,
			showRefresh:"true",
			pageList:"[30, 50, 80]" ,
			pageSize:"30",
			showExport:"true",
	        showExport:"true",
	        exportDataType:"all",
			responseHandler:"dealSettingLogData",
		
            columns: [

						{
    						field: 'createDate1',
    						title: '操作时间',
    						sortable:"true",
    						align:"center"
						},
						{
    						field: 'platName',
    						title: '平台',
    						sortable:"true",
    						align:"center"
						},
						{
    						field: 'platNature',
    						title: '平台性质',
    						sortable:"true",
    						align:"center"
						},
						{
    						field: 'goldPoolCategory',
    						title: '奖金池类别',
    						sortable:"true",
    						align:"center"
						},
						{
    						field: 'newServiceGold',
    						title: '新服资源',
    						sortable:"true",
    						align:"center"
						},
						{
    						field: 'rResourceAmount',
    						title: '大R资源<br/>金额(RMB)',
    						sortable:"true",
    						align:"center"
						},
						{
    						field: 'rResourceRatio1',
    						title: '大R资源<br/>比例',
    						sortable:"true",
    						align:"center"
						},
						{
    						field: 'singleChargeRatio1',
    						title: '单服总充值<br/>后续比例',
    						sortable:"true",
    						align:"center"
						},
						{
    						field: 'topCharge',
    						title: '内部号最高<br/>充值限制(RMB)',
    						sortable:"true",
    						align:"center"
						},
						{
    						field: 'topGoldDay',
    						title: '单角色每日<br/>最高限额(元宝)',
    						sortable:"true",
    						align:"center"
						},
						{
    						field: 'topHoldGold',
    						title: '单角色最高<br/>持有额度(元宝)',
    						sortable:"true",
    						align:"center"
						},
						{
    						field: 'topInternalNumber',
    						title: '单区内部号<br/>数量上限(个)',
    						sortable:"true",
    						align:"center"
						},
						
						{
    						field: 'addTimeLimit',
    						title: '内部号添加<br/>时间限制(天)',
    						sortable:"true",
    						align:"center"
						},
						{
    						field: 'status1',
    						title: '状态',
    						sortable:"true",
    						align:"center"
						},
						{
    						field: 'editType1',
    						title: '操作类型',
    						sortable:"true",
    						align:"center"
						},
						{
    						field: 'createName',
    						title: '操作人',
    						sortable:"true",
    						align:"center"
						}
						
                  ]
            
        });
	}
	</script>
</body>
</html>
