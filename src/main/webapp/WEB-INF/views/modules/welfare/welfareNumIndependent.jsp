<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code='str1217' /></title>
<meta name="decorator" content="default" />

<script type="text/javascript">
	var fileName = "福利号配置";
	var passageway = "独代通道";
	$(function() {
		$(".tipDiv").tooltip('show');
		addNumFormValid();
		//加载平台下拉框
		initPlatSelect2("#platSel1","#serverSelect1");
		initPlatSelect1("#platSel2","#serverSelect2");
	
		initTable(BASE+"/welfare/num/numData?pid="+$("#platSel2").select2('val'));
		
		$("#platSel1").on("change",function(e){
			initServerSelect2("#serverSelect1",e.val);
		})
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
	src="${ctxStatic}/modules/welfare/welfareNum.js?timestamp=1111"></script>
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
			<li class="active"><a href="${ctx}/welfare/num/independentNum">福利号管理</a></li>
			
		</ul>


		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span8">
					<form class="form-horizontal" id="numForm">
						<div class="control-group">

							<!-- Select Basic -->
							<label class="control-label">平台</label>
							<div class="controls">
								<select id="platSel1">
								</select>
							</div>

						</div>
						<div class="control-group">

							<!-- Select Basic -->
							<label class="control-label">服务器</label>
							<div class="controls">
								<select id="serverSelect1">
								</select>
							</div>

						</div>
						<div class="control-group">
							<label class="control-label" for="inputEmail">* 角色名</label>
							<div class="controls">
								<input id="roleName" name="roleName" type="text" /> 
								<button type="button" data-loading-text="正在检查"  class="btn btn-danger" id="check" data-toggle="modal" onclick="checkRoleName()">
		          				 			检查
		       					</button>
		       					<p class="help-block">说明：点击检查，输入角色名，自动匹配平台账号和角色名，若输入角色名，检测到多个角色id，
		       					则提示用户，请手动收入角色id，进行匹配。因为合服及命名规则的不同，不同的游戏可能会出现：同一个服中，
		       					相同的角色名对应不同的角色id。故此需要额外进行角色id的匹配</p>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="inputPassword">* 角色ID</label>
							<div class="controls">
								<input id="roleId" name="roleId" disabled="disabled" type="text" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="inputPassword">* 平台账号</label>
							<div class="controls">
								<input id="userId" name="userId" disabled="disabled" value="自动匹配" type="text" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="inputPassword">* 使用人</label>
							<div class="controls">
								<input id="usePeople" name="usePeople" type="text" />
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label" for="inputPassword">* 使用人职务</label>
							<div class="controls">
								<input id="usePeoplePost" name="usePeoplePost" type="text" />
							</div>
						</div>
						
						<div class="control-group">
							<div class="controls">
								<button type="button" data-loading-text="正在添加" class="btn btn-primary " id="add" data-toggle="modal" onclick="addWelfareNum()">
		            				
		          				 			 添加福利号
		       					</button>
								
							</div>
						</div>
						<p style="color: red">注意:</p>
						<p style="color: red">1、请勿添加非内部号（如：新手指导员、版主等），添加后将受到内部号限制，包括但不限于连续5天未登录封禁等</p>
						<p style="color: red">2、内部福利账号不允许进行任何交易或买卖（包括但不限于账号间交易、市场交易、公会交易等），一经发现，将立即做封号处理，且所有交易金额均计入对账。</p>
					</form>
				</div>
			
			</div>
		</div>
		
		<hr style="border: 1px dashed #000; height: 1px">
		<hr style="border: 1px dashed #000; height: 1px">
		<div id="toolbar">
		<div class="row-fluid">
			<div class="span12">
		<div style="margin-left:20px;">
			
			<div class="form-group">
			 <label for="name" >平台:</label>
			   	<select  id="platSel2">
			   	</select>
			   
			
			
			   <label for="name" style="margin-left: 20px" >服务器:</label>
			   	<select  id="serverSelect2">
			   	</select>
			   	
			   	  <label for="name" style="margin-left: 20px" >角色名:</label>
			   	<input id="roleName2"/>
			   	
			   <label for="name" style="margin-left: 20px" >平台账号:</label>
			   	<input id="userId2"/>
			  
			   
			    <div class="btn-group" style="margin-left: 20px" >
			        <button type="button" class="btn btn-primary " data-toggle="modal" onclick="search()">
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
		<table id="welfareNumTable" >
			
		</table>

		

	</div>
	<script type="text/javascript">
	function initTable(url){
		$('#welfareNumTable').bootstrapTable('destroy').bootstrapTable({
			url:url,
			uniqueId:"id",
			toggle:"table",
			cache:"false",
			sidePagination:"server",
			pagination:"true",
			buttonsAlign:"left" ,
			toolbar:"#toolbar",
			toolbarAlign:"right" ,
			showToggle:"true" ,
			showColumns:"true" ,
			showRefresh:"true",
			pageList:"[30, 50, 80]" ,
			pageSize:"30",
			showExport:"true",
	        showExport:"true",
	        exportDataType:"all",
			responseHandler:"dealNumData",
		
            columns: [
						{
    						field: 'platName',
    						title: '平台',
    						sortable:"true",
    						align:"center"
						},
						{
    						field: 'serverName',
    						title: '服务器',
    						sortable:"true",
    						align:"center"
						},

						{
    						field: 'roleName',
    						title: '角色名',
    						sortable:"true",
    						align:"center"
						},
						{
    						field: 'userId',
    						title: '平台账号',
    						sortable:"true",
    						align:"center"
						},
						{
    						field: 'roleId',
    						title: '角色ID',
    						sortable:"true",
    						align:"center"
						},
						{
    						field: 'usePeople',
    						title: '使用人',
    						sortable:"true",
    						align:"center"
						},
						{
    						field: 'usePeoplePost',
    						title: '使用人职<br/>务',
    						sortable:"true",
    						align:"center"
						},
						{
    						field: 'vipLevel',
    						title: 'VIP等级',
    						sortable:"true",
    						align:"center"
						},
						{
    						field: 'sumYb',
    						title: '累计发放<br/>元宝',
    						sortable:"true",
    						align:"center"
						},
						{
    						field: 'sumCharge',
    						title: '实际总充值<br/>(rmb)',
    						sortable:"true",
    						align:"center"
						},
						{
    						field: 'topCharge',
    						title: '充值上限<br/>(rmb)',
    						sortable:"true",
    						align:"center"
						},
						{
    						field: 'numOfDays',
    						title: '连续未登<br/>天数',
    						sortable:"true",
    						align:"center"
						},
						
						{
    						field: 'level',
    						title: '等级',
    						sortable:"true",
    						align:"center"
						},
						{
    						field: 'combat',
    						title: '当前战力',
    						sortable:"true",
    						align:"center"
						},
						{
    						field: 'surplusYb',
    						title: '剩余元宝',
    						sortable:"true",
    						align:"center"
						},
						{
    						field: 'createName',
    						title: '添加人',
    						sortable:"true",
    						align:"center"
						},
						{
    						field: 'createDate1',
    						title: '设置时间',
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
    						field: 'operate',
    						title: '操作',
    						align:"center"
						}
						
                  ]
            
        });
	}
	</script>
</body>
</html>
